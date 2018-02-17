package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Grabber extends Subsystem {

	static final double grabberSpeed = SmartDashboard.getNumber("Grabber Speed", 0.5);
	static final int kPIDLoopIdx = 0;
	static final int closedLoopErrorConstant = 15;
	static final int velocityConstant = 5;
	static final int kTimeoutMs = 10;
	
	public static Spark leftGrabberMotor = new Spark(RobotMap.leftGrabberMotor);
	public static Spark rightGrabberMotor = new Spark(RobotMap.rightGrabberMotor);
	
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
    public boolean checkClosedLoopError() {
    	return false;
    }
}

