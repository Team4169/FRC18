package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFrame extends Command {

    public MoveFrame() {
        requires(Robot.kFrame);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.getInstance().controller.getAButton() == true){
    		Robot.kFrame.moveFrame(1);
    	}
    	else if(OI.getInstance().controller.getBButton() == true){
    		Robot.kFrame.moveFrame(-1);
    	}
    	else{
    		Robot.kFrame.moveFrame(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kFrame.moveFrame(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
