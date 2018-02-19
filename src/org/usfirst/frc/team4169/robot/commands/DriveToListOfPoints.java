package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Vec2d;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveToListOfPoints extends CommandGroup {

    public DriveToListOfPoints(Vec2d initial, int destinations[], double delay) {
    	addSequential(new WaitCommand(delay));
    	for (int i = 0; i < destinations.length; i++) {
    		addSequential(new DriveToPoint(initial, Vec2d.points[destinations[i]]));
    		initial.add(Vec2d.points[destinations[i]]);
    	}
    }
}
