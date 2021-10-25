/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.IntakeGroup;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import edu.wpi.first.wpilibj.Timer;

public class ConveyorIn extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Conveyor conveyor;
  private boolean ender;
  private Timer timer;
  private boolean fifthBall;
  double target = -9.0;
  double tolerance = 0.1;
  int modenum = 2;
  /**
   * Creates a new ConveyorIn.
   */
  public ConveyorIn(Conveyor m_conveyor) {
    addRequirements(m_conveyor);
    conveyor = m_conveyor;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ender = false;
    fifthBall = false;
    conveyor.enable();
    conveyor.setIntake(conveyor.down);
    System.out.println("Starting Step 3!");
    timer = new Timer();
    if (conveyor.lastBall) target = -1.0;
    else target = -8.5;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(conveyor.getMeasurement() <= (target + tolerance) || conveyor.mode != modenum){
      ender = true; 
      if(conveyor.mode != modenum){
        System.out.println("Step 3 will finish because my mode is not 3! My mode is: " + conveyor.mode);
      } else if(conveyor.getMeasurement() <= (target + tolerance)){
        System.out.println("Step 3 will finish because I have reached my target!");
      }
    } else{
        conveyor.setIntakeMotors(0.0);
        conveyor.setSetpoint(target);
        System.out.println("I should have moved the conveyor out! I am still not close enough to my target! My error is: " + (9 + conveyor.getMeasurement()));
      }
    }

  // Called once the command ends or is interrupted.
  @Override
public void end(boolean interrupted) {
  if(conveyor.mode == modenum && !interrupted){
    conveyor.mode = 0; 
    if (conveyor.lastBall) {
      conveyor.mode = 4;
      conveyor.lastBall = false;
    }
    conveyor.resetEncoder();
    conveyor.setSetpoint(0.0);
    System.out.println("My mode is now: " + conveyor.mode);
  }
  System.out.println("Step 3 has finished!");
}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ender;
  }
}
