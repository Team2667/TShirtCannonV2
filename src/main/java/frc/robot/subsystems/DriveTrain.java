package frc.robot.subsystems;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.Constants;
import frc.robot.coprocessor.Lidar;

public class DriveTrain extends SubsystemBase {
    private WPI_TalonSRX leftPrimary;
    private WPI_TalonSRX leftFollower;
    private WPI_TalonSRX rightPrimary;
    private WPI_TalonSRX rightFollower;
    private DifferentialDrive diffDriveTrain;
    private double PID_Val;
    // Add an analog sensor for the distance sensor


    public DriveTrain(){
        leftPrimary = new WPI_TalonSRX(Constants.DT_LEFT_PRIMARY);
        rightPrimary = new WPI_TalonSRX(Constants.DT_RIGHT_PRIMARY);
        leftFollower = new WPI_TalonSRX(Constants.DT_LEFT_FOLLOWER);
        rightFollower = new WPI_TalonSRX(Constants.DT_RIGHT_FOLLOWER);
        leftFollower.follow(leftPrimary);
        rightFollower.follow(rightPrimary);
        rightPrimary.setInverted(true);
        rightFollower.setInverted(true);
        diffDriveTrain = new DifferentialDrive(leftPrimary, rightPrimary);
        Lidar.initI2c();
    }

    public void arcadeDrive(GenericHID jstick){
        diffDriveTrain.arcadeDrive(jstick.getX(), jstick.getY());
    }

    public double getDSReading(){
        // multiply getValue by 1.02396 returns distance in millimeters
        // devide millimeters by 20.54 returns distance in inches.
        return Lidar.getDistance();
    }

    public void moveForward(double value) {
        PID_Val = value;
   //     var signNumber = value > 0 ? 1 : -1;
   //     var absValue = Math.abs(value) > 1 ? 1 : Math.abs(value);
   //     double speed = (signNumber*absValue);        
        diffDriveTrain.arcadeDrive(0, value);
        SmartDashboard.putNumber("PID Val", PID_Val);
    
    }
    public void stop() {
        diffDriveTrain.stopMotor();
    }

    public void periodic(){
        SmartDashboard.putNumber("Distance Sensor", getDSReading());
    }

}