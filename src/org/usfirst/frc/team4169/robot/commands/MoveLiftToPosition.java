package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftToPosition extends Command {
	double inches;
	
    public MoveLiftToPosition(double distance) {
        requires(Robot.kLift);
        inches = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kLift.moveLiftToPosition(inches);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.kLift.atTop) {
    		Robot.kLift.moveLiftToPosition(Robot.kLift.getLiftPosition());
    		return true;
    	}
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
}
