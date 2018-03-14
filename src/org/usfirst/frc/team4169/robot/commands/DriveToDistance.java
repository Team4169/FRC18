package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToDistance extends Command {
	double pulses;
    public DriveToDistance(double distance) {
        requires(Robot.kDriveTrain);
        pulses = distance / 6 / Math.PI * DriveTrain.pulses;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveTrain.driveForDistance(pulses);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	StringBuilder sb = new StringBuilder();
    	boolean left = Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("leftFront"));
//    	boolean right = Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("rightFront"));
    	if (left) {
    		sb.append("left done.");
    	}
    	
//    	if (right) {
//    		sb.append("right done.");
//    	}
    	
    	System.out.print(sb);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("leftFront")); //&& 
        		//Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("rightFront"));
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("DriveToDistance is finished");
    	Robot.kDriveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
