/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;

  /**
   * Creates a new ConveyorPID.
   */
  //get distance and output goal 
  
public class Conveyor extends PIDSubsystem {
  VictorSPX motor;
  public static boolean close = false;
  public static boolean open = true;

  Solenoid intakeSol;
  TalonFX intakeMotor;
  public static boolean up = false;
  public static boolean down = true;
  SpeedControllerGroup intakeMotors;

  Encoder conveyorEncoder;
  DigitalInput infraredSensor1;
  DigitalInput infraredSensor2;

  Timer timer;

  static double kP = 0.2;
  static double kI = 0.0;
  static double kD = 0.0;

  public double testSetpoint = 0.0;

  /**
   * Creates a new conveyorPID.
   */
  public int mode = 0;
  public boolean lastBall = false;

  public Conveyor() {
    super(
        // The PIDController used by the subsystem
        new PIDController(kP, kI, kD));

        getController().setTolerance(0.1);
        setSetpoint(0.0);
        motor = new VictorSPX(Constants.motor);
    
        intakeSol = new Solenoid(Constants.intakeSol);
        intakeMotor = new TalonFX(Constants.intakeMotor);
        intakeMotor.setInverted(true);

        conveyorEncoder = new Encoder(Constants.conveyorEncoderA, Constants.conveyorEncoderB);
    
        timer = new Timer();
        infraredSensor1= new DigitalInput(0);
        infraredSensor2 = new DigitalInput(1);
    
        timer.start();
  }

  @Override
  public void useOutput(double output, double setpoint) {
    double limiter = 1.0;
    /*if(mode < 2) {
      limiter = 0.3; 
    } else{
      limiter = 1.0;
    }*/
    if(output > limiter){
      output = limiter;
    }
    else if (output < -limiter){
      output = -limiter; 
    }
    setConveyorMotors(output); 
    //System.out.println(output);
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return (double)conveyorEncoder.getRaw()/1000.0;
  }

  public boolean atSetpoint(double tolerance, double setpoint){
    if(getMeasurement() <= (setpoint - tolerance)){
      return true;
    }
    if (getMeasurement() >= (setpoint + tolerance)) {
      return true;
    }
    return false;
  }

  public void setConveyorMotors(double speed){
    motor.set(ControlMode.PercentOutput, speed);
  }

  public void conveyorReverseIntake(){
    motor.set(ControlMode.PercentOutput, 0.5);
  }
  public void conveyorIntake(){
    motor.set(ControlMode.PercentOutput, -0.7);
  }

  public void conveyorExtake(){
    motor.set(ControlMode.PercentOutput, 1.0);
  }

  public void conveyorDeploy(){
    motor.set(ControlMode.PercentOutput, -1.0);
  }

  public void conveyorStop(){
    motor.set(ControlMode.PercentOutput, 0.0);
  }

  public void setIntake(boolean state){
    intakeSol.set(state);
  }

  public void setIntakeMotors(double speed){
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }

  public int getConveyorEncoderDistance(){
    int distance = conveyorEncoder.getRaw();
    return distance;
  }

  public void resetEncoder(){
    conveyorEncoder.reset();
  }

  public boolean getSensorInput1(){
    boolean feedback = !infraredSensor1.get();
    return feedback;
  }

  public boolean getSensorInput2(){
    boolean feedback = !infraredSensor2.get();
    return feedback;
  }

  public boolean checker(){
    if(!getSensorInput1() != !true){ //leave it be
      timer.reset();
    }

    if(timer.get() > 0.0075){
      return true;
    }
    return false;
  }

  public void setmode(int newMode){
    mode = newMode; 
  }

  public int getMode(){
    return mode; 
  }
}
