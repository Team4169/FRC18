package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLift extends Command {
	boolean done;
	
    public MoveLift() {
        requires(Robot.kLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(OI.getInstance().controller2.getY(GenericHID.Hand.kRight)) >= 0.15) {
    		Robot.kLift.moveLift(OI.getInstance().controller2.getY(GenericHID.Hand.kRight));
   		} else {
   			done = true;
   		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.kLift.atTop || done == true) {
    		return true;
    	}
        return false;
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