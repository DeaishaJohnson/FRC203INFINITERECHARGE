/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants.FlywheelConstants;

public class AngleAdjust extends PIDSubsystem {
  /**
   * Creates a new AngleAdjust.
   */

  //Instantiate and create motor objects here
  private static WPI_VictorSPX angleMotor = new WPI_VictorSPX(FlywheelConstants.kangleMotor);

  //Instantiate and create sensor objects here
  private static DigitalInput adjustLowerLimit = new DigitalInput(2);

  //Constructor Method used for setting up the object and the PID controller
  public AngleAdjust() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
        angleMotor.setNeutralMode(NeutralMode.Brake);
  }

  //Method to manually adjust the shooter hood angle forward
  public void adjustForward(){
    angleMotor.set(0.3);
  }

  //Method to manually adjust the shooter hood angle reverse
  public void adjustReverse(){
    angleMotor.set(-0.3);
  }

  //Method to stop adjusting the shooter hood angle
  public void stop(){
    angleMotor.set(0);
  }

  //Method to check the state of the lower limit switch placed for the shooter hood
  public boolean lowerLimit(){
    return adjustLowerLimit.get();
  }

  //Method that outputs the result from the PID controller to the motors
  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }

  //Method that will send input to the PID controller from your sensors.
  @Override
  public double getMeasurement() {
    // Return the sensor data below that you want to send into the PID controller
    return 0;
  }
}
