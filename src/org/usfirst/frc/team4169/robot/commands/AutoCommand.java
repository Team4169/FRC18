package org.usfirst.frc.team4169.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCommand extends CommandGroup {

    public AutoCommand(int slot, int orient, int switchOrScale, double delay) {
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
    	
    	int dist[] = {29, 149, 221, 101, 29, 90, -90, 26, 84, 108, 268, 28, 41};
    	int a = dist[slot + 2 * orient - 1];
    	int b = dist[orient + 5];
    	//int c = dist[switchOrScale + 7];
    	int d = dist[switchOrScale + 9];
    	int e = dist[switchOrScale + 11];
    	
    	addSequential(new WaitCommand(delay));
    	addSequential(new DriveToDistance(32));
    	addSequential(new TurnForDegrees(b));
    	addSequential(new DriveToDistance(a));
    	addSequential(new TurnForDegrees(-b));
    	addSequential(new DriveToDistance(d));
    	addSequential(new TurnForDegrees(-b));
    	//addSequential(new liftToPosition(c);
    	addSequential(new DriveToDistance(e));
    }
}
