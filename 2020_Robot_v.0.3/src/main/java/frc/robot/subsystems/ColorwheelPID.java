/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants.ColorwheelConstants;
import frc.robot.Constants.FlywheelConstants;

public class ColorwheelPID extends PIDSubsystem {

//Create motor controller objects
private static WPI_VictorSPX colorwheel = new WPI_VictorSPX (ColorwheelConstants.kcolorwheel);

private final Encoder colorwheelEncoder = new Encoder (0, 1, false, CounterBase.EncodingType.k4X);
//private final SimpleMotorFeedforward m_shooterFeedforward = new SimpleMotorFeedforward(FlywheelConstants.ksVolts, kv)
public static PIDController ColorwheelPID; 

private final SimpleMotorFeedforward m_colorwheelFeedforward =
new SimpleMotorFeedforward(FlywheelConstants.kSVolts,
                           FlywheelConstants.kVVoltSecondsPerRotation);

  public ColorwheelPID() {
    super(
        // The PIDController used by the subsystem
        ColorwheelPID = new PIDController(ColorwheelConstants.kP, ColorwheelConstants.kI, ColorwheelConstants.kD));
          getController().setTolerance(ColorwheelConstants.kTolerance);
          colorwheelEncoder.setDistancePerPulse(ColorwheelConstants.kEncoderDistancePerPulse);
          setSetpoint(ColorwheelConstants.kTargetRPS);

        colorwheel.setNeutralMode(NeutralMode.Brake);

        SmartDashboard.putNumber("kP", ColorwheelPID.getP());
        SmartDashboard.putNumber("kI", ColorwheelPID.getI());
        SmartDashboard.putNumber("kD", ColorwheelPID.getD());
        SmartDashboard.putNumber("Tolerance", ColorwheelConstants.kTolerance);
        SmartDashboard.putNumber("Setpoint", ColorwheelConstants.kTargetRPS);
  }

  //MANUAL DRIVEN METHODS
  public void spinForward (double speed){
    colorwheel.set(speed);
  }
  
  public void spinReverse (double speed){
    colorwheel.set(-speed);
  }
  
  public void stopFlywheel (){
    colorwheel.set(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Rate", colorwheelEncoder.getRate());
    SmartDashboard.putNumber("kP", ColorwheelPID.getP());
    SmartDashboard.putNumber("kI", ColorwheelPID.getI());
    SmartDashboard.putNumber("kD", ColorwheelPID.getD());
    SmartDashboard.putNumber("Tolerance", ColorwheelConstants.kTolerance);
    SmartDashboard.putNumber("Setpoint", ColorwheelConstants.kTargetRPS);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    colorwheel.set(output + m_colorwheelFeedforward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return colorwheelEncoder.getRate();
  }
}
