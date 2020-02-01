/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static class DriveConstants {
       public static int kleftOne = 1;
       public static int kleftTwo = 1;
       public static int krightOne = 1;
       public static int krightTwo = 1;
    }

    public static class FlywheelConstants {
        public static int kflywheelOne = 1;
        public static int kflywheelTwo = 1;
    }

    public static class ColorWheelConstants {
        public static int kcolorWheel = 1;
        
        public static Color kblueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
        public static Color kgreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        public static Color kredTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
        public static Color kyellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

        public static ColorMatch colorMatcher = new ColorMatch();

    }

    public static class TurretConstants {
        public static int kturretOne = 1;
        public static int kturretTwo = 2;
    }

}
