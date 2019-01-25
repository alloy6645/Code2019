/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Hatch extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid hatchPiston;
  private boolean state;

  public Hatch(){
    hatchPiston = new DoubleSolenoid(RobotMap.hatch_retract, RobotMap.hatch_extend);
    retract();
    state = false;
  }

  public void extend(){
    hatchPiston.set(DoubleSolenoid.Value.kForward);
    System.out.println("Piston moved forward");
    state = true;

  }

  public void retract(){
    hatchPiston.set(DoubleSolenoid.Value.kReverse);
    System.out.println("Piston moved backward");
    state = false;
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
