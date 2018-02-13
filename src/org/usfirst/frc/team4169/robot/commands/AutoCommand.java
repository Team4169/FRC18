package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCommand extends CommandGroup {

    public AutoCommand(int slot, int orient, int switchOrScale, double delay, int dirSOS) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	int dirMatch = Math.abs(switchOrScale - dirSOS);
    	//			  0  1   2    3   4   5   6  7  8   9  10  11  12  13   14 15  16 17
    	int dist[] = {29,149,221,101,29, 90,-90, 26,84, 17,30, 108,268,188, 70,80};
    	int a = dist[slot + 2 * orient - 1]; //Distance to a side
    	int b = dist[orient + 5]; //First turn
    	int c = dist[switchOrScale + 7]; //Height of lift
    	int d = dist[switchOrScale + 9]; //Distance from side to target (Switch or Scale)
    	int e = dist[switchOrScale * (1 - dirMatch) + 2 * dirMatch + 11]; //Second drive distance
    	int f = dist[switchOrScale + 14]; //Distance from crossing point to target
    	
    	addSequential(new WaitCommand(delay));
    	addSequential(new DriveToDistance(32));
    	addSequential(new TurnForDegrees(b));
    	addSequential(new DriveToDistance(a));
    	addSequential(new TurnForDegrees(-b));
    	addSequential(new DriveToDistance(e));
    	
    	addSequential(new TurnForDegrees(-b * dirMatch));
    	addSequential(new DriveToDistance(254 * dirMatch));
    	addSequential(new TurnForDegrees((2 * switchOrScale - 1) * b * dirMatch));
    	addSequential(new DriveToDistance(f * dirMatch));
    	
    	addSequential(new TurnForDegrees(-b));
    	addSequential(new DriveToDistance(d));
    	addSequential(new MoveLiftToPosition(c));
    	Robot.kGrabber.moveGrabber(-1);
    	addSequential(new MoveLiftToPosition(-c));
    	addSequential(new DriveToDistance(-(d + 16)));
    	
    	
    	
    	//addSequential(new liftToPosition(c);
    }
}
