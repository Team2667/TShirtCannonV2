package frc.robot.coprocessor;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.nio.ByteBuffer;

public class Lidar {
    private static I2C Wire;
    private static I2C counterWire;
    private static I2C lidarWire;

    public static void initI2c(){
    Wire = new I2C(Port.kMXP, 4);
    counterWire = new I2C(Port.kMXP,6);
    lidarWire = new I2C(Port.kMXP,98);
    }

    public static boolean isAddressFound(){
      return !Wire.addressOnly();
    }
    
    public static int getDistance() {
        int numbytes = 4;
        byte[] returnData = new byte[numbytes];
        if (!Wire.readOnly(returnData, numbytes)){
          return ByteBuffer.wrap(returnData, 0, 4).getInt();
        }
		    return -1;
    }
    
    public static int getCount() {
      int numbytes = 4;
      byte[] returnData = new byte[numbytes];
      if (!counterWire.readOnly(returnData, numbytes)){
        return ByteBuffer.wrap(returnData, 0, 4).getInt();
      }
      return -10;
    }

    // this method gets the distance from a lidarlite via I2C. In order for this to work
    // I connected the Lidar to a rioduino which was connected to the MXP port of the roborio. 
    // the signal to the Lidar goes through a circut in the rioduino that converts 3.5v to 5v
    // and back. The I2C address for the Lidar is 98. I got this by looking at the code for the Lidar 
    // arduino library. when connecting the lidar to I2C, back is ground, red is 5v, green is SCL and
    // blue is SDA.
    public static int getLidarDistance() {
      int numbytes = 2;
      byte[] returnData = new byte[numbytes];
      if (!lidarWire.write(0x00,0x04) && !lidarWire.read(0x8f,2,returnData)){
        return ByteBuffer.wrap(returnData).getShort();
      } 
      return -10;
    }
}