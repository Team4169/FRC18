package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.RobotMap;
import org.usfirst.frc.team4169.robot.commands.MoveClimber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	static WPI_TalonSRX climberMotor = new WPI_TalonSRX(RobotMap.climberMotor);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MoveClimber());
    	
    }
    public void moveClimber(int speed) {
    	if (speed == 1) {
    		climberMotor.set(Robot.climberSpeed);
    		
    	} else if(speed == -1) {
    		climberMotor.set(-Robot.climberSpeed);
    	
    	} else {
    		climberMotor.set(0);
    	}
    }
}
