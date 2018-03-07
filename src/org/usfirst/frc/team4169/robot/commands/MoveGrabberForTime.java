package org.usfirst.frc.team4169.robot.commands;
import org.usfirst.frc.team4169.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGrabberForTime extends Command {

	double timeToRun;
	double initTime;
	double speed;
	
    public MoveGrabberForTime(double spd, double seconds) {
    	speed = spd;
    	timeToRun = seconds;
        requires(Robot.kGrabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kGrabber.moveGrabber(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Timer.getFPGATimestamp() - initTime > timeToRun;
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
