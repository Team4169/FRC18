package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGrabberForTime extends Command {
	int direction;
	double seconds;
	Timer timer = new Timer();
    public MoveGrabberForTime(int direction, double seconds) {
        requires(Robot.kGrabber);
        this.direction = direction;
        this.seconds = seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	while(timer.get() <= seconds){
    		Robot.kGrabber.moveGrabber(direction);
    	}
    	timer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return Robot.LimitSwitch.get();
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
