// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */

  double tx;
  double ty;
  double ta;

  double x_initial = -15.3125; // Robot's initial x relative to starting target
  double y_initial = -115.75; // Robot's initial y relative to starting target
  double h_robot = 24.5625; //height of mount
  double h_target = 82.875; //height of target
  double a_limelight = 35.0054; //38.83; //angle of limelight mount

  public Limelight() {
    reset();
  }

  void reset(){
    tx = 0.0;
    ty = 0.0;
    ta = 0.0;
  }

  public double getTx() {
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    return tx;
  }

  public double getTy(){
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    return ty;
  }


  public double getDistance(){

    double angleToTarget = a_limelight + getTy();

    double heightDiff = h_target - h_robot;

    double distance = heightDiff / Math.tan(Math.toRadians(angleToTarget));

    return distance;
  }

  public Pose2d calculateDisplacement(double robotOrientation){
    Pose2d displacement;

    double distance = getDistance();

    double x_final = distance * Math.sin(robotOrientation); // Robot's final x position
    double y_final = distance * Math.cos(robotOrientation); // Robot's final y position

    double delta_x = x_final - x_initial;
    double delta_y = y_final - y_initial;

    displacement = new Pose2d(delta_x, delta_y, new Rotation2d(Math.toRadians(robotOrientation)));

    return displacement;
  }

  public void update(){
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    update();
  }
}
