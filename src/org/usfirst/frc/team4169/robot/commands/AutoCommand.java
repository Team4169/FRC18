package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;
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
	
	static final int approachingLeftSwitch = 5,
			approachingRightSwitch = 6,
			leftSwitch = 7,
			rightSwitch = 8,
			betweenLeftSwitchAndScale = 9,
			betweenRightSwitchAndScale = 10,
			approachingLeftScale = 11,
			approachingRightScale = 12,
			leftScale = 13,
			rightScale = 14;

	//AutoCommand places a cube at specified spot from beginning slot
	//slot is the starting position. 1, 2, or 3 from the left
	//dir is the initial turn, 0 for left and 1 for right
	//sos is switch or scale, switch is 0, scale is 1
	//delay is the time to wait before moving so we do not collide with other robots, in seconds
	//sosDir is which side the switch or scale target we want to go to is on, left is 0, right is 1
	
    public AutoCommand(int slot, int dir, int sos, double delay, int sosDir){
    	Vec2d start = DriveToListOfPoints.startingPositions[slot];
    	addSequential(new WaitCommand(delay));
    	
    	if (dir == 0) {
    		if (sos == 0) {
	    		addParallel(new MoveLiftToPosition(switchLiftHeight));
	    		if (sosDir == 0) {
	    			int arr[] = {slot - 1, 3, 5, 7};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * DriveToListOfPoints.points[leftSwitch].sub(DriveToListOfPoints.points[approachingLeftSwitch]).getR()));
	    			addSequential(new DriveToPoint(DriveToListOfPoints.points[approachingLeftSwitch], Robot.kDriveTrain.angle, DriveToListOfPoints.points[betweenLeftSwitchAndScale]));
	    		} else {
	    			int arr[] = {slot - 1, 3, 9, 10, 6, 8};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * DriveToListOfPoints.points[rightSwitch].sub(DriveToListOfPoints.points[approachingRightSwitch]).getR()));
	    			addSequential(new DriveToPoint(DriveToListOfPoints.points[approachingRightSwitch], Robot.kDriveTrain.angle, DriveToListOfPoints.points[betweenRightSwitchAndScale]));
	    		}
	    	} else {
	    		addParallel(new MoveLiftToPosition(scaleLiftHeight));
	    		if (sosDir == 0) {
	    			int arr[] = {slot - 1, 3, 11, 13};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * DriveToListOfPoints.points[leftScale].sub(DriveToListOfPoints.points[approachingLeftScale]).getR()));
	    			addSequential(new DriveToPoint(DriveToListOfPoints.points[approachingLeftScale], Robot.kDriveTrain.angle, DriveToListOfPoints.points[betweenLeftSwitchAndScale]));
	    		} else {
	    			int arr[] = {slot - 1, 3, 9, 10, 12, 14};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * DriveToListOfPoints.points[rightScale].sub(DriveToListOfPoints.points[approachingRightScale]).getR()));
	    			addSequential(new DriveToPoint(DriveToListOfPoints.points[approachingRightScale], Robot.kDriveTrain.angle, DriveToListOfPoints.points[betweenRightSwitchAndScale]));
	    		}
	    	}
	    } else {
	    	if (sos == 0) {
	    		addParallel(new MoveLiftToPosition(switchLiftHeight));
	    		if (sosDir == 0) {
	    			int arr[] = {slot - 1, 4, 10, 9, 5, 7};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * DriveToListOfPoints.points[leftSwitch].sub(DriveToListOfPoints.points[approachingLeftSwitch]).getR()));
	    			addSequential(new DriveToPoint(DriveToListOfPoints.points[approachingLeftSwitch], Robot.kDriveTrain.angle, DriveToListOfPoints.points[betweenLeftSwitchAndScale]));
	    		} else {
	    			int arr[] = {slot - 1, 4, 6, 8};
	    			addSequential(new DriveToListOfPoints(start, arr, 0));
	    			addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	    			addSequential(new DriveToDistance(-1 * DriveToListOfPoints.points[rightSwitch].sub(DriveToListOfPoints.points[approachingRightSwitch]).getR()));
	    			addSequential(new DriveToPoint(DriveToListOfPoints.points[approachingRightSwitch], Robot.kDriveTrain.angle, DriveToListOfPoints.points[betweenRightSwitchAndScale]));
	    		}
	    	} else {
	   			addParallel(new MoveLiftToPosition(scaleLiftHeight));
	   			if (sosDir == 0) {
	   				int arr[] = {slot - 1, 4, 10, 9, 11, 13};
	   				addSequential(new DriveToListOfPoints(start, arr, 0));
	   				addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	   				addSequential(new DriveToDistance(-1 * DriveToListOfPoints.points[leftScale].sub(DriveToListOfPoints.points[approachingLeftScale]).getR()));
	    			addSequential(new DriveToPoint(DriveToListOfPoints.points[approachingLeftScale], Robot.kDriveTrain.angle, DriveToListOfPoints.points[betweenLeftSwitchAndScale]));
    			} else {
	   				int arr[] = {slot - 1, 4, 12, 14};
	   				addSequential(new DriveToListOfPoints(start, arr, 0));
	   				addSequential(new MoveGrabberForTime(Grabber.grabberSpeed, 2));
	   				addSequential(new DriveToDistance(-1 * DriveToListOfPoints.points[rightScale].sub(DriveToListOfPoints.points[approachingRightScale]).getR()));
	    			addSequential(new DriveToPoint(DriveToListOfPoints.points[approachingRightScale], Robot.kDriveTrain.angle, DriveToListOfPoints.points[betweenRightSwitchAndScale]));
	   			}
	   		}
    	}
    }
}
