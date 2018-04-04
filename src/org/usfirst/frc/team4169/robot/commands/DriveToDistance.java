package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToDistance extends Command {
	double pulses;
	double circumference = 6 * Math.PI;
	
    public DriveToDistance(double distance) {
        requires(Robot.kDriveTrain);
        pulses = distance / circumference * Robot.kDriveTrain.getPulsesPerInch();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveTrain.driveForDistance(pulses);
    	Robot.kDriveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	StringBuilder sb = new StringBuilder();
    	//boolean left = Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("leftFront"));
    	boolean right = Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("rightFront"));
//    	if (left) {
//    		sb.append("left done.");
//    	}
//    	
    	if (right) {
    		sb.append("right done.");
    	}
    	
    	SmartDashboard.putNumber("leftVel", Robot.kDriveTrain.getMotor("leftFront").getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("rightVel", Robot.kDriveTrain.getMotor("rightFront").getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("leftPos", Robot.kDriveTrain.getMotor("leftFront").getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("rightPos", Robot.kDriveTrain.getMotor("rightFront").getSelectedSensorPosition(0));
    	if (sb.length() > 0) System.out.println(sb);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("rightFront"));// &&
//        	Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("rightFront"));
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
