package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {

    private final SparkMax armMotor = Constants.MotorConstants.armMotor;
    private final double speed = 0.5;

    public void operateArm(boolean buttonExtend, boolean buttonRetract) {
        if (buttonExtend) {
            armMotor.set(speed);
        } else if (buttonRetract) {
            armMotor.set(-speed);
        } else {
            armMotor.set(0);
        }
    }
}