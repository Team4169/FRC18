package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLift extends Command {
	boolean done = false;
	
    public MoveLift() {
        requires(Robot.kLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.getInstance().controller.getTriggerAxis(GenericHID.Hand.kLeft) >= 0.15) {
    		Robot.kLift.moveLift(Lift.Direction.eDown);
    	} else if (OI.getInstance().controller.getTriggerAxis(GenericHID.Hand.kRight) >= 0.15) {
   			Robot.kLift.moveLift(Lift.Direction.eUp);
   		} else {
   			done = true;
   		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.kLift.atTop) {
    		return true;
    	}
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kLift.moveLiftToPosition(Robot.kLift.getLiftPosition());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		end();
    }
}