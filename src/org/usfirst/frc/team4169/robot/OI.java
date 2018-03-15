/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4169.robot;

import org.usfirst.frc.team4169.robot.commands.AutoCubePickup;
//import org.usfirst.frc.team4169.robot.commands.DriveToDistance;

import org.usfirst.frc.team4169.robot.commands.SlowMode;
import org.usfirst.frc.team4169.robot.commands.SlowMode2;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	
	//FIRST CONTROLLER PLUGGED INTO LAPTOP WILL AUTOMATICALLY BE CONTROLLER 0. THE PORT
	//THE CONTROLLER IS PLUGGED INTO DOES NOT MATTER.
	
	XboxController controller1 = new XboxController(0);
	XboxController controller2 = new XboxController(1);
	
	//private JoystickButton AButton1 = new JoystickButton(controller1, 1);
	private JoystickButton BButton1 = new JoystickButton(controller1, 2);
	private JoystickButton XButton1 = new JoystickButton(controller1, 3);
	private JoystickButton XButton2 = new JoystickButton(controller2, 3);
	
	public OI(){
	//	AButton1.whenPressed(new DriveToDistance(SmartDashboard.getNumber("distanceToDrive", 120.0)));
		BButton1.whenPressed(new AutoCubePickup());
		XButton1.whenPressed(new SlowMode());
		XButton2.whenPressed(new SlowMode2());
	}
	
	public XboxController getController(int value) {
		switch(value) {
			case 1:
				return controller1;
			case 2:
				return controller2;
			default:
				return controller1;
		}
	}
	
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
