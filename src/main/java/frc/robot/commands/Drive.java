// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class Drive extends CommandBase {
  /** Creates a new Drive. */

  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain drivetrain;
  private final Limelight limelight;

  public Drive(Drivetrain m_drivetrain, Limelight m_limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
    addRequirements(m_limelight);
    drivetrain = m_drivetrain;
    limelight = m_limelight;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(drivetrain.firstRead){
      drivetrain.gyroOffset = drivetrain.getPose().getRotation().getDegrees();
      drivetrain.firstRead = false;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    final double pwrConst = 1.0; //please do not go lower than 0.3, the robot doesnt want to move

    double throttle = RobotContainer.m_driveController.getY(Hand.kLeft) * pwrConst;
    
    double turn = RobotContainer.m_driveController.getX(Hand.kRight) * pwrConst;

    drivetrain.setTestMotor(0.2 * throttle);

    System.out.println(drivetrain.getTestEncoder());

    drivetrain.arcadeDrive(0.0, 0.0);


    /*
    Pose2d odometry_position = drivetrain.getPose();
    double rotation = odometry_position.getRotation().getDegrees();
    double od_x = odometry_position.getX();
    double od_y = odometry_position.getY();

    Pose2d vision_position = limelight.calculateDisplacement(rotation);
    double vi_x = vision_position.getX();
    double vi_y = vision_position.getY();

    System.out.print("Odometry X: " + od_x + ", Odometry Y: " + od_y);
    System.out.print("Vision X: " + vi_x + ", Vision Y: " + vi_y);
    */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
