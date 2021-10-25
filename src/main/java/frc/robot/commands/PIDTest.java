// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class PIDTest extends CommandBase {
  /** Creates a new PIDTest. */
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Conveyor conveyor;
  double step = -8.5;
  boolean ender = false;
  public PIDTest(Conveyor m_conveyor) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_conveyor);
    conveyor = m_conveyor;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    conveyor.enable();
    conveyor.setIntake(conveyor.down);
    conveyor.setIntakeMotors(0.3);
    conveyor.setSetpoint(conveyor.testSetpoint);
    System.out.println("Starting TESTPID: Setpoint: " + conveyor.testSetpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(conveyor.atSetpoint(0.05, conveyor.testSetpoint)){
      System.out.println("TESTPID: Conveyor at setpoint: " + conveyor.getSetpoint());
      ender = true;
    } else{
      System.out.println("Conveyor Pos: " + conveyor.getMeasurement());
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyor.testSetpoint += step;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ender;
  }
}
