package org.usfirst.frc.team4169.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import org.usfirst.frc.team4169.robot.commands.DriveWithController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team4169.robot.Robot;
//import org.usfirst.frc.team4169.robot.Robot;
import org.usfirst.frc.team4169.robot.RobotMap;

/**
 *
 */

public class DriveTrain extends Subsystem {
	StringBuilder sb = new StringBuilder();
	HashMap<String, WPI_TalonSRX> dict = new HashMap<>(); 
	public static final double JOY_DEAD_ZONE = 0.2d;
	public double angle = Math.PI / 2;
	double radians;
	static final double leftkF = 1.02,
			leftkP = 1.2,
			leftkI = 0.004,
			leftkD = 8.0,
			rightkF = 1.02,
			rightkP = 1.2,
			rightkI = 0.004,
			rightkD = 8.0;
	static final double secondsForAcceleration = 0.0;
	static final int closedLoopErrorConstant = 20;
	static final int velocityConstant = 5;
	static final double inchesPerCompleteTurn = 28;
	static final int kSlotIdx = 0;
	static final int kPIDLoopIdx = 0;
	static final int kTimeoutMs = 10;
	static final int pulses = (int)SmartDashboard.getNumber("Pulses per revolution", 1440);
	static int _loops = 0;
	static int _timesInMotionMagic = 0;
	
