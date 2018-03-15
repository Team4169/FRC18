package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SlowMode extends Command {
	static final double value = 0.6;

    public SlowMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveTrain.setSlowMode(value);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.m_oi.getController(1).getYButton();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kDriveTrain.setSlowMode(1.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
