// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Drivetrain Constants
    public static final int d_tl = 0;
    public static final int d_tr = 2;
    public static final int d_bl = 1;
    public static final int d_br = 3;

    //Climber
    public static int forwardChannel = 0;
    public static int reverseChannel = 1;
    public static int leftClimbMotor = 4;
    public static int rightClimbMotor = 1;
    /*public static int leftClimbDutyCycleEncoder = 0;
    public static int rightClimbDutyCycleEncoder = 1;
    public static int leftClimbSEncoderA = 0;
    public static int leftClimbSEncoderB = 1;
    public static int rightClimbSEncoderA = 2;
    public static int rightClimbSEncoderB = 3;*/

    //Conveyor
    public static int motor = 0;
    public static int intakeSol = 3;
    public static int intakeMotor = 4;
    public static int conveyorEncoderA = 2;
    public static int conveyorEncoderB = 3;

    //LEDs
    public static int blinkinPort = 0;
}
