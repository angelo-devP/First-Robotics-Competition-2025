package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.AutonomousSubsystem;

public class AutonomousEsquerda extends SequentialCommandGroup {

    public AutonomousEsquerda(AutonomousSubsystem autonomousSubsystem) {
        addCommands(
            new InstantCommand(() -> autonomousSubsystem.moveFrente(0.3), autonomousSubsystem),
            new WaitCommand(2.0),

            new InstantCommand(() -> autonomousSubsystem.stopMotors(), autonomousSubsystem),
            new WaitCommand(1.0),

            new InstantCommand(() -> autonomousSubsystem.retrairArm(0.2), autonomousSubsystem),

            new InstantCommand(() -> autonomousSubsystem.ligarRoller(0.5), autonomousSubsystem),
            new WaitCommand(1.0),

            new InstantCommand(() -> autonomousSubsystem.desligarRoller(), autonomousSubsystem)
        );
    }
}