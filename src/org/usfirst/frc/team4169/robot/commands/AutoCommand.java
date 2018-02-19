package org.usfirst.frc.team4169.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


/**
 *
 */
public class AutoCommand extends CommandGroup {
	static final int switchLiftHeight = 26;
	static final int scaleLiftHeight = 84;
	int arr[];

    public AutoCommand(int slot, int dir, int sos, double delay, int sosDir){
    	addSequential(new WaitCommand(delay));
    	
    	if (slot == 1) {
    		if (dir == 0) {
    			if (sos == 0) {
    				addParallel(new MoveLiftToPosition(switchLiftHeight));
    				if (sosDir == 0) {
    					arr[0] = 0;
    					arr[1] = 3;
    					arr[2] = 5;
    					arr[3] = 7;
    					addSequential(new DriveToListOfPoints(slot, arr, 0));
    				}
    			}
    		}
    	}
    }
}
