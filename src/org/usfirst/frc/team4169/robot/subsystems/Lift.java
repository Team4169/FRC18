package org.usfirst.frc.team4169.robot.subsystems;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.RobotMap;
import org.usfirst.frc.team4169.robot.commands.MoveLift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem {
	static final double testInches = 40.0;
	static final double liftSpeed = SmartDashboard.getNumber("Lift Speed", 0.5);
	static final int kPIDLoopIdx = 0;
	static final int closedLoopErrorConstant = 15;
	static final int velocityConstant = 5;
	static final int kTimeoutMs = 10;
	static final double pulsesPerInch = 1024 * 3 * Math.PI;
	static final double liftkF = 0,
			liftkP = 0,
			liftkI = 0,
		    liftkD = 0;
	static final int kSlotIdx = 0;
	double slowMode;
	StringBuilder sb = new StringBuilder();
	static int _timesInMotionMagic = 0;
	static int _loops = 0;
	
	static WPI_TalonSRX liftMotor = new WPI_TalonSRX(RobotMap.liftMotor);
	
	public Lift() {
		slowMode = 1.0;
		
		double kF = SmartDashboard.getNumber("liftkF", 0);
		double kP = SmartDashboard.getNumber("liftkP", 0);
		double kI = SmartDashboard.getNumber("liftkI", 0);
		double kD = SmartDashboard.getNumber("liftkD", 0);
		
		liftMotor.setInverted(true);
		liftMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, kTimeoutMs);
		
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
    
    public void moveLift(double speed) {
        liftMotor.set(speed * slowMode);
    }
    
    public double getLiftPosition() {
    	return (double)(liftMotor.getSelectedSensorPosition(0)) / pulsesPerInch;
    }
    
    public void moveLiftToPosition(double inches){
    	liftMotor.set(ControlMode.MotionMagic, inches * pulsesPerInch);
    }
    
    public void pidTest() {
    	/* get gamepad axis - forward stick is positive */
    	double targetPos = pulsesPerInch * testInches;
    	
		double spd = Robot.m_oi.getController(2).getY(GenericHID.Hand.kLeft);
		sb.append("\tsetOut:");
		sb.append(spd);
		/* calculate the percent motor output */
		double motorOutput = liftMotor.getMotorOutputPercent();
		/* prepare line to print */
		sb.append("\tOut%:");
		sb.append(motorOutput);
		sb.append("\tVel:");
		sb.append(liftMotor.getSelectedSensorVelocity(kPIDLoopIdx));

		if (Robot.m_oi.getController(2).getBumper(GenericHID.Hand.kRight)) {
			
			if (!(liftMotor.getControlMode() == ControlMode.MotionMagic)) {
				liftMotor.setSelectedSensorPosition(kSlotIdx, kPIDLoopIdx, kTimeoutMs);
				liftMotor.set(ControlMode.MotionMagic, targetPos);
			}
			
			/* append more signals to print when in speed mode. */
			sb.append("\terr:");
			sb.append(liftMotor.getClosedLoopError(kPIDLoopIdx));
			sb.append("\ttrg:");
			sb.append(targetPos);
    	} else {
			/* Percent voltage mode */
			liftMotor.set(ControlMode.PercentOutput, spd);
		}
		
		/* instrumentation */
		process(liftMotor, sb);
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (Exception e) {
		}
    }

    public void process(WPI_TalonSRX tal, StringBuilder sb) {
    	/* smart dash plots */
		SmartDashboard.putNumber("liftSensorVel", tal.getSelectedSensorVelocity(kPIDLoopIdx));
		SmartDashboard.putNumber("liftSensorPos", tal.getSelectedSensorPosition(kPIDLoopIdx));
		SmartDashboard.putNumber("liftMotorOutputPercent", tal.getMotorOutputPercent());
		SmartDashboard.putNumber("liftClosedLoopError", tal.getClosedLoopError(kPIDLoopIdx));
		SmartDashboard.putNumber("liftClosedLoopTarget", tal.getClosedLoopTarget(kPIDLoopIdx));
		
		/* check if we are motion-magic-ing */
		if (tal.getControlMode() == ControlMode.MotionMagic) {
			++_timesInMotionMagic;
		} else {
			_timesInMotionMagic = 0;
		}
		if (_timesInMotionMagic > 10) {
			/* print the Active Trajectory Point Motion Magic is servoing towards */
    		SmartDashboard.putNumber("liftActTrajVelocity", tal.getActiveTrajectoryVelocity());
    		SmartDashboard.putNumber("liftActTrajPosition", tal.getActiveTrajectoryPosition());
    		SmartDashboard.putNumber("liftActTrajHeading", tal.getActiveTrajectoryHeading());
		}
		/* periodically print to console */
		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(sb.toString());
		}
		/* clear line cache */
		sb.setLength(0);
    }
    
    public void setSlowMode(double value) {
    	if (value <= 1.0 && value >= 0.0) {
    		slowMode = value;
    	}
    }
}

