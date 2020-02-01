/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ColorWheelConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionTrackingSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  private final DriveSubsystem  m_driveCommand = new DriveSubsystem();
  private final XboxController m_driverController = new XboxController(0);
  private final XboxController m_operatorController = new XboxController(1);
  private final FlywheelSubsystem m_flywheelSubsystem = new FlywheelSubsystem();
  private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
  private final VisionTrackingSubsystem m_visionTrackingSubsystem = new VisionTrackingSubsystem();
  private final ColorWheelConstants m_colorWheelSubsysytem = new ColorWheelConstants();
  
  
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    
//INCORRECT BUT WORKS ?
m_driveCommand.setDefaultCommand(
        
  new RunCommand(() -> m_driveCommand.driveCartesian(m_driverController.getRawAxis(2)*-1.00 + 
      m_driverController.getRawAxis(3)*1.00,
      m_driverController.getY(GenericHID.Hand.kLeft),
                        m_driverController.getX(GenericHID.Hand.kRight)),
                        m_driveCommand)
    );
//INCORRECT BUT WORKS ?



/*  // CORRECT BUT DOESN'T WORK 
m_driveCommand.setDefaultCommand(
    
new RunCommand(() -> m_driveCommand.driveCartesian(m_driverController.getY(GenericHID.Hand.kLeft),
                        m_driverController.getRawAxis(2)*-1.00 + 
                        m_driverController.getRawAxis(3)*1.00,
                        m_driverController.getX(GenericHID.Hand.kRight)),
                        m_driveCommand)
    );
*/      // CORRECT BUT DOESN'T WORK 


  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //FLYWHEEL BUTTONS--------------------------------------------------------------------------------------------------
    new JoystickButton(m_driverController, Button.kA.value)
    .whenPressed(() -> m_flywheelSubsystem.forwardFlywheel(0.8), m_driveCommand)
    .whenReleased(() -> m_flywheelSubsystem.forwardFlywheel(0), m_driveCommand);
    //-------------------------------------------------------------------------------------------------------------------

    //TURRET BUTTONS-----------------------------------------------------------------------------------------------------
    new JoystickButton(m_operatorController, Button.kX.value)
    .whenPressed(() -> m_turretSubsystem.turretRotateLeft(0.5), m_turretSubsystem)
    .whenReleased(() -> m_turretSubsystem.turretStop(), m_turretSubsystem);

    new JoystickButton(m_operatorController, Button.kY.value)
    .whenPressed(() -> m_turretSubsystem.turretRotateRight(0.5), m_turretSubsystem)
    .whenReleased(() -> m_turretSubsystem.turretStop(), m_turretSubsystem);

    //-------------------------------------------------------------------------------------------------------------------
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
    // An ExampleCommand will run in autonomous
  }