	static WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.leftFrontMotor);
	static WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(RobotMap.leftBackMotor);
	static WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(RobotMap.rightFrontMotor);
	static WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(RobotMap.rightBackMotor);
	
	static SpeedControllerGroup left = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
	static SpeedControllerGroup right = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);
	
	static DifferentialDrive drive = new DifferentialDrive(left, right);
	
	double slowMode;
	
	public DriveTrain() {
		slowMode = 1;
		
		dict.put("leftFront", leftFrontMotor);
		dict.put("leftBack", leftBackMotor);
		dict.put("rightFront", rightFrontMotor);
		dict.put("rightBack", rightBackMotor);
		
    	rightFrontMotor.setInverted(true);
    	rightBackMotor.setInverted(true);
		right.setInverted(true);
		
		/* first choose the sensor */
		leftFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kPIDLoopIdx, kTimeoutMs);
		leftFrontMotor.setSensorPhase(true);
		
		leftFrontMotor.setNeutralMode(NeutralMode.Brake);
		leftBackMotor.setNeutralMode(NeutralMode.Brake);
		rightFrontMotor.setNeutralMode(NeutralMode.Brake);
		rightBackMotor.setNeutralMode(NeutralMode.Brake);
		
		rightFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kPIDLoopIdx, kTimeoutMs);
		rightFrontMotor.setSensorPhase(true);
		
		/* Set relevant frame periods to be at least as fast as periodic rate */
		leftFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		leftFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
		
		rightFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		rightFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
		
		/* set the peak and nominal outputs */
		leftFrontMotor.configNominalOutputForward(0, kTimeoutMs);
		leftFrontMotor.configNominalOutputReverse(0, kTimeoutMs);
		leftFrontMotor.configPeakOutputForward(1, kTimeoutMs);
		leftFrontMotor.configPeakOutputReverse(-1, kTimeoutMs);
		leftBackMotor.configPeakOutputForward(1, kTimeoutMs);
		leftBackMotor.configPeakOutputReverse(-1, kTimeoutMs);
		
		
		rightFrontMotor.configNominalOutputForward(0, kTimeoutMs);
		rightFrontMotor.configNominalOutputReverse(0, kTimeoutMs);
		rightFrontMotor.configPeakOutputForward(1, kTimeoutMs);
		rightFrontMotor.configPeakOutputReverse(-1, kTimeoutMs);
		rightBackMotor.configPeakOutputForward(1, kTimeoutMs);
		rightBackMotor.configPeakOutputReverse(-1, kTimeoutMs); 

		/* set closed loop gains in slot0 - see documentation */
		leftFrontMotor.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		leftFrontMotor.config_kF(0, leftkF, kTimeoutMs);
		leftFrontMotor.config_kP(0, leftkP, kTimeoutMs);
		leftFrontMotor.config_kI(0, leftkI, kTimeoutMs);
		leftFrontMotor.config_kD(0, leftkD, kTimeoutMs);
		leftFrontMotor.config_IntegralZone(0, 50, kTimeoutMs);
		
		rightFrontMotor.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		rightFrontMotor.config_kF(0, rightkF, kTimeoutMs);
		rightFrontMotor.config_kP(0, rightkP, kTimeoutMs);
		rightFrontMotor.config_kI(0, rightkI, kTimeoutMs);
		rightFrontMotor.config_kD(0, rightkD, kTimeoutMs);
		rightFrontMotor.config_IntegralZone(0, 50, kTimeoutMs);
		
		/* set acceleration and vcruise velocity - see documentation */
		leftFrontMotor.configMotionCruiseVelocity(100, kTimeoutMs);
		leftFrontMotor.configMotionAcceleration(50, kTimeoutMs);
		
		rightFrontMotor.configMotionCruiseVelocity(100, kTimeoutMs);
		rightFrontMotor.configMotionAcceleration(50, kTimeoutMs);
		/* zero the sensor */
		leftFrontMotor.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
		
		rightFrontMotor.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
		
		rightFrontMotor.enableCurrentLimit(false);
		rightBackMotor.enableCurrentLimit(false);
		leftFrontMotor.enableCurrentLimit(false);
		leftBackMotor.enableCurrentLimit(false);
		
		rightFrontMotor.configOpenloopRamp(secondsForAcceleration, kTimeoutMs);
		leftFrontMotor.configOpenloopRamp(secondsForAcceleration, kTimeoutMs);
		rightBackMotor.configOpenloopRamp(secondsForAcceleration, kTimeoutMs);
		leftBackMotor.configOpenloopRamp(secondsForAcceleration, kTimeoutMs);
		
		rightFrontMotor.configClosedloopRamp(secondsForAcceleration, kTimeoutMs);
		leftFrontMotor.configClosedloopRamp(secondsForAcceleration, kTimeoutMs);
		rightBackMotor.configClosedloopRamp(secondsForAcceleration, kTimeoutMs);
		leftBackMotor.configClosedloopRamp(secondsForAcceleration, kTimeoutMs);
	}
	
    // Put methods f	or controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new DriveWithController());
    }
    
    //drives the robot using controller values
    public void drive() {
//    		double leftY = -Robot.m_oi.getInstance().controller1.getY(GenericHID.Hand.kLeft);
//    		double rightY = -Robot.m_oi.getInstance().controller1.getY(GenericHID.Hand.kRight);
//    		
//    		if (Math.abs(leftY) < 0.2) {
//    			leftY = 0;
//    		}
//    		
//    		if (Math.abs(rightY) < 0.2) {
//    			rightY = 0;
//    		}
//    		
//    		drive.tankDrive(leftY * 0.8 * slowMode, rightY * 0.8 * slowMode);
    	
    	
    	
    	
    	
		double speed = joystickToMotorPower(Robot.m_oi.getController(1).getY(GenericHID.Hand.kLeft));
		double rotation = Robot.m_oi.getController(1).getTriggerAxis(GenericHID.Hand.kRight) -
				Robot.m_oi.getController(1).getTriggerAxis(GenericHID.Hand.kLeft);
		
		rotation = joystickToMotorPower(rotation);

		drive.arcadeDrive(speed * slowMode, rotation * slowMode);
		
		
    	
//    		StringBuilder sb = new StringBuilder();
//        	sb.append("\tleftOut:");
//        	sb.append(leftFrontMotor.getMotorOutputVoltage());
//        	sb.append("\trightOut:");
//        	sb.append(rightFrontMotor.getMotorOutputVoltage());
//        	sb.append("\tleftVel:");
//        	sb.append(leftFrontMotor.getSelectedSensorVelocity(kSlotIdx));
//        	sb.append("\trightVel:");
//        	sb.append(rightFrontMotor.getSelectedSensorVelocity(kSlotIdx));
//        	sb.append("\tleftCount");
//        	sb.append(leftFrontMotor.getSelectedSensorPosition(kSlotIdx));
//        	sb.append("\trightCount");
//        	sb.append(rightFrontMotor.getSelectedSensorPosition(kSlotIdx));
//        	System.out.println(sb.toString());
        	
        	
    }
    
    //stops the robot
    public void stop() {
    	drive.tankDrive(0, 0);
    	System.out.println("Stop command stopped");
    }
    
    private double joystickToMotorPower(double joy) {
    	final double slope = -1.0d/(JOY_DEAD_ZONE - 1.0d);
    	final double intercept = 1.0d + (1.0d/JOY_DEAD_ZONE - 1.0d);
    	
    	double motorPower = 0.0;
    	
    	joy = -joy;
    	
    	if (Math.abs(joy) < JOY_DEAD_ZONE) {
    		motorPower = 0.0;
    	} else if (joy > 0) {
    		motorPower = slope * joy + intercept;
    	} else if (joy < 0) {
    		motorPower = slope * joy - intercept;
    	}
    	
    	return motorPower;
    }
    
    public void pidTest() {
    	/* get gamepad axis - forward stick is positive */
		double leftYstick =	-Robot.m_oi.getController(1).getY(GenericHID.Hand.kLeft);
		double rightYstick = -Robot.m_oi.getController(1).getY(GenericHID.Hand.kRight);
		sb.append("\trightStick:");
		sb.append(rightYstick);
		sb.append("\tleftStick:");
		sb.append(leftYstick);
		/* calculate the percent motor output */
		double lmotorOutput = leftFrontMotor.getMotorOutputPercent();
		double rmotorOutput = rightFrontMotor.getMotorOutputPercent();
		/* prepare line to print */
		sb.append("\tleftOut%:");
		sb.append(lmotorOutput);
		sb.append("\tleftVel:");
		sb.append(leftFrontMotor.getSelectedSensorVelocity(kPIDLoopIdx));
		sb.append("\trightOut%:");
		sb.append(rmotorOutput);
		sb.append("\trightVel:");
		sb.append(rightFrontMotor.getSelectedSensorVelocity(kPIDLoopIdx));

		if (Robot.m_oi.getController(1).getBumper(GenericHID.Hand.kLeft)) {
			/* Motion Magic - 4096 ticks/rev * 10 Rotations in either direction */
			// Motion Magic for 10 ft
			double targetPos = pulses * 10.0 * 12.0 / 6 / Math.PI;
			
			if (!(leftFrontMotor.getControlMode() == ControlMode.MotionMagic)) {
				leftFrontMotor.setSelectedSensorPosition(kSlotIdx, kPIDLoopIdx, kTimeoutMs);
				leftFrontMotor.set(ControlMode.MotionMagic, targetPos);
				leftBackMotor.follow(leftFrontMotor);
			}
			
			/* append more signals to print when in speed mode. */
			sb.append("\terr:");
			sb.append(leftFrontMotor.getClosedLoopError(kPIDLoopIdx));
			sb.append("\ttrg:");
			sb.append(targetPos);
		} else if (Robot.m_oi.getController(1).getBumper(GenericHID.Hand.kRight)) {
			/* Motion Magic - 4096 ticks/rev * 10 Rotations in either direction */
			// Motion Magic for 10 ft
			double targetPos = pulses * 10.0 * 12.0 / 6 / Math.PI;
			
			if (!(rightFrontMotor.getControlMode() == ControlMode.MotionMagic)) {
				rightFrontMotor.setSelectedSensorPosition(kSlotIdx, kPIDLoopIdx, kTimeoutMs);
				rightFrontMotor.set(ControlMode.MotionMagic, targetPos);
				rightBackMotor.follow(rightFrontMotor);
			}
			
			/* append more signals to print when in speed mode. */
			sb.append("\terr:");
			sb.append(rightFrontMotor.getClosedLoopError(kPIDLoopIdx));
			sb.append("\ttrg:");
			sb.append(targetPos);
    	} else {
			/* Percent voltage mode */
			leftFrontMotor.set(ControlMode.PercentOutput, leftYstick);
			leftBackMotor.follow(leftFrontMotor);
			rightFrontMotor.set(ControlMode.PercentOutput, rightYstick);
			rightBackMotor.follow(rightFrontMotor);
		}
		
		/* instrumentation */
		process(rightFrontMotor, sb);
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
    
    public void driveForDistance(double distance) {
    	rightFrontMotor.setSelectedSensorPosition(kSlotIdx, kPIDLoopIdx, kTimeoutMs);
    	leftFrontMotor.setSelectedSensorPosition(kSlotIdx, kPIDLoopIdx, kTimeoutMs);
    	leftFrontMotor.set(ControlMode.MotionMagic, distance);
    	leftBackMotor.follow(leftFrontMotor);
    	rightFrontMotor.set(ControlMode.MotionMagic, distance);
    	rightBackMotor.follow(rightFrontMotor);
    }

    public void turnForRadians(double radians) {
    	rightFrontMotor.setSelectedSensorPosition(kSlotIdx, kPIDLoopIdx, kTimeoutMs);
    	leftFrontMotor.setSelectedSensorPosition(kSlotIdx, kPIDLoopIdx, kTimeoutMs);
    	double distance = inchesPerCompleteTurn * pulses * radians / 2 / Math.PI;
    	rightFrontMotor.set(ControlMode.MotionMagic, distance);
    	rightBackMotor.follow(rightFrontMotor);
    	leftFrontMotor.set(ControlMode.MotionMagic, -distance);
    	leftBackMotor.follow(leftFrontMotor);
    }
    
    public boolean checkClosedLoopError(WPI_TalonSRX tal) {
    	return Math.abs(tal.getSelectedSensorPosition(kSlotIdx) - tal.getClosedLoopTarget(kPIDLoopIdx)) < closedLoopErrorConstant &&
    			Math.abs(tal.getSelectedSensorVelocity(kPIDLoopIdx)) < velocityConstant;
    }
    
    public void driveSafetyEnabled(boolean foo) {
    	drive.setSafetyEnabled(foo);
    }
    
    public WPI_TalonSRX getMotor(String key) {
    	return dict.get(key);
    }
    
    public void speedTest() {
    	drive.tankDrive(1, 1*.93);
    	StringBuilder sb = new StringBuilder();
    	sb.append("\tleftOut:");
    	sb.append(leftFrontMotor.getMotorOutputPercent());
    	sb.append("\trightOut:");
    	sb.append(rightFrontMotor.getMotorOutputPercent());
    	sb.append("\tleftVel:");
    	sb.append(leftFrontMotor.getSelectedSensorVelocity(kSlotIdx));
    	sb.append("\trightVel:");
    	sb.append(rightFrontMotor.getSelectedSensorVelocity(kSlotIdx));
    	sb.append("\tleftCount");
    	sb.append(leftFrontMotor.getSelectedSensorPosition(kSlotIdx));
    	sb.append("\trightCount");
    	sb.append(rightFrontMotor.getSelectedSensorPosition(kSlotIdx));
    	System.out.println(sb.toString());
    }
    
    public void setSlowMode(double value) {
    	if (value <= 1.0 && value >= 0.0) {
    		slowMode = value;
    	}
    }
    
    public double getPulsesPerInch() {
    	return pulses;
    }
} 

