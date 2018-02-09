package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.RobotMap;
import org.usfirst.frc.team4169.robot.commands.MoveLift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem {
	static final double liftSpeed = SmartDashboard.getNumber("Lift Speed", 0.5);
	
	static WPI_TalonSRX liftMotor = new WPI_TalonSRX(RobotMap.liftMotor);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new MoveLift());
    }
    
    public void moveLift(int speed) {
    	if (speed == 1) {
    		liftMotor.set(liftSpeed);
    	} else if (speed == -1) {
    		liftMotor.set(liftSpeed);
    	} else {
    		liftMotor.set(0);
    	}
    }
    public void moveLiftToPosition(double inches){
    	liftMotor.set(ControlMode.MotionMagic, 8640*inches/Math.PI);
    }
}

