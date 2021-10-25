// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.Deploy;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Extend;
import frc.robot.commands.Intake;
import frc.robot.commands.Outtake;
import frc.robot.commands.PIDTest;
import frc.robot.commands.Retract;
import frc.robot.commands.Stop;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  enum Goal {HIGH, LOW, RED};

  enum StartingPosition {LEFT, RIGHT};

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final Drivetrain m_drivetrain = new Drivetrain();
  public final Limelight m_limelight = new Limelight();
  public final Climber m_climber = new Climber();
  public final Conveyor m_conveyor = new Conveyor();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static XboxController m_driveController = new XboxController(0);
  public static XboxController m_mechController = new XboxController(1);

  private static JoystickButton climberUpButton = new JoystickButton(m_driveController, Button.kX.value);
  private static JoystickButton climberDownButton = new JoystickButton(m_driveController, Button.kB.value);

  private static JoystickButton intakeButton = new JoystickButton(m_mechController, Button.kBumperRight.value);
  private static JoystickButton outtakeButton = new JoystickButton(m_mechController, Button.kBumperLeft.value);
  private static JoystickButton deployButton = new JoystickButton(m_mechController, Button.kX.value);
  private static JoystickButton testPIDButton = new JoystickButton(m_mechController, Button.kY.value);

  // The robot's current state
  public Goal goal = Goal.HIGH;
  public StartingPosition startPos = StartingPosition.LEFT;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    // Default Commands
    m_drivetrain.setDefaultCommand(new Drive(m_drivetrain, m_limelight));
    m_climber.setDefaultCommand(new Retract(m_climber));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    climberUpButton.whileHeld(new Extend(m_climber));
    climberDownButton.whenPressed(new Retract(m_climber));
    intakeButton.whileHeld(new Intake(m_conveyor)); //Intake
    intakeButton.whenReleased(new Stop(m_conveyor));
    outtakeButton.whenPressed(new Outtake(m_conveyor)); //Outtake
    outtakeButton.whenReleased(new Stop(m_conveyor));
    deployButton.whenPressed(new Deploy(m_conveyor)); //Deploy
    deployButton.whenReleased(new Stop(m_conveyor));
    testPIDButton.whenPressed(new PIDTest(m_conveyor));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
