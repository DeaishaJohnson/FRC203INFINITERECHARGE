/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


public class DriveSubsystem extends SubsystemBase {

public static WPI_VictorSPX rightFront = new WPI_VictorSPX(DriveConstants.krightFrontMotor);
public static WPI_VictorSPX rightBack = new WPI_VictorSPX(DriveConstants.krightBackMotor);
public static WPI_VictorSPX leftFront = new WPI_VictorSPX(DriveConstants.kleftFrontMotor);
public static WPI_VictorSPX leftBack = new WPI_VictorSPX(DriveConstants.kleftBackMotor);

private static MecanumDrive mecanumDrive = new MecanumDrive(leftFront, leftBack, rightFront, rightBack);

/*TANK DRIVE ------------

private static SpeedControllerGroup left_c = new SpeedControllerGroup(leftFront, leftBack);
private static SpeedControllerGroup right_c = new SpeedControllerGroup(rightFront, rightBack);
private static DifferentialDrive m_drive = new DifferentialDrive(left_c, right_c);

*/



  public DriveSubsystem() {


    
  }

public void driveCartesian(double ySpeed, double xSpeed, double zRotation){

mecanumDrive.driveCartesian(ySpeed, -xSpeed, zRotation);

}

/*TANK DRIVE ------------
public void manualDrive(double xSpeed, double zRotation){

m_drive.arcadeDrive(xSpeed, zRotation);

}
*/
public void setMaxOutput(double maxOutput){
//m_drive.setMaxOutput(maxOutput); 

mecanumDrive.setMaxOutput(maxOutput);

}



  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
}
