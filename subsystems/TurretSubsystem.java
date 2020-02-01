/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
  /**
   * Creates a new TurretSubsystem.
   */

  public static WPI_VictorSPX turretOne = new WPI_VictorSPX(TurretConstants.kturretOne);
  public static WPI_VictorSPX turretTwo = new WPI_VictorSPX(TurretConstants.kturretTwo);
  public TurretSubsystem() {

  }

  public void turretRotateLeft (double turn){
    turretOne.set(turn);
    turretTwo.set(turn);
  }

  public void turretRotateRight (double turn) {
    turretOne.set(-turn);  
    turretTwo.set(-turn); 
  }
  
  public void turretStop (){
    turretOne.set(0);
    turretTwo.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
