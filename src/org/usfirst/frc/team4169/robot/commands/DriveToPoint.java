package org.usfirst.frc.team4169.robot.commands;

//import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.Vec2d;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToPoint extends CommandGroup {
    public DriveToPoint(Vec2d current, double currentAngle, Vec2d target) {
    	Vec2d vec = target.sub(current);
    	
    	addSequential(new TurnForDegrees(180.0 / Math.PI * (vec.getTheta() - currentAngle)));
    	addSequential(new DriveToDistance(vec.getR()));
    }
}
