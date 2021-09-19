package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import  edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveToDistance extends PIDCommand {
    private DriveTrain dt;
    private DifferentialDrive driveTrain;
    private static double setPoint = 60;
    private static double p = .01;
    private static double i = 0;
    private static double d = 0;

    public DriveToDistance(DriveTrain driveTrain) {

        super(new PIDController(p,i,d), 
                () -> driveTrain.getDSReading(), 
                ()-> setPoint,  // determine the distance to move to in inches. This could be a 
                           // parameter to this command 
                (double d) -> driveTrain.moveForward(-d),
                 driveTrain);
        this.dt = driveTrain;
        // call setTolerance(positionTolerance) to set the acceptable error range

        /* 
            PIDCommand(PIDController controller, DoubleSupplier measurementSource,
                    DoubleSupplier setpointSource, DoubleConsumer useOutput,
                    Subsystem... requirements)
        */
    }

    @Override
    public boolean isFinished() {
        // call this.getController().getPositionError()
        var diff = setPoint +- this.getController().getPositionError();
        if (diff > -3 && diff < 3) {
            return true;
        }
        // if within an exceptable range, return true.
        return false;
    }



}
