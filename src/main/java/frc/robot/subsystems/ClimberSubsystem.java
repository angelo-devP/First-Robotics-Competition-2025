package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

    private final SparkMax climberMotor = Constants.MotorConstants.climberMotor;
    
    private static final double POSITION_UP = 0;
    private static final double POSITION_DOWN = -400;
    private static final double POSITION_TOLERANCE  = 0.1;
    
    private static final double kP = 0.021; 
    private static final double kI = 0.0;   
    private static final double kD = 0.5;   

    private static final double POSITION_CONVERSION_FACTOR = 1.0; 
    private static final double VELOCITY_CONVERSION_FACTOR = 1.0; 

    private final SparkClosedLoopController pidController;
    private final RelativeEncoder climberEncoder;

    private double targetPosition = POSITION_UP;

    private boolean isMoving = false;
    private boolean isEnabled = false;

    public ClimberSubsystem() {
        pidController = climberMotor.getClosedLoopController();
        climberEncoder = climberMotor.getEncoder();

        SparkMaxConfig config = new SparkMaxConfig();
        
        config
              .inverted(false)
              .idleMode(IdleMode.kBrake)
              .openLoopRampRate(1.0)
              .closedLoopRampRate(1.0);

        config.encoder
              .positionConversionFactor(POSITION_CONVERSION_FACTOR)
              .velocityConversionFactor(VELOCITY_CONVERSION_FACTOR);

        config.closedLoop
              .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
              .pid(kP, kI, kD); 

        climberMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void operateClimber(boolean climberUp, boolean climberDown) {
        if (!isEnabled) {
            isEnabled = true;
        }

        if (climberUp) {
            moveToPosition(POSITION_UP);
        } else if (climberDown) {
            moveToPosition(POSITION_DOWN);
        }
    }

    private void moveToPosition(double newPosition) {
        if (!isEnabled) return;

        double currentPosition = climberEncoder.getPosition();

        if (Math.abs(currentPosition - newPosition) > POSITION_TOLERANCE) {
            targetPosition = newPosition;
            isMoving = true;
            pidController.setReference(targetPosition, ControlType.kPosition);
        }
    }

    @Override
    public void periodic() {
        display();

        double currentPosition = climberEncoder.getPosition();

        if (isMoving && Math.abs(currentPosition - targetPosition) <= POSITION_TOLERANCE) {
            isMoving = false;
            climberMotor.set(0);
        }
    }

    public void display() {
        SmartDashboard.putNumber("Climber - Posicao Atual", getClimberPosition());
        SmartDashboard.putNumber("Climber - Posicao Alvo", getClimberTargetPosition());
    }
    
    public void stopClimber() {
        isMoving = false;
        isEnabled = false;
        targetPosition = climberEncoder.getPosition();
        pidController.setReference(targetPosition, ControlType.kPosition);
        climberMotor.set(0);
    }

    public double getClimberPosition() {
        return climberEncoder.getPosition();
    }

    public double getClimberTargetPosition() {
        return targetPosition;
    }
}