/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.IntakeGroup;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class ConveyorBackout extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Conveyor conveyor; 
  private boolean ender;
  double target = 0.5;
  double tolerance = 0.1; 
  int modenum = 1;
  /**
   * Creates a new ConveyorBackout.
   */
  public ConveyorBackout(Conveyor m_conveyor) {
    addRequirements(m_conveyor);
    conveyor = m_conveyor; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ender = false;
     conveyor.enable() ; 
     conveyor.setIntake(conveyor.down);
     System.out.println("Starting step 1!");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(conveyor.getMeasurement() >= (target - tolerance) || conveyor.mode != modenum){
      ender = true; 
      if(conveyor.mode != modenum){
        System.out.println("Step 1 will finish because my mode is not 1! My mode is: " + conveyor.mode);
      } else if(conveyor.getMeasurement() >= (target - tolerance)){
        System.out.println("Step 1 will finish because I have reached my target!");
      }
    } else{
      conveyor.setIntakeMotors(0.0);
      conveyor.setSetpoint(target);
      System.out.println("I should have moved the conveyor out! I am still not close enough to my target! My error is: " + (0.5 - conveyor.getMeasurement()));
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(conveyor.mode == modenum && !interrupted){
      conveyor.mode = 2; 
      conveyor.resetEncoder();
      conveyor.setSetpoint(0.0);
      System.out.println("Step 1 Finished! My mode is now: " + conveyor.mode);
    }
    System.out.println("Step 1 Finished!");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ender;
  }
}
