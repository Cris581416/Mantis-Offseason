/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.IntakeGroup;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.RobotContainer;


public class FrontIntaking extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Conveyor conveyor;
  public boolean intaking;
  int modenum = 0;

  /**
   * Creates a new Test.
   */
  public FrontIntaking(final Conveyor m_conveyor) {
    addRequirements(m_conveyor);
    conveyor = m_conveyor;
  }
  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    conveyor.enable(); 
    conveyor.setIntake(conveyor.down);
    intaking = false;
    System.out.println("At the start!");
    if (conveyor.getSensorInput2()) {
      conveyor.lastBall = true;
      System.out.println("Last Ball");
    } else {
      conveyor.lastBall = false;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    if(conveyor.checker() == true || conveyor.mode != modenum){
      if(conveyor.mode != modenum){
        System.out.println("Step 0 has finished because my mode isnt 0. My mode is: " + conveyor.mode);
      }
      intaking = true;
    } else{
      conveyor.setIntakeMotors(0.3);
      System.out.println("Front Intake Speed: " + RobotContainer.m_mechController.getTriggerAxis(Hand.kRight));
    }
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    if(conveyor.mode == modenum && !interrupted){
      conveyor.mode = 1; 
      conveyor.resetEncoder();
      conveyor.setSetpoint(0.0);
      System.out.println("My mode is now: " + conveyor.mode);
    }else{
      System.out.println("Finishing step 0");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return intaking; 
  }
}
