package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;



public class Arm extends Subsystem {

	static WPI_TalonSRX leftGrabberMotor = new WPI_TalonSRX(RobotMap.leftGrabberMotor);
	static WPI_TalonSRX rightGrabberMotor = new WPI_TalonSRX(RobotMap.rightGrabberMotor);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void moveGrabber(int speed) {
    	if (speed == 1) {
    		leftGrabberMotor.set(0.5);
    		rightGrabberMotor.set(0.5);
    	} else if(speed == -1) {
    		leftGrabberMotor.set(-0.5);
    		rightGrabberMotor.set(-0.5);
    	} else {
    		leftGrabberMotor.set(0);
    		rightGrabberMotor.set(0);
    	}
    	
    }
}
