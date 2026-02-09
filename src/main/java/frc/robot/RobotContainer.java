package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutonomousCentro;
import frc.robot.commands.AutonomousDireita;
import frc.robot.commands.AutonomousEsquerda;
import frc.robot.subsystems.AutonomousSubsystem;

public class RobotContainer {

    private final AutonomousSubsystem autonomousSubsystem = new AutonomousSubsystem();
    private final SendableChooser<Command> autonomousChooser = new SendableChooser<>();

    // Comandos autônomos
    private final Command autonomousCentro;
    private final Command autonomousDireita;
    private final Command autonomousEsquerda;

    public RobotContainer() {
        // Inicializa os comandos uma única vez
        autonomousCentro = new AutonomousCentro(autonomousSubsystem);
        autonomousDireita = new AutonomousDireita(autonomousSubsystem);
        autonomousEsquerda = new AutonomousEsquerda(autonomousSubsystem);

        // Adiciona as opções de rotina autônoma ao chooser
        autonomousChooser.setDefaultOption("Centro", autonomousCentro);
        autonomousChooser.addOption("Direita", autonomousDireita);
        autonomousChooser.addOption("Esquerda", autonomousEsquerda);

        // Publica no SmartDashboard
        SmartDashboard.putData("Selecione o Autonomo", autonomousChooser);
    }

    public Command getAutonomousCommand() {
        Command selectedCommand = autonomousChooser.getSelected();
        return selectedCommand;
    }
}