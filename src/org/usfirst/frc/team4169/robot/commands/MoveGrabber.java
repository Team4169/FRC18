package org.usfirst.frc.team4169.robot.commands;
import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGrabber extends Command {
	int speed;
	
    public MoveGrabber(int x) {
    	speed = x;
        requires(Robot.kGrabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (speed == 1) {
    		Robot.kGrabber.moveGrabber(1);
    	} else if (speed == -1) {
    		Robot.kGrabber.moveGrabber(-1);
   		} else {
    		Robot.kGrabber.moveGrabber(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.kGrabber.isSwitchSet();
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
