package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Grabber extends Subsystem {

	static final double grabberSpeed = SmartDashboard.getNumber("Grabber Speed", 0.5);
	static final double executions = 20;
	int timesPressed = 0;
	
	public static Spark leftGrabberMotor = new Spark(RobotMap.leftGrabberMotor);
	public static Spark rightGrabberMotor = new Spark(RobotMap.rightGrabberMotor);
	public static DigitalInput limitSwitch = new DigitalInput(1);
	
	public static enum Speed {
		eIn, eStop, eOut;
	}
	
	public Grabber() {
		leftGrabberMotor.setInverted(true);
	}
	
    public void initDefaultCommand() {
    }
    
    public void moveGrabber(Speed spd) {
    	int speed = spd.ordinal() - 1;
    	leftGrabberMotor.set(speed * grabberSpeed);
    	rightGrabberMotor.set(speed * grabberSpeed);
    }
    public boolean isSwitchSet() {
    	if(limitSwitch.get()) {
    		timesPressed++;
    	} else {
    		timesPressed = 0;
    	}
    	
    	return timesPressed > executions;
    }
}

