/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.Robot;


public class Outtake extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Conveyor conveyor;
  /**
   * Creates a new Test.
   */
  public Outtake(Conveyor m_conveyor) {
    addRequirements(m_conveyor);
    conveyor = m_conveyor;
  }
  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    //conveyor.setStopper(conveyor.close);
    conveyor.disable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    conveyor.setIntake(conveyor.up);
    //conveyor.setConveyorMotors(-0.7);
    conveyor.conveyorExtake();
    conveyor.setIntakeMotors(-0.0);
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    conveyor.disable();
    conveyor.mode = 0;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }
}