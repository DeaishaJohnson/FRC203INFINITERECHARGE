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
import frc.robot.Constants.FlywheelConstants;

public class FlywheelPID extends PIDSubsystem {

   //Create motor controller objects
   private static WPI_VictorSPX flywheel1 = new WPI_VictorSPX (FlywheelConstants.kflywheel1);
   private static WPI_VictorSPX flywheel2 = new WPI_VictorSPX (FlywheelConstants.kflywheel2);

   private final Encoder flywheelEncoder = new Encoder (0, 1, false, CounterBase.EncodingType.k4X);
   
   //private final SimpleMotorFeedforward m_shooterFeedforward = new SimpleMotorFeedforward(FlywheelConstants.ksVolts, kv)
   public static PIDController FlywheelPID; 
   
   private final SimpleMotorFeedforward m_flywheelFeedforward =
   new SimpleMotorFeedforward(FlywheelConstants.kSVolts,
                              FlywheelConstants.kVVoltSecondsPerRotation);

  public FlywheelPID() {
    super(
        // The PIDController used by the subsystem
        FlywheelPID = new PIDController(FlywheelConstants.kP, FlywheelConstants.kI, FlywheelConstants.kD));
          getController().setTolerance(FlywheelConstants.kShooterTolerance);
          flywheelEncoder.setDistancePerPulse(FlywheelConstants.kEncoderDistancePerPulse);
          setSetpoint(FlywheelConstants.kShooterTargetRPS);

        flywheel2.setInverted(true);
        flywheel1.setNeutralMode(NeutralMode.Coast);
        flywheel2.setNeutralMode(NeutralMode.Coast);

        SmartDashboard.putNumber("Rate", getRate());
        SmartDashboard.putNumber("kP", FlywheelPID.getP());
        SmartDashboard.putNumber("kI", FlywheelPID.getI());
        SmartDashboard.putNumber("kD", FlywheelPID.getD());
        SmartDashboard.putNumber("Tolerance", FlywheelConstants.kShooterTolerance);
        SmartDashboard.putNumber("Setpoint", FlywheelConstants.kShooterTargetRPS);
  }

  public void forwardFlywheel (double speed){
  flywheel1.set(speed);
  flywheel2.set(speed);
  SmartDashboard.putNumber("Rate", getRate());
}

  public void reverseFlywheel (double speed){
    flywheel1.set(-speed);
    flywheel2.set(-speed);
    SmartDashboard.putNumber("Rate", getRate());
  }

  public void stop(){
    flywheel1.set(0);
    flywheel2.set(0);
    SmartDashboard.putNumber("Rate", getRate());
  }

  public boolean atSetpoint(){
    return FlywheelPID.atSetpoint();
  }

  public double getDistance (){
    return flywheelEncoder.getDistance();
  }

  public double getRate (){
    return flywheelEncoder.getRate();
  }

  public void resetEncoders (){
     flywheelEncoder.reset();
  }

  public void setkP(double kP) {
    FlywheelPID.setP(kP);
  }

  public void setkI(double kI){
    FlywheelPID.setI(kI);
  }

  public void setkD(double kD){
    FlywheelPID.setD(kD);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Rate", getRate());
    SmartDashboard.putNumber("kP", FlywheelPID.getP());
    SmartDashboard.putNumber("kI", FlywheelPID.getI());
    SmartDashboard.putNumber("kD", FlywheelPID.getD());
    SmartDashboard.putNumber("Tolerance", FlywheelConstants.kShooterTolerance);
    SmartDashboard.putNumber("Setpoint", FlywheelConstants.kShooterTargetRPS);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    flywheel1.set(output + m_flywheelFeedforward.calculate(setpoint));
    flywheel2.set(output + m_flywheelFeedforward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    // Check for new PID Constants and replace them if they changed on the SMARTDASHBOARD
    setkP(SmartDashboard.getNumber("kP", FlywheelPID.getP()));
    setkI(SmartDashboard.getNumber("kI", FlywheelPID.getI()));
    setkD(SmartDashboard.getNumber("kD", FlywheelPID.getD()));
    FlywheelPID.setSetpoint(SmartDashboard.getNumber("Setpoint", FlywheelConstants.kShooterTargetRPS));
    FlywheelPID.setTolerance(SmartDashboard.getNumber("Tolerance", FlywheelConstants.kShooterTolerance));
    // Return the process variable measurement here
    return getRate();
  }
}
