package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RollerSubsystem extends SubsystemBase {

    private final SparkMax rollerMotor = Constants.MotorConstants.rollerMotor;
    private double speed = 0.5;
    private double speed2 = 0.7;


    public void operateRoller(boolean L1, boolean Processor) {
        if (L1) {
            rollerMotor.set(speed);
        } else if (Processor) {
            rollerMotor.set(-speed2);
        } else {
            rollerMotor.set(0);
        }
    }
}