package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Grabber extends Subsystem {

	static final double grabberSpeed = SmartDashboard.getNumber("Grabber Speed", 0.5);
	
	public static WPI_TalonSRX leftGrabberMotor = new WPI_TalonSRX(RobotMap.leftGrabberMotor);
	public static WPI_TalonSRX rightGrabberMotor = new WPI_TalonSRX(RobotMap.rightGrabberMotor);
	
    public void initDefaultCommand() {
    }
    
    public void moveGrabber(int speed) {
    	if (speed == 1) {
    		leftGrabberMotor.set(grabberSpeed);
    		rightGrabberMotor.set(grabberSpeed);
    	} else if (speed == -1) {
    		leftGrabberMotor.set(-grabberSpeed);
    		rightGrabberMotor.set(-grabberSpeed);
    	} else {
    		leftGrabberMotor.set(0);
    		rightGrabberMotor.set(0);
    	}
    }
}

