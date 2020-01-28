/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drivetrain extends SubsystemBase {

  //Instantiate and create motor controllers objects 
  private static WPI_VictorSPX frontRight = new WPI_VictorSPX(DriveConstants.kfrontRight);
  private static WPI_VictorSPX backRight = new WPI_VictorSPX(DriveConstants.kbackRight);
  private static WPI_VictorSPX frontLeft = new WPI_VictorSPX(DriveConstants.kfrontLeft);
  private static WPI_VictorSPX backLeft = new WPI_VictorSPX(DriveConstants.kbackLeft);

  
  private static MecanumDrive mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

  //Constructor for Drivetrain Object created in RobotContainer Class
  public Drivetrain() {
    //Invert any motors here
    }

  //Method for driving the robot manually using inputs from a controller
  public void manualDrive(double xSpeed, double ySpeed, double zRotation){
    mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation);
  }

  //Method for driving the robot forward in autonomous (no joystick input)
  public void driveForward(){
    mecanumDrive.driveCartesian(1, 0, 0);
  }

  //Method for driving the robot backward in autonomous (no joystick input)
  public void driveBackward(){
    mecanumDrive.driveCartesian(-1, 0, 0);
  }

  //Method for a right strafe maneuver in autonomous (no joystick input)
  public void strafeRight(){
    mecanumDrive.driveCartesian(0, 1, 0);
  }

  //Method for a left strafe maneuver in autonomous (no joystick input)
  public void strafeLeft(){
    mecanumDrive.driveCartesian(0, -1, 0);
  }
  
  //Method for stopping the robot drive
  public void stop(){
    mecanumDrive.driveCartesian(0, 0, 0);
  }

  /*
    Method to set the max output of the drive motors. 
    This will allow for drivers to have added precision 
    with a slower driving robot
  */
  public void setMaxOutput(double maxOutput){
    mecanumDrive.setMaxOutput(maxOutput);
  }
  
  /*
    This method will be called once per scheduler run. 
    Place SmartDashboard values you would like to see updated here.
  */
  @Override
  public void periodic() {
    
  }
}