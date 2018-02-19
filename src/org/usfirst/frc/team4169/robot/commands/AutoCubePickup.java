package org.usfirst.frc.team4169.robot.commands;

import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCubePickup extends CommandGroup {

    public AutoCubePickup() {
    	Robot.limelight.findCube();
        addSequential(new TurnForDegrees(Robot.limelight.getTx()));
        addSequential(new DriveToDistance(Robot.limelight.getDist()));
        addSequential(new MoveGrabber(Grabber.Speed.eIn));
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
    }
}
