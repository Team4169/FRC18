package org.usfirst.frc.team4169.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team4169.robot.commands.DriveWithController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.RobotMap;



public class DriveTrain extends Subsystem {
	
	static WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.leftFrontMotor);
	static WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(RobotMap.leftBackMotor);
	static WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(RobotMap.rightFrontMotor);
	static WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(RobotMap.rightBackMotor);
	
	static SpeedControllerGroup left = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
	static SpeedControllerGroup right = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);
	
	static DifferentialDrive drive = new DifferentialDrive(left, right);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithController());
    }
    
    //drives the robot using controller values
    public void drive() {
    	double leftY = OI.getInstance().controller.getY(GenericHID.Hand.kLeft);
    	double rightY = OI.getInstance().controller.getY(GenericHID.Hand.kRight);
    		
    	if (Math.abs(leftY) < 0.2) {
    		leftY = 0;
    	}
    		
    	if (Math.abs(rightY) < 0.2) {
    		rightY = 0;
    	}
    		
    	if (OI.getInstance().controller.getBumper(GenericHID.Hand.kRight)) {
    		leftY = leftY / 4;
    		rightY = rightY / 4;
    	}
    		
    	drive.tankDrive(leftY, rightY);
   }
    
    //stops the robot
    public void stop() {
    	drive.tankDrive(0, 0);
    }
}

