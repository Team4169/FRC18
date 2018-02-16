/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */ 
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Just some test code

//
package org.usfirst.frc.team4169.robot;


import org.usfirst.frc.team4169.robot.commands.AutoCommand;
import org.usfirst.frc.team4169.robot.subsystems.DriveTrain;

import org.usfirst.frc.team4169.robot.subsystems.Grabber;
import org.usfirst.frc.team4169.robot.subsystems.Lift;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static final DriveTrain kDriveTrain = new DriveTrain();
	public static final Grabber kGrabber = new Grabber();
	public static final Lift kLift = new Lift();
	public static OI m_oi;
	
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");
	double x = tx.getDouble(0);
	double y = ty.getDouble(0);
	double area = ta.getDouble(0);
	
	Command m_autonomousCommand1;
	Command m_autonomousCommand2;
	Command m_autonomousCommand3;
	Command m_autonomousCommand4;
	
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		
		SmartDashboard.putData("Drive Train", kDriveTrain);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		int x = 0;
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L'){
				x = 1;
			}
			else if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R'){
				x = 2;
			}
			else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L'){
				x = 3;
			}
			else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R'){
				x = 4;
			}
		}
		
		switch(x) {
			case 1:
				m_autonomousCommand1 = new AutoCommand((int)SmartDashboard.getNumber("slot1", 1), (int)SmartDashboard.getNumber("dir1", 1), (int)SmartDashboard.getNumber("sos1", 1), SmartDashboard.getNumber("delay1", 1), (int)SmartDashboard.getNumber("sosDir1", 1));
				break;
			case 2:
				m_autonomousCommand2 = new AutoCommand((int)SmartDashboard.getNumber("slot2", 1), (int)SmartDashboard.getNumber("dir2", 1), (int)SmartDashboard.getNumber("sos2", 1), SmartDashboard.getNumber("delay2", 1), (int)SmartDashboard.getNumber("sosDir2", 1));
				break;
			case 3:
				m_autonomousCommand3 = new AutoCommand((int)SmartDashboard.getNumber("slot3", 1), (int)SmartDashboard.getNumber("dir3", 1), (int)SmartDashboard.getNumber("sos3", 1), SmartDashboard.getNumber("delay3", 1), (int)SmartDashboard.getNumber("sosDir3", 1));
				break;
			case 4:
				m_autonomousCommand4 = new AutoCommand((int)SmartDashboard.getNumber("slot4", 1), (int)SmartDashboard.getNumber("dir4", 1), (int)SmartDashboard.getNumber("sos4", 1), SmartDashboard.getNumber("delay4", 1), (int)SmartDashboard.getNumber("sosDir4", 1));
				break;
		}
		
		
		
		
		

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand1 != null) {
			m_autonomousCommand1.start();
		}
		if (m_autonomousCommand2 != null) {
			m_autonomousCommand2.start();
		}
		if (m_autonomousCommand3 != null) {
			m_autonomousCommand3.start();
		}
		if (m_autonomousCommand4 != null) {
			m_autonomousCommand4.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand1 != null) {
			m_autonomousCommand1.cancel();
		}
		if (m_autonomousCommand2 != null) {
			m_autonomousCommand2.cancel();
		}
		if (m_autonomousCommand3 != null) {
			m_autonomousCommand3.cancel();
		}
		if (m_autonomousCommand4 != null) {
			m_autonomousCommand4.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		kDriveTrain.speedTest();
	}
}
