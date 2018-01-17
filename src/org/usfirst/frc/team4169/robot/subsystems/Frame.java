package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Frame extends Subsystem {

	public static WPI_TalonSRX extensionMotor = new WPI_TalonSRX(RobotMap.extensionMotor);
	
	
    public void initDefaultCommand() {
    }
    
    public void moveFrame(int speed){
    	
    	if(speed == 1){
    		extensionMotor.set(0.5);
    	}
    	else if(speed == -1){
    		extensionMotor.set(-0.5);
    	}
    	else{
    		extensionMotor.set(0);
    	}
    }
}

