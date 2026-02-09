package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.RollerSubsystem;

public class Robot extends TimedRobot {
    
    private final Joystick joystick = new Joystick(0);
    private Command autonomousCommand;
    private RobotContainer robotContainer;
 
    private final Timer tempo = new Timer();
    
    private final ArmSubsystem armSubsystem = new ArmSubsystem();
    private final RollerSubsystem rollerSubsystem = new RollerSubsystem();
    private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
    
    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
    }
    
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        climberSubsystem.display();
    }
     
    @Override
    public void autonomousInit() {
        if (robotContainer != null) {
            autonomousCommand = robotContainer.getAutonomousCommand();
            if (autonomousCommand != null) {
                System.out.println("Executando modo autonomo: " + autonomousCommand.getName());
                autonomousCommand.schedule();
            } else {
                System.out.println("Nenhum comando autonomo foi selecionado.");
            }
        } else {
            System.out.println("robotContainer é nulo!");
        }
    }
    
    @Override
    public void autonomousPeriodic() {
    
    }
    
    @Override
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        tempo.reset();
        tempo.start();
    }

    @Override
    public void teleopPeriodic() {
        controlRoller();
        controlArm();
        controlMovement();
        controlClimber();
    }

    private void controlRoller() {
        boolean L1 = joystick.getRawButton(Constants.ButtonConstants.BUTTON_INTAKE);
        boolean processor = joystick.getRawButton(Constants.ButtonConstants.BUTTON_PROCESSOR);
        rollerSubsystem.operateRoller(L1, processor);
    }

    private void controlArm() {
        boolean activateArm = joystick.getRawButton(Constants.ButtonConstants.BUTTON_PROCESSOR_EXTEND);
        boolean reverseArm = joystick.getRawButton(Constants.ButtonConstants.BUTTON_PROCESSOR_RETRACT);
        armSubsystem.operateArm(activateArm, reverseArm);
    }

    private void controlMovement() {
        double speed = joystick.getRawAxis(Constants.AxisConstants.AXIS_SPEED);
        double rotation = joystick.getRawAxis(Constants.AxisConstants.AXIS_ROTATION);
        double multiplier = joystick.getRawAxis(Constants.AxisConstants.AXIS_MULTIPLIER);
        boolean brake = joystick.getRawButton(Constants.ButtonConstants.BUTTON_BRAKE);
        drivetrainSubsystem.operateDrive(speed, rotation, multiplier, brake);
    }

    private void controlClimber() {
        boolean climberUp = joystick.getRawButton(Constants.ButtonConstants.BUTTON_CLIMBER_UP);
        boolean climberDown = joystick.getRawButton(Constants.ButtonConstants.BUTTON_CLIMBER_DOWN);
        climberSubsystem.operateClimber(climberUp, climberDown);
    }

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {}

    @Override
    public void testPeriodic() {}

    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {}
}