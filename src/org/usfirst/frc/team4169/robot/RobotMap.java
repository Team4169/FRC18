/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4169.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final int leftFrontMotor = 0;
	public static final int leftBackMotor = 1;
	public static final int rightFrontMotor = 2;
	public static final int rightBackMotor = 3;
	public static final int extensionMotor = 4;
	public static final int liftMotor = 5;
	public static final int leftEncoderPortA = 6;
	public static final int leftEncoderPortB = 7;
	public static final int rightEncoderPortA = 8;
	public static final int rightEncoderPortB = 9;
	public static final int leftGrabberMotor = 10;
	public static final int rightGrabberMotor = 10;
	
}
