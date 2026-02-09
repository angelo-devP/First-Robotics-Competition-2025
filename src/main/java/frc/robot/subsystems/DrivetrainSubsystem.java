package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {

    private final SparkMax MDE = Constants.MotorConstants.MDE;
    private final SparkMax MTD = Constants.MotorConstants.MTD;
    private final SparkMax MDD = Constants.MotorConstants.MDD;
    private final SparkMax MTE = Constants.MotorConstants.MTE;

    private final SparkMaxConfig config = new SparkMaxConfig();

    public DrivetrainSubsystem() {
        config.voltageCompensation(Constants.voltageCompensation.Max_Voltagem);

        config
              .inverted(false)
              .idleMode(IdleMode.kBrake)
              .openLoopRampRate(0)
              .closedLoopRampRate(0);

        MDE.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        MTD.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        MDD.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        MTE.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void operateDrive(Double speed, Double rotation, double multiplier, boolean brake) {
        if (!brake) {
            MTE.set(((speed - (rotation / 2.6)) * multiplier));
            MDD.set(((-speed + (-rotation / 2.6)) * multiplier));
            MDE.set(((speed - (rotation / 2.6)) * multiplier));
            MTD.set(((-speed + (-rotation / 2.6)) * multiplier));
        } else {
            MTE.set(0);
            MDD.set(0);
            MDE.set(0);
            MTD.set(0);
        }
    }
}