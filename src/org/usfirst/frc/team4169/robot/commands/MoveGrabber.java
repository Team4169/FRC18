package org.usfirst.frc.team4169.robot.commands;
import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGrabber extends Command {
	Grabber.Speed speed;
	
    public MoveGrabber(Grabber.Speed spd) {
    	speed = spd;
        requires(Robot.kGrabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kGrabber.moveGrabber(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.kGrabber.isSwitchSet();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kGrabber.moveGrabber(Grabber.Speed.eStop);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
