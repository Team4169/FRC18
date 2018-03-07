package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SlowMode2 extends Command {

    public SlowMode2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kGrabber.slowMode = 0.5;
    	Robot.kLift.slowMode = 0.5;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	 return OI.getInstance().controller2.getYButton();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kGrabber.slowMode = 1;
    	Robot.kLift.slowMode = 1;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
