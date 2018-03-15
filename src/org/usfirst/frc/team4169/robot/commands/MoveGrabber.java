package org.usfirst.frc.team4169.robot.commands;
import org.usfirst.frc.team4169.robot.Robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGrabber extends Command {
	static final double dead_zone = 0.15;
	
    public MoveGrabber() {
        requires(Robot.kGrabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double left = Robot.m_oi.getController(2).getTriggerAxis(GenericHID.Hand.kLeft);
    	double right = Robot.m_oi.getController(2).getTriggerAxis(GenericHID.Hand.kRight);
    	
    	if (left <= dead_zone) {
    		left = 0;
    	}
    	if (right <= dead_zone) {
    		right = 0;
    	}
    	double speed = right - left;
    	Robot.kGrabber.moveGrabber(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kGrabber.moveGrabber(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
