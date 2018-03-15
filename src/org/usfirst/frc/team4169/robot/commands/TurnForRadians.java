package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnForRadians extends Command {
	double radians;
    public TurnForRadians(double value) {
        requires(Robot.kDriveTrain);
        radians = value;
        Robot.kDriveTrain.angle += radians;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveTrain.turnForRadians(radians);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("leftFront")) &&
    			Robot.kDriveTrain.checkClosedLoopError(Robot.kDriveTrain.getMotor("rightFront"));
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
