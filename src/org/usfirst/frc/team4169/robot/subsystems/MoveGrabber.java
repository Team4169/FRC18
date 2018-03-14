package org.usfirst.frc.team4169.robot.subsystems;
import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.Robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGrabber extends Command {
	
    public MoveGrabber() {
        requires(Robot.kGrabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double left = OI.getInstance().controller2.getTriggerAxis(GenericHID.Hand.kLeft);
    	double right = OI.getInstance().controller2.getTriggerAxis(GenericHID.Hand.kRight);
    	if (left >= 0.15) {
    		Robot.kGrabber.moveGrabber(-left);
    	} else if (right >= 0.15) {
    		Robot.kGrabber.moveGrabber(right); 
    	} else {
    		Robot.kGrabber.moveGrabber(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
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
