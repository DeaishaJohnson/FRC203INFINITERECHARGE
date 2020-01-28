/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.AngleAdjust;
//import frc.robot.subsystems.ColorwheelPID;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FlywheelPID;
import frc.robot.subsystems.LEDLights;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final Drivetrain m_driveTrain = new Drivetrain();
  private final FlywheelPID m_flywheel = new FlywheelPID();
  //private final ColorwheelPID m_colorFlywheelPID = new ColorwheelPID();
  private final LEDLights m_LEDLights = new LEDLights();
  private final AngleAdjust m_angleAdjust = new AngleAdjust();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.m_driverControllerPort);

  // A simple autonomous routine that shoots the preloaded balls
  private final Command m_autoCommand =
      // Start the command by spinning up the shooter...
      new InstantCommand(m_flywheel::enable, m_flywheel).andThen(
          // Wait until the shooter is at speed before feeding the frisbees
          new WaitUntilCommand(m_flywheel::atSetpoint),
          // Drive forward
          new InstantCommand(m_driveTrain::driveForward, m_driveTrain),
          // Shoot for the specified time
          new WaitCommand(2))
          // Add a timeout (will end the command if, for instance, the shooter never gets up to speed)
          .withTimeout(5)
          // When the command ends, turn off the shooter and the feeder
          .andThen(() -> {
            m_driveTrain.stop();
            m_flywheel.stop();
          });

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands

    // Set the default drive command to split-stick arcade drive
    m_driveTrain.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new RunCommand(() -> m_driveTrain.manualDrive(m_driverController.getY(GenericHID.Hand.kLeft),
                            -1 * m_driverController.getX(GenericHID.Hand.kRight),
                            m_driverController.getX(GenericHID.Hand.kLeft)),
                            m_driveTrain));
  }
  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Move the flywheel forward at 75% speed
    new JoystickButton(m_driverController, Button.kA.value)
        .whenPressed(() -> m_angleAdjust.adjustForward(), m_flywheel)
        .whenReleased(() -> m_angleAdjust.stop());
        //.whenPressed(new ConditionalCommand(
          //Adjust hood of shooter back as long as lower limit switch is not pressed
         // new InstantCommand(m_angleAdjust::adjustForward, m_angleAdjust),
          //Stop motor at lower limit switch
          //new InstantCommand(m_angleAdjust::stop, m_angleAdjust),
         // m_angleAdjust::lowerLimit));

    // Move the flywheel backwards at 75% speed
    new JoystickButton(m_driverController, Button.kB.value)
        .whenPressed(() -> m_angleAdjust.adjustReverse(), m_flywheel)
        .whenReleased(() -> m_angleAdjust.stop());

    // PID Control of flywheel
    // Spin up the shooter when the 'A' button is pressed
    new JoystickButton(m_driverController, Button.kX.value)
        .whenPressed(() -> m_flywheel.reverseFlywheel(0.75), m_flywheel)
        .whenReleased(() -> m_flywheel.stop());
        //.whenPressed(new InstantCommand(m_flywheel::enable, m_flywheel));
        //if(m_flywheel.atSetpoint()){
          //m_driverController.setRumble(RumbleType.kRightRumble, 1);
        //  m_LEDLights.setLight(.56);
        //}

    // Turn off the shooter when the 'B' button is pressed
    //new JoystickButton(m_driverController, Button.kY.value)
    //    .whenPressed(new InstantCommand(m_flywheel::disable, m_flywheel));

    // Drive at half speed when the right bumper is held
    new JoystickButton(m_driverController, Button.kBumperRight.value).whenPressed(() -> m_driveTrain.setMaxOutput(0.5))
        .whenReleased(() -> m_driveTrain.setMaxOutput(1));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoCommand;
    }
}
