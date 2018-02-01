package org.usfirst.frc.team4169.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4169.robot.commands.DriveWithController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team4169.robot.OI;
import org.usfirst.frc.team4169.robot.RobotMap;

/**
 *
 */

public class DriveTrain extends Subsystem {
	StringBuilder sb = new StringBuilder();
	static final int kSlotIdx = 0;
	static final int kPIDLoopIdx = 0;
	static final int kTimeoutMs = 10;
	static int _loops = 0;
	static int _timesInMotionMagic = 0;
	
	static WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.leftFrontMotor);
	static WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(RobotMap.leftBackMotor);
	static WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(RobotMap.rightFrontMotor);
	static WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(RobotMap.rightBackMotor);
	
	static SpeedControllerGroup left = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
	static SpeedControllerGroup right = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);
	
	static DifferentialDrive drive = new DifferentialDrive(left, right);
	
	public DriveTrain() {
		left.setInverted(true);
		right.setInverted(true);
		
		/* first choose the sensor */
		leftFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		leftFrontMotor.setSensorPhase(true);
		leftFrontMotor.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		leftFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		leftFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

		/* set the peak and nominal outputs */
		leftFrontMotor.configNominalOutputForward(0, kTimeoutMs);
		leftFrontMotor.configNominalOutputReverse(0, kTimeoutMs);
		leftFrontMotor.configPeakOutputForward(1, kTimeoutMs);
		leftFrontMotor.configPeakOutputReverse(-1, kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		leftFrontMotor.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		leftFrontMotor.config_kF(0, SmartDashboard.getNumber("Left kF", 0.2), kTimeoutMs);
		leftFrontMotor.config_kP(0, SmartDashboard.getNumber("Left kP", 0.2), kTimeoutMs);
		leftFrontMotor.config_kI(0, SmartDashboard.getNumber("Left kI", 0), kTimeoutMs);
		leftFrontMotor.config_kD(0, SmartDashboard.getNumber("Left kD", 0), kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		leftFrontMotor.configMotionCruiseVelocity(15000, kTimeoutMs);
		leftFrontMotor.configMotionAcceleration(6000, kTimeoutMs);
		/* zero the sensor */
		leftFrontMotor.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithController());
    }
    
    //drives the robot using controller values
    public void drive() {
    		double leftY = OI.getInstance().controller.getY(GenericHID.Hand.kLeft);
    		double rightY = OI.getInstance().controller.getY(GenericHID.Hand.kRight);
    		
    		if (Math.abs(leftY) < 0.2) {
    			leftY = 0;
    		}
    		
    		if (Math.abs(rightY) < 0.2) {
    			rightY = 0;
    		}
    		
    		if (OI.getInstance().controller.getBumper(GenericHID.Hand.kRight)) {
    			leftY = leftY / 4;
    			rightY = rightY / 4;
    		}
    		
    		drive.tankDrive(leftY, rightY);
    }
    
    //stops the robot
    public void stop() {
    	drive.tankDrive(0, 0);
    }
    
    public void pidTest() {
    	/* get gamepad axis - forward stick is positive */
		double leftYstick =	OI.getInstance().controller.getY(GenericHID.Hand.kLeft);
		/* calculate the percent motor output */
		double motorOutput = leftFrontMotor.getMotorOutputPercent();
		/* prepare line to print */
		sb.append("\tOut%:");
		sb.append(motorOutput);
		sb.append("\tVel:");
		sb.append(leftFrontMotor.getSelectedSensorVelocity(kPIDLoopIdx));

		if (OI.getInstance().controller.getXButton()) {
			/* Motion Magic - 4096 ticks/rev * 10 Rotations in either direction */
			// Motion Magic for 10 ft
			double targetPos = 1440 * 10.0 * 12.0 / 6 / Math.PI;
			leftFrontMotor.set(ControlMode.MotionMagic, targetPos);

			/* append more signals to print when in speed mode. */
			sb.append("\terr:");
			sb.append(leftFrontMotor.getClosedLoopError(kPIDLoopIdx));
			sb.append("\ttrg:");
			sb.append(targetPos);
		} else {
			/* Percent voltage mode */
			leftFrontMotor.set(ControlMode.PercentOutput, leftYstick);
		}
		
		/* instrumentation */
		process(leftFrontMotor, sb);
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (Exception e) {
		}
    }

    public void process(WPI_TalonSRX tal, StringBuilder sb) {
    	/* smart dash plots */
		SmartDashboard.putNumber("SensorVel", tal.getSelectedSensorVelocity(kPIDLoopIdx));
		SmartDashboard.putNumber("SensorPos", tal.getSelectedSensorPosition(kPIDLoopIdx));
		SmartDashboard.putNumber("MotorOutputPercent", tal.getMotorOutputPercent());
		SmartDashboard.putNumber("ClosedLoopError", tal.getClosedLoopError(kPIDLoopIdx));
		SmartDashboard.putNumber("ClosedLoopTarget", tal.getClosedLoopTarget(kPIDLoopIdx));
		
		/* check if we are motion-magic-ing */
		if (tal.getControlMode() == ControlMode.MotionMagic) {
			++_timesInMotionMagic;
		} else {
			_timesInMotionMagic = 0;
		}
		if (_timesInMotionMagic > 10) {
			/* print the Active Trajectory Point Motion Magic is servoing towards */
    		SmartDashboard.putNumber("ActTrajVelocity", tal.getActiveTrajectoryVelocity());
    		SmartDashboard.putNumber("ActTrajPosition", tal.getActiveTrajectoryPosition());
    		SmartDashboard.putNumber("ActTrajHeading", tal.getActiveTrajectoryHeading());
		}
		/* periodically print to console */
		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(sb.toString());
		}
		/* clear line cache */
		sb.setLength(0);
    }
} 