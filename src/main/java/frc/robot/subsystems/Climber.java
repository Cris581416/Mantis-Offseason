// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */

  DoubleSolenoid hooks;

  WPI_TalonFX winch;

  public Climber() {
    hooks = new DoubleSolenoid(0, 1);
    winch = new WPI_TalonFX(5);
  }

  public void retractHooks(){
    hooks.set(DoubleSolenoid.Value.kReverse);
  }

  public void extendHooks(){
    hooks.set(DoubleSolenoid.Value.kForward);
  }

  public void setMotor(double speed){
    winch.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
