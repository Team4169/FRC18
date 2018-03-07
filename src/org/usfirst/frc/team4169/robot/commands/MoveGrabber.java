package org.usfirst.frc.team4169.robot.commands;
import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.subsystems.Grabber;
import org.usfirst.frc.team4169.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGrabber extends Command {
	boolean done = false;
	
    public MoveGrabber() {
        requires(Robot.kGrabber);
        
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.getInstance().controller2.getTriggerAxis(GenericHID.Hand.kLeft) >= 0.15) {
    		Robot.kGrabber.moveGrabber(-OI.getInstance().controller2.getTriggerAxis(GenericHID.Hand.kLeft));
    	} else if (OI.getInstance().controller2.getTriggerAxis(GenericHID.Hand.kRight) >= 0.15) {
    		Robot.kGrabber.moveGrabber(-OI.getInstance().controller2.getTriggerAxis(GenericHID.Hand.kLeft));   		
    	} else {
   			done = true;
   		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//return Robot.kGrabber.isSwitchSet();
    	return done;
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
