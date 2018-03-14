package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.Vec2d;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveToListOfPoints extends CommandGroup {
	public static final Vec2d startingPositions[] = {Vec2d.makeCart(65.69, 17.25), 
    		Vec2d.makeCart(185.69, 17.25), Vec2d.makeCart(257.69, 17.25)};
	
	//								      0					   		 1					 		 2
	public static final Vec2d points[] = {Vec2d.makeCart(65.69, 60), Vec2d.makeCart(185.69, 60), Vec2d.makeCart(257.69, 60),
	//		3					 	   4					   	   5					 	   6
			Vec2d.makeCart(32.69, 60), Vec2d.makeCart(290.69, 60), Vec2d.makeCart(32.69, 168), Vec2d.makeCart(290.69, 168),
	//		7				   8					  9						10
			Vec2d.makeCart(65, 168), Vec2d.makeCart(258.38, 168), Vec2d.makeCart(32.69, 232), Vec2d.makeCart(290.69, 232),
	//		11					  		12					 		 13					   		 14
			Vec2d.makeCart(32.69, 324), Vec2d.makeCart(290.69, 324), Vec2d.makeCart(51.32, 324), Vec2d.makeCart(272.06, 324),
	//		15					   		 16					 		 17
			Vec2d.makeCart(161.69, 232), Vec2d.makeCart(125.69, 60), Vec2d.makeCart(125.69, 33)};

    public DriveToListOfPoints(Vec2d initial, int destinations[], double delay) {
    	addSequential(new WaitCommand(delay));
    	for (int i = 0; i < destinations.length; i++) {
    		addSequential(new DriveToPoint(initial, Robot.kDriveTrain.angle, points[destinations[i]]));
    		initial = points[destinations[i]];
    		Robot.kDriveTrain.angle = points[destinations[i]].sub(initial).getTheta();
    	}
    }
}
