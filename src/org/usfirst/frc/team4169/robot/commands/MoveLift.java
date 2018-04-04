package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLift extends Command {
	static final double dead_zone = 0.15;
	
    public MoveLift() {
        requires(Robot.kLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double y = -Robot.m_oi.getController(2).getY(GenericHID.Hand.kLeft);
    	if (Math.abs(y) >= dead_zone) {
    		Robot.kLift.moveLift(joystickToMotorPower(y));
   		} else {
   	    	Robot.kLift.moveLiftToPosition(Robot.kLift.getLiftPosition());
   		}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		end();
    }
    
    private double joystickToMotorPower(double joy) {
    	final double slope = -1.0d / (dead_zone - 1.0d);
    	final double intercept = 1.0d + (1.0d/(dead_zone - 1.0d));
    	
    	double motorPower = 0.0d;
    	
    	if (joy > 0) {
    		motorPower = slope * joy + intercept;
    	} else if (joy < 0) {
    		motorPower = slope * joy - intercept;
    	}
    	
    	return motorPower;
    }
}