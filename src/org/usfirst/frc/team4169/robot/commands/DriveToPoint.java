package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.Vec2d;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToPoint extends Command {
	Vec2d initial;
	Vec2d target;
    public DriveToPoint(Vec2d vecInit, Vec2d dest) {
        requires(Robot.kDriveTrain);
        target = dest.sub(vecInit);
        initial = vecInit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveTrain.driveSafetyEnabled(false);
    	Robot.kDriveTrain.turnForDegrees(target.getTheta() - initial.getTheta());
    	Robot.kDriveTrain.driveForDistance(target.getR());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.kDriveTrain.checkClosedLoopError();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kDriveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
