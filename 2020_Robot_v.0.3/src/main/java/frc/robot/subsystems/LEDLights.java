/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDLights extends SubsystemBase {
  
  Spark lights = new Spark(0);

  public LEDLights() {
    SmartDashboard.getNumber("LED", 0);
  }

  public void setLight(double lightValue){
    lights.set(lightValue);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("LED", 0);
  }
}
