// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeGroup.ConveyorBackout;
import frc.robot.commands.IntakeGroup.ConveyorIn;
import frc.robot.commands.IntakeGroup.FrontIntaking;
import frc.robot.subsystems.Conveyor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Intake extends SequentialCommandGroup {
  /** Creates a new IntakeGroup. */
  public Intake(Conveyor m_conveyor) {
    Conveyor conveyor = m_conveyor;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new FrontIntaking(conveyor), new ConveyorBackout(conveyor), new ConveyorIn(conveyor));
  }
}
