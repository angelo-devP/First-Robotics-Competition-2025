package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

public final class Constants {

    public static class MotorConstants {
        public static SparkMax MDE = new SparkMax(2, MotorType.kBrushless); // Motor Dianteiro Esquerdo
        public static SparkMax MTD = new SparkMax(4, MotorType.kBrushless); // Motor Traseiro Direito
        public static SparkMax MDD = new SparkMax(5, MotorType.kBrushless); // Motor Dianteiro Direito
        public static SparkMax MTE = new SparkMax(3, MotorType.kBrushless); // Motor Traseiro Esquerdo
        public static SparkMax climberMotor = new SparkMax(8, MotorType.kBrushless); // Motor do Climber
        public static SparkMax rollerMotor = new SparkMax(7, MotorType.kBrushless);  // Motor do Roller
        public static SparkMax armMotor = new SparkMax(6, MotorType.kBrushless); // Motor do Arm
    }

    public static class AxisConstants {
        public static final int AXIS_SPEED = 1; // Aceleração
        public static final int AXIS_ROTATION = 2; // Rotação
        public static final int AXIS_MULTIPLIER = 3; // Multiplicador de velocidade
    }

    public static class ButtonConstants {
        public static final int BUTTON_INTAKE = 1; // Pontua L1
        public static final int BUTTON_PROCESSOR = 2; // Pontua processador
        public static final int BUTTON_PROCESSOR_EXTEND = 3;  // Sobe o arm
        public static final int BUTTON_PROCESSOR_RETRACT = 4; // Desce o arm
        public static final int BUTTON_BRAKE = 5; // Freia os motores do Drivetrain
        public static final int BUTTON_CLIMBER_UP = 13; // Pendura o robô
        public static final int BUTTON_CLIMBER_DOWN = 12; // Despendura o robô
        public static final int BUTTON_CLIMBER_INIT = 14; // Reseta a posicao do climber
    }

    public static class voltageCompensation {
        public static final int Max_Voltagem = 12; // 12 Volts
    }
}