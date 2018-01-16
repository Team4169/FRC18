package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.RobotMap;
import org.usfirst.frc.team4169.robot.commands.moveLift;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
	static WPI_TalonSRX liftMotor = new WPI_TalonSRX(RobotMap.liftMotor);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new moveLift());
    }
    
    public void moveLift(int speed){
    	if(speed == 1){
    		liftMotor.set(0.5);
    	}
    	else if(speed == -1){
    		liftMotor.set(-0.5);
    	}
    	else{
    		liftMotor.set(0);
    	}
    }
}

