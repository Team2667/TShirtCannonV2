/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveToDistance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LidarSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private XboxController joyStick;
  private  DriveTrain driveTrain;
  private LidarSubsystem lidarSubsystem;
  private DriveToDistance driveToDistance;
  private  Drive driveCmd;




  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    joyStick = new XboxController(0);
    //createDriveTrainSubsystem();
    createLidarSubsystem();

  }

  private void createLidarSubsystem(){
    this.lidarSubsystem = new LidarSubsystem();
  }

  private void createDriveTrainSubsystem(){
    driveTrain = new DriveTrain();
    driveCmd = new Drive(driveTrain,joyStick);
    driveTrain.setDefaultCommand(driveCmd);
    driveToDistance = new DriveToDistance(driveTrain);
    JoystickButton x = new JoystickButton(joyStick,XboxController.Button.kX.value);
    x.whenPressed(driveToDistance);


    // Add the drive to distance command to a button
    
    }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return driveCmd;
  }
}
