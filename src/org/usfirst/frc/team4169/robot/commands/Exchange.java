package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Exchange extends CommandGroup {

    public Exchange(int slot, double delay, int dir) {
    	addSequential(new WaitCommand(delay));
    	addParallel(new MoveLiftToPosition(20));
    	addSequential(new MoveLiftToPosition(2));
        
        int arr[] = {slot - 1, 16, 17};
        int arr2[] = {3, 9};
        
        if (dir == 1) {
        	arr2[0] += 1;
        	arr2[1] += 1;
        }
        
        addSequential(new DriveToListOfPoints(DriveToListOfPoints.startingPositions[slot - 1], arr, 0));
        addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
        addSequential(new DriveToDistance(-1 * (DriveToListOfPoints.points[17].sub(DriveToListOfPoints.points[16])).getR()));
		addSequential(new DriveToListOfPoints(DriveToListOfPoints.points[16], arr2, 0));
    }
}
