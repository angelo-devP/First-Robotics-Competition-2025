package frc.robot.subsystems;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AutonomousSubsystem extends SubsystemBase {

    private final SparkMax MDE = Constants.MotorConstants.MDE;
    private final SparkMax MTD = Constants.MotorConstants.MTD;
    private final SparkMax MDD = Constants.MotorConstants.MDD;
    private final SparkMax MTE = Constants.MotorConstants.MTE;

    private final SparkMax rollerMotor = Constants.MotorConstants.rollerMotor;
    private final SparkMax armMotor = Constants.MotorConstants.armMotor;

    public AutonomousSubsystem() {
        configureMotors();
    }

    private void configureMotors() {
        configureMotor(MDE);
        configureMotor(MTD);
        configureMotor(MDD);
        configureMotor(MTE);
        configureMotor(rollerMotor);
        configureMotor(armMotor);
    }

    private void configureMotor(SparkMax motor) {
        SparkMaxConfig config = new SparkMaxConfig();

        config.voltageCompensation(Constants.voltageCompensation.Max_Voltagem);

        config.inverted(false)
              .idleMode(IdleMode.kBrake)
              .openLoopRampRate(1.0)
              .closedLoopRampRate(1.0);

        motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void moveFrente(double speed) {
        System.out.println("Movendo para frente");
        MDE.set(speed);
        MTD.set(-speed);
        MDD.set(-speed);
        MTE.set(speed);
    }

    public void moveTras(double speed) {
        System.out.println("Movendo para tras");
        MDE.set(-speed);
        MTD.set(speed);
        MDD.set(speed);
        MTE.set(-speed);
    }

    public void viraDireita(double speed) {
        System.out.println("Girando para a direita");
        MDE.set(speed);
        MTD.set(speed);
        MDD.set(speed);
        MTE.set(speed);
    }

    public void viraEsquerda(double speed) {
        System.out.println("Girando para a esquerda");
        MDE.set(-speed);
        MTD.set(-speed);
        MDD.set(-speed);
        MTE.set(-speed);
    }

    public void stopMotors() {
        System.out.println("Parando motores");
        MDE.set(0);
        MTD.set(0);
        MDD.set(0);
        MTE.set(0);
    }

    public void ligarRoller(double speed) {
        System.out.println("Ligando Roller");
        rollerMotor.set(speed);
    }

    public void desligarRoller() {
        System.out.println("Desligando Roller");
        rollerMotor.set(0);
    }

    public void extenderArm(double speed) {
        System.out.println("Extendendo o Arm");
        armMotor.set(speed);
    }

    public void retrairArm(double speed) {
        System.out.println("Retraindo o Arm");
        armMotor.set(-speed);
    }
}