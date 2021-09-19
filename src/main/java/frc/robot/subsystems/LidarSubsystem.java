package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.coprocessor.Lidar;

public class LidarSubsystem extends SubsystemBase {
    public LidarSubsystem() {
        Lidar.initI2c(); 
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Distance", Lidar.getDistance());
        SmartDashboard.putNumber("Count", Lidar.getCount());
        SmartDashboard.putNumber("Lidar", Lidar.getLidarDistance());
    }
}