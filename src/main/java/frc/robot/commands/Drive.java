package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {
    private DriveTrain driveTrain;
    private GenericHID joy;

    public Drive(DriveTrain driveTrain, GenericHID joy){
        this.driveTrain = driveTrain;
        this.joy = joy;
        this.addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        driveTrain.arcadeDrive(joy);
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }

}