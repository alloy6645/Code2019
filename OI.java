/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.ArmMove;
import frc.robot.commands.DeployHatch;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);


  public XboxController controller;
  public int port;

  // Configuration


	public OI() {
		controller = new XboxController(0);

		//Button buttonA = new JoystickButton(controller, 1);
 		//buttonB = new JoystickButton(controller, 2);
		//Button buttonX = new JoystickButton(controller, 3),
		Button buttonY = new JoystickButton(controller, 4);
		/* buttonBumperLeft = new JoystickButton(controller, 5),
		buttonBumperRight = new JoystickButton(controller, 6),
		buttonBack = new JoystickButton(controller, 7),
		buttonStart = new JoystickButton(controller, 8);*/

		Trigger triggerR = new JoystickButton(controller, 3);
		Trigger triggerL = new JoystickButton(controller, 2);

		buttonY.toggleWhenPressed(new DeployHatch());
		triggerL.whileActive(new ArmMove());
		triggerR.whileActive(new ArmMove());


	}

	double DEAD_ZONE = 0.08;
  // This is a sensitivity buffer for the joysticks. (Recommended 0.05 or higher)
  // Prevents the robot from going nuts at the slightest movement.
  double TRIGGER_BUFFER = 0.2;
  // This is a sensitivity buffer for the triggers. When the pressure on a trigger is greater than this value, the trigger is considered 'pressed'.
  // Recommended 0.2
  // Prevents wild outbursts from minor amounts of pressure.

  /*
  INDEX
  Joysticks 		- Getting X&Y Axis values for the Left and Right Joystick
  ABXY Buttons 	- Getting the Pressed status for the ABXY buttons
  DPad 			- Getting the Pressed status for the DPad Up/Down/Left/Right Buttons
  Bumpers 		- Getting the Pressed status for the Left & Right Bumpers
  Triggers 		- Getting the Pressed status or pressure placed on the Left & Right Triggers
  Start/Back		- Getting the Pressed status for the Start & Back Buttons

  RumbleMotors 	- Operating the Left & Right Rumble motors
  */
  
  public double correctDeadSpot(double value) {
    if (Math.abs(value) < DEAD_ZONE) {
			return 0;
		}
		return value;
	}

	public boolean getButton(int buttonNumber) {
		return controller.getRawButton(buttonNumber);
	}

	public double getAxis(int axisNumber) {
		return controller.getRawAxis(axisNumber);
	}

	public int getPOV(int povNumber) {
		return controller.getPOV(povNumber);
  }
  
  // Thumbsticks

		// Left Thumbstick

	public double getLeftThumbstickX() {
		return correctDeadSpot(getAxis(0));
	}

	public double getLeftThumbstickY() {
		return correctDeadSpot(getAxis(1));
	}

		// Right Thumbstick

	public double getRightThumbstickX() {
		return correctDeadSpot(getAxis(4));
	}

	public double getRightThumbstickY() {
		return correctDeadSpot(getAxis(5));
  }
  
  // ABXY Buttons

	public boolean getAButton() {
		return getButton(1);
	}

	public boolean getBButton() {
		return getButton(2);
	}

	public boolean getXButton() {
		return getButton(3);
	}

	public boolean getYButton() {
		return getButton(4);
  }
  
  // DPad
	// The DPad is unique in that it works with a 0-360 degrees POV

	public boolean getDPadUp(){
		int degree = getPOV(0);
		return (degree >= 315 || degree <= 45);
	}

	public boolean getDPadDown(){
		int degree = getPOV(0);
		return (degree <= 225 && degree >= 135);
	}

	public boolean getDPadLeft(){
		int degree = getPOV(0);
		return (degree <= 315 && degree >= 225);
	}

	public boolean getDPadRight(){
		int degree = getPOV(0);
		return (degree <= 135 && degree >= 45);
  }
  
  // Bumpers

	public boolean getLeftBumper(){
		return getButton(5);
	}

	public boolean getRightBumper(){
		return getButton(6);
  }
  
  // Triggers

		// Returns the amount of pressure placed on the triggers

	public double getLeftTriggerAbsolute(){
		return Math.abs(getAxis(2));
	}

	public double getRightTriggerAbsolute(){
		return Math.abs(getAxis(3));
	}

		// Returns simple bool values to check if the trigger is considered down or not

	public boolean getLeftTrigger(){
		return (getLeftTriggerAbsolute() >= TRIGGER_BUFFER);
	}

	public boolean getRightTrigger(){
		return (getRightTriggerAbsolute() >= TRIGGER_BUFFER);
  }
  

  // Start & Back

	public boolean getBackButton(){
		return getButton(7);
	}

	public boolean getStartButton(){
		return getButton(8);
	}


  // Rumble Function

	public boolean setRumble(String type, float value) {
    if ((type == "Both") || (type == "Left")) {
        controller.setRumble(Joystick.RumbleType.kLeftRumble, value);
    }
    if ((type == "Both") || (type == "Right")) {
      controller.setRumble(Joystick.RumbleType.kRightRumble, value);
    }
    return true;

}



  
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
