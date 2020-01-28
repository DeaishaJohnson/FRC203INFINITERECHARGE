/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
    public static final class DriveConstants{
        public static int kfrontLeft = 4;
        public static int kfrontRight = 2;
        public static int kbackLeft = 1;
        public static int kbackRight = 3;
    }

    public static final class FlywheelConstants{
        public static int kflywheel1 = 5;
        public static int kflywheel2 = 6;
        public static int kangleMotor = 7;

        //PID CONSTANTS
        public static double kP = 0.0001;
        public static double kI = 0.0055;
        public static double kD = 0;

        //Specfic target and tolerance values for: 21FT SHOT
        public static double kShooterTargetRPS = 5000;
        public static double kShooterTolerance = 50; 

        //Encoder Constants for PID
        public static final int kencoderPort1 = 0;
        public static final int kencoderPort2 = 1;
        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterInches = 7.25; //TODO THIS IS NOT CORRECT
        public static final double kEncoderDistancePerPulse =
            // Assumes the encoders are directly mounted on the wheel shafts //TODO NEED TO ADD GEAR RATIO
            100 / (double) kEncoderCPR;
       
        public static final double kSVolts = 0.004;
        public static final double kVVoltSecondsPerRotation =
        // Should have value 12V at free speed...
        12 / 18730;
    }

    public static final class ColorwheelConstants{
        public static int kcolorwheel = 7;

        //PID CONSTANTS
        public static double kP = 0.0001;
        public static double kI = 0.0055;
        public static double kD = 0;

        //Specfic target and tolerance values for: 21FT SHOT
        public static double kTargetRPS = 100;
        public static double kTolerance = 5; 

        //Encoder Constants for PID
        public static final int kencoderPort1 = 2;
        public static final int kencoderPort2 = 3;
        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterInches = 4; //TODO THIS IS NOT CORRECT
        public static final double kEncoderDistancePerPulse =
            // Assumes the encoders are directly mounted on the wheel shafts //TODO NEED TO ADD GEAR RATIO
            1 / (double) kEncoderCPR;
       
        public static final double kSVolts = 0.004;
        public static final double kVVoltSecondsPerRotation =
        // Should have value 12V at free speed...
        12 / 18730;
    }

    public static final class OIConstants{
        public static int m_driverControllerPort = 0;
    }

    
}
