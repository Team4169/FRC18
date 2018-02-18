package org.usfirst.frc.team4169.robot.commands;


import org.usfirst.frc.team4169.robot.Vec2d;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class AutoCommand extends CommandGroup {

    public AutoCommand(int slot, int dir, int sos, double delay, int sosDir){
    	int length = 15;
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
   
    	Vec2d startingPositions[] = {Vec2d.makeCart(65.69, 17.25), Vec2d.makeCart(185.69, 17.25), Vec2d.makeCart(257.69, 17.25)};
    	//			  	x:0      1       2       3      4       5      6       7
    	double xDist[] = {65.69, 185.69, 257.69, 17.69, 305.69, 16.69, 305.69, 68,
    			//        8       9      10      11     12      13     14
    	                  255.38, 17.69, 305.69, 17.69, 305.69, 54.32, 269.06};
    	//              y:0   1   2   3   4   5    6    7    8   
    	double yDist[] = {32, 32, 32, 32, 32, 168, 168, 168, 168, 
    			//        9    10   11   12   13   14
    					  232, 232, 324, 324, 324, 324};
    	
    	Vec2d dist[] = new Vec2d[length];
    	for(int i = 0; i < length; i++){
    		dist[i] = Vec2d.makeCart(xDist[i], yDist[i]);
    	}
    	
    	Vec2d currentPosition = startingPositions[slot - 1];
    	
    	addSequential(new DriveToPoint(currentPosition, dist[slot - 1]));
    	currentPosition.add(dist[slot - 1].sub(currentPosition));
    	
    	addSequential(new DriveToPoint(currentPosition, dist[dir + 3]));
    	currentPosition.add(dist[dir + 3].sub(currentPosition));
    	
    	addSequential(new DriveToPoint(currentPosition, dist[(9 + dir) * Math.abs(sosDir - dir)
    	                              + (dir + 3) * (1 - Math.abs(sosDir - dir))]));
    	currentPosition.add(dist[(9 + dir) * Math.abs(sosDir - dir)
    	                              + (dir + 3) * (1 - Math.abs(sosDir - dir))].sub(currentPosition));
    	
    	addSequential(new DriveToPoint(currentPosition, dist[(10 - dir) * Math.abs(sosDir - dir) 
    	                                           + (dir + 3) * (1 - Math.abs(sosDir - dir))]));
    	currentPosition.add(dist[(10 - dir) * Math.abs(sosDir - dir) 
    	                + (dir + 3) * (1 - Math.abs(sosDir - dir))].sub(currentPosition));
    	
    	addSequential(new DriveToPoint(currentPosition, dist[5 + sosDir + 6 * sos]));
    	currentPosition.add(dist[5 + sosDir + 6 * sos].sub(currentPosition));
    	
    	addSequential(new DriveToPoint(currentPosition, dist[7 + sosDir + 6 * sos]));
    	currentPosition.add(dist[7 + sosDir + 6 * sos].sub(currentPosition));
    	
    	
    	addSequential(new MoveLiftToPosition(26 * (1 - sos) + 84 * sos));
    	addSequential(new MoveGrabber(1));
    	addSequential(new MoveLiftToPosition(-(26 * (1 - sos) + 84 * sos)));
    	
    	
    	addSequential(new DriveToPoint(currentPosition, dist[5 + sosDir + 6 * sos]));
    	currentPosition.add(dist[5 + sosDir + 6 * sos].sub(currentPosition));
    	
    	addSequential(new DriveToPoint(currentPosition, dist[9 + sosDir]));
    	currentPosition.add(dist[9 + sosDir].sub(currentPosition));
    	
    	
    	
    
    	
    	
    	//addSequential(new liftToPosition(c);
    }
}
