import org.firmata4j.IODevice;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;
import java.util.Timer;

public class Minor_Project	{
    public	static	void	main(String[]	args)
            throws	InterruptedException,	IOException
    {
        // Starts the Arduino board
        IODevice device = new FirmataDevice("COM5");
        device.start();
        device.ensureInitializationIsDone();

        // Gets voltage and pump from their respective pins
        var voltage = device.getPin(16);
        var pump = device.getPin(7);

        // TimerTask is called
        var myTimer = new TimerMinor(voltage, pump);
        new Timer().schedule(myTimer, 0, 1000);


    }
}