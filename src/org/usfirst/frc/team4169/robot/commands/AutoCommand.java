package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Vec2d;
import org.usfirst.frc.team4169.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


/**
 *
 */
public class AutoCommand extends CommandGroup {
	static final int switchLiftHeight = 26;
	static final int scaleLiftHeight = 84;

    public AutoCommand(int slot, int dir, int sos, double delay, int sosDir){
    	Vec2d start = Vec2d.startingPositions[slot];
    	addSequential(new WaitCommand(delay));
    	
    	if (dir == 0) {
    		if (sos == 0) {
	    		addParallel(new MoveLiftToPosition(switchLiftHeight));
	    		if (sosDir == 0) {
	    			int arr[] = {slot - 1, 3, 5, 7};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * Vec2d.points[7].sub(Vec2d.points[5]).getR()));
	    			addSequential(new DriveToPoint(Vec2d.points[5], Vec2d.points[9]));
	    		} else {
	    			int arr[] = {slot - 1, 3, 9, 10, 6, 8};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * Vec2d.points[8].sub(Vec2d.points[6]).getR()));
	    			addSequential(new DriveToPoint(Vec2d.points[6], Vec2d.points[10]));
	    		}
	    	} else {
	    		addParallel(new MoveLiftToPosition(scaleLiftHeight));
	    		if (sosDir == 0) {
	    			int arr[] = {slot - 1, 3, 11, 13};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * Vec2d.points[13].sub(Vec2d.points[11]).getR()));
	    			addSequential(new DriveToPoint(Vec2d.points[11], Vec2d.points[9]));
	    		} else {
	    			int arr[] = {slot - 1, 3, 9, 10, 12, 14};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * Vec2d.points[14].sub(Vec2d.points[12]).getR()));
	    			addSequential(new DriveToPoint(Vec2d.points[12], Vec2d.points[10]));
	    		}
	    	}
	    } else {
	    	if (sos == 0) {
	    		addParallel(new MoveLiftToPosition(switchLiftHeight));
	    		if (sosDir == 0) {
	    			int arr[] = {slot - 1, 4, 10, 9, 5, 7};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * Vec2d.points[7].sub(Vec2d.points[5]).getR()));
	    			addSequential(new DriveToPoint(Vec2d.points[5], Vec2d.points[9]));
	    		} else {
	    			int arr[] = {slot - 1, 4, 6, 8};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * Vec2d.points[8].sub(Vec2d.points[6]).getR()));
	    			addSequential(new DriveToPoint(Vec2d.points[6], Vec2d.points[10]));
	    		}
	    	} else {
	   			addParallel(new MoveLiftToPosition(scaleLiftHeight));
	   			if (sosDir == 0) {
	   				int arr[] = {slot - 1, 4, 10, 9, 11, 13};
	   				addSequential(new DriveToListOfPoints(start, arr, 0));
	   				addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	   				addSequential(new DriveToDistance(-1 * Vec2d.points[13].sub(Vec2d.points[11]).getR()));
	   				addSequential(new DriveToPoint(Vec2d.points[11], Vec2d.points[9]));
    			} else {
	   				int arr[] = {slot - 1, 4, 12, 14};
	   				addSequential(new DriveToListOfPoints(start, arr, 0));
	   				addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	   				addSequential(new DriveToDistance(-1 * Vec2d.points[14].sub(Vec2d.points[12]).getR()));
	   				addSequential(new DriveToPoint(Vec2d.points[12], Vec2d.points[10]));
	   			}
	   		}
    	}
    }
}
