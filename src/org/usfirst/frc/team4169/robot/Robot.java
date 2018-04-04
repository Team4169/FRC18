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
import org.usfirst.frc.team4169.robot.commands.DriveToDistance;
import org.usfirst.frc.team4169.robot.commands.DriveToListOfPoints;
import org.usfirst.frc.team4169.robot.commands.DriveWithController;
import org.usfirst.frc.team4169.robot.commands.MoveLift;
import org.usfirst.frc.team4169.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4169.robot.subsystems.Grabber;
import org.usfirst.frc.team4169.robot.subsystems.Lift;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Preferences;


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
	//public static final Limelight limelight = new Limelight();
	
	Command m_autonomousCommand;
	Command moveLift = new MoveLift();
	
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData("Drive Train", kDriveTrain);
//		
//		double emptyArr[] = {};
//		SmartDashboard.putNumberArray("pointList1", emptyArr);
//		SmartDashboard.putNumberArray("pointList2", emptyArr);
//		SmartDashboard.putNumberArray("pointList3", emptyArr);
//		SmartDashboard.putNumberArray("pointList4", emptyArr);
		
//		SmartDashboard.putNumber("Lift Speed", 1);
//		SmartDashboard.putNumber("distanceToDrive", 120.0);
		
	//	limelight.setLedMode(Limelight.LightMode.eOff);
//		try {
//			Thread.sleep(22500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	//	limelight.setLedMode(Limelight.LightMode.eOn);
	//	limelight.setLedMode(Limelight.LightMode.eOff);		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		//limelight.setLedMode(Limelight.LightMode.eOn);
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
		kDriveTrain.driveSafetyEnabled(false);
		
		String gameData = "";
		while (gameData.length() == 0) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		
		char swi = gameData.charAt(0);
		char sca = gameData.charAt(1);
		int slot = (int)SmartDashboard.getNumber("slot", 1);
		Vec2d start = DriveToListOfPoints.startingPositions[slot - 1];
		
		double emptyArr[] = {};
		
		double doubles1[] = SmartDashboard.getNumberArray("pointList1", emptyArr);
		int arr1[] = new int[doubles1.length];
		for (int i = 0; i < doubles1.length; i++) {
			arr1[i] = (int)doubles1[i];
		}
		double doubles2[] = SmartDashboard.getNumberArray("pointList2", emptyArr);
		int arr2[] = new int[doubles2.length];
		for (int i = 0; i < doubles2.length; i++) {
			arr2[i] = (int)doubles2[i];
		}
		double doubles3[] = SmartDashboard.getNumberArray("pointList3", emptyArr);
		int arr3[] = new int[doubles3.length];
		for (int i = 0; i < doubles3.length; i++) {
			arr3[i] = (int)doubles3[i];
		}
		double doubles4[] = SmartDashboard.getNumberArray("pointList4", emptyArr);
		int arr4[] = new int[doubles4.length];
		for (int i = 0; i < doubles4.length; i++) {
			arr4[i] = (int)doubles4[i];
		}
		
		if (swi == 'L') {
			if (sca == 'L') {
				switch((int)SmartDashboard.getNumber("sos1", 2)) {
					case 0:
						m_autonomousCommand = new AutoCommand(slot, (int)SmartDashboard.getNumber("dir1", 1),
								(int)SmartDashboard.getNumber("sos1", 1), SmartDashboard.getNumber("delay1", 1), 0);
						break;
					case 1:
						m_autonomousCommand = new AutoCommand(slot, (int)SmartDashboard.getNumber("dir1", 1),
								(int)SmartDashboard.getNumber("sos1", 1), SmartDashboard.getNumber("delay1", 1), 1);
						break;
					case 2:
					default:
						m_autonomousCommand = new DriveToListOfPoints(start, arr1, SmartDashboard.getNumber("delay1", 0));
						break;
				}
			} else {
				switch((int)SmartDashboard.getNumber("sos2", 2)) {
					case 0:
						m_autonomousCommand = new AutoCommand(slot, (int)SmartDashboard.getNumber("dir2", 1),
								(int)SmartDashboard.getNumber("sos2", 1), SmartDashboard.getNumber("delay2", 1), 0);
						break;
					case 1:
						m_autonomousCommand = new AutoCommand(slot, (int)SmartDashboard.getNumber("dir2", 1),
							(int)SmartDashboard.getNumber("sos2", 1), SmartDashboard.getNumber("delay2", 1), 1);
						break;
					case 2:
					default:
						m_autonomousCommand = new DriveToListOfPoints(start, arr2, SmartDashboard.getNumber("delay2", 0));
						break;
				}
			}
		} else {
			if (sca == 'L') {
				switch((int)SmartDashboard.getNumber("sos3", 2)) {
					case 0:
						m_autonomousCommand = new AutoCommand(slot, (int)SmartDashboard.getNumber("dir3", 1),
								(int)SmartDashboard.getNumber("sos3", 1), SmartDashboard.getNumber("delay3", 1), 0);
						break;
					case 1:
						m_autonomousCommand = new AutoCommand(slot, (int)SmartDashboard.getNumber("dir3", 1),
								(int)SmartDashboard.getNumber("sos3", 1), SmartDashboard.getNumber("delay3", 1), 1);
						break;
					case 2:
					default:
						m_autonomousCommand = new DriveToListOfPoints(start, arr3, SmartDashboard.getNumber("delay3", 0));
						break;
				}
			} else {
				switch((int)SmartDashboard.getNumber("sos4", 2)) {
					case 0:
						m_autonomousCommand = new AutoCommand(slot, (int)SmartDashboard.getNumber("dir4", 1),
								(int)SmartDashboard.getNumber("sos4", 1), SmartDashboard.getNumber("delay4", 1), 0);
						break;
					case 1:
						m_autonomousCommand = new AutoCommand(slot, (int)SmartDashboard.getNumber("dir4", 1),
								(int)SmartDashboard.getNumber("sos4", 1), SmartDashboard.getNumber("delay4", 1), 1);
						break;
					case 2:
					default:
						m_autonomousCommand = new DriveToListOfPoints(start, arr4, SmartDashboard.getNumber("delay4", 0));
						break;
				}
			}
		}

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		m_autonomousCommand = new DriveToDistance(120);

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();		}
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
		kDriveTrain.setDefaultCommand(new DriveWithController());
		
		System.out.println("Telop called");
		kDriveTrain.driveSafetyEnabled(true);
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		System.out.println("Lift position (pulses): " + kLift.getMotor().getSelectedSensorPosition(0));
	
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		kDriveTrain.pidTest();
	}
}
