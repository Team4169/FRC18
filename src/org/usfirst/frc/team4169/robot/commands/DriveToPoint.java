package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.Vec2d;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToPoint extends CommandGroup {
    public DriveToPoint(Vec2d current, Vec2d target) {
    	Vec2d vec = target.sub(current);
    	
    	addSequential(new TurnForDegrees(180 / Math.PI * (vec.getTheta() - Robot.kDriveTrain.angle)));
    	addSequential(new DriveToDistance(vec.getR()));
    }
}
