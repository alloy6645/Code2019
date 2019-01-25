/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class CargoArm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //private Spark armMotor;

  Spark armMotorSpark; //= RobotMap.armMove;
  private boolean state;

  public CargoArm(){
    armMotorSpark = new Spark(4);
    //armRetract();
    state = false;
  }

  public void armLift(){
    armMotorSpark.set(1.0);
    state = true;
  }

  public void armRetract(){
    armMotorSpark.set(-1.0);
    state = false;
  }

  public void armStop(){
    armMotorSpark.set(0.0);
  }

  public boolean get(){
    return state;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
