package org.usfirst.frc.team4169.robot.subsystems;

import org.usfirst.frc.team4169.robot.RobotMap;
import org.usfirst.frc.team4169.robot.commands.MoveLift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem {
	static final double liftSpeed = SmartDashboard.getNumber("Lift Speed", 0.5);
	static final int kPIDLoopIdx = 0;
	static final int closedLoopErrorConstant = 15;
	static final int velocityConstant = 5;
	static final int kTimeoutMs = 10;
	static final double pulsesPerInch = 17280 / Math.PI;
	static final double liftkF = 0,
			liftkP = 0,
			liftkI = 0,
		    liftkD = 0;
	static final int kSlotIdx = 0;
	public boolean atTop = false;
	public double slowMode = 1;
	
	static WPI_TalonSRX liftMotor = new WPI_TalonSRX(RobotMap.liftMotor);
	public static DigitalInput limitSwitch = new DigitalInput(RobotMap.liftLimitSwitch);
	
	public static enum Direction {
		eDown, eStop, eUp
	}
	
	public Lift() {
		double kF = SmartDashboard.getNumber("liftkF", 0);
		double kP = SmartDashboard.getNumber("liftkP", 0);
		double kI = SmartDashboard.getNumber("liftkI", 0);
		double kD = SmartDashboard.getNumber("liftkD", 0);
		
		liftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kPIDLoopIdx, kTimeoutMs);
		liftMotor.setSensorPhase(false);
		/* Set relevant frame periods to be at least as fast as periodic rate */
		liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
		

		/* set the peak and nominal outputs */
		liftMotor.configNominalOutputForward(0, kTimeoutMs);
		liftMotor.configNominalOutputReverse(0, kTimeoutMs);
		liftMotor.configPeakOutputForward(1, kTimeoutMs);
		liftMotor.configPeakOutputReverse(-1, kTimeoutMs);
		
	
		/* set closed loop gains in slot0 - see documentation */
		liftMotor.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		liftMotor.config_kF(0, kF, kTimeoutMs);
		liftMotor.config_kP(0, kP, kTimeoutMs);
		liftMotor.config_kI(0, kI, kTimeoutMs);
		liftMotor.config_kD(0, kD, kTimeoutMs);
		liftMotor.config_IntegralZone(0, 50, kTimeoutMs);
		
	
		/* set acceleration and vcruise velocity - see documentation */
		liftMotor.configMotionCruiseVelocity(750, kTimeoutMs);
		liftMotor.configMotionAcceleration(750, kTimeoutMs);		/* zero the sensor */
		liftMotor.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new MoveLift());
    }
    
    public void moveLift(Direction dir) {
    	if (dir.ordinal() == 2) {
        	liftMotor.set(liftSpeed);
        } else if (dir.ordinal() == 0) {
        	liftMotor.set(-liftSpeed);
        }
    	
    	if(limitSwitch.get() == true) {
    		atTop = true;
    	} else {
    		atTop = false;
    	}
    }
    
    public double getLiftPosition() {
    	return (double)(liftMotor.getSelectedSensorPosition(0)) / pulsesPerInch;
    }
    
    public void moveLiftToPosition(double inches){
    	liftMotor.set(ControlMode.MotionMagic, inches * pulsesPerInch);
    }
}

