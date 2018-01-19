package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLift extends Command {

    public MoveLift() {
        requires(Robot.kLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed, left, right;
    	
    	left = OI.getInstance().controller.getTriggerAxis(GenericHID.Hand.kLeft);
    	right = OI.getInstance().controller.getTriggerAxis(GenericHID.Hand.kRight);
    	
    	left = (left - 0.15) / 4;
    	right = (right - 0.15) / 4;
    	
    	if (left < 0) left = 0;
    	if (right < 0) right = 0;
    	
    	speed = right - left;
    	
    	Robot.kLift.moveLift(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kLift.moveLift(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}