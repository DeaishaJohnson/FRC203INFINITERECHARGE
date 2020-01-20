/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;


public class Robot extends TimedRobot {
  
  //port that the color sensor plugs into
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  //creating the color sensor object with the i2c port as its parameter
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

  //Color Match object is needed to register and detect colors
  private final ColorMatch colorMatcher = new ColorMatch();

  //example colors that can be recalibrated for the user's needs
  private final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  @Override
  public void robotInit() {
  colorMatcher.addColorMatch(blueTarget);
  colorMatcher.addColorMatch(greenTarget);
  colorMatcher.addColorMatch(redTarget);
  colorMatcher.addColorMatch(yellowTarget);
  }

  
  @Override
  public void robotPeriodic() {
    Color detectedColor = colorSensor.getColor();

    String colorString;
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    if (match.color == blueTarget){
      colorString = "Blue";
    } else if (match.color == redTarget){
      colorString = "Red";
    } else if (match.color == greenTarget){
      colorString = "Green";
    } else if (match.color == yellowTarget){
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  }
}