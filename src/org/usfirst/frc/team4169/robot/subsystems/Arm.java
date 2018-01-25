package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.RobotMap;
import org.usfirst.frc.team4169.robot.commands.MoveArm;

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
    	setDefaultCommand(new MoveArm());
    }
    public void moveGrabber(int speed) {
    	if (speed == 1) {
    		leftGrabberMotor.set(Robot.armSpeed);
    		rightGrabberMotor.set(Robot.armSpeed);
    	} else if(speed == -1) {
    		leftGrabberMotor.set(-Robot.armSpeed);
    		rightGrabberMotor.set(-Robot.armSpeed);
    	} else {
    		leftGrabberMotor.set(0);
    		rightGrabberMotor.set(0);
    	}
    	
    }
}

