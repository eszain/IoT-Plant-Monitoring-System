import org.firmata4j.Pin;

import java.util.TimerTask;
public class TimerMinor extends TimerTask {
    private	int	duration;
    private	final Pin voltage;
    private	final Pin pump;
    //	class	constructor.
    public TimerMinor(Pin voltage, Pin	pump) {
        this.voltage = voltage;
        this.pump =	pump;
    }


    private int timer = 0;

    @Override
    public void run() {

        // Voltage values measured from my plant
        final double reallyDryValue = 350;
        final double moistureThreshold = 250;
        final double saturatedValue = 220;

        // State machine timings
        final int red = 5;
        final int yellow = 7;
        final int green = 12;

        try {
            // Increment timer of state machine
            timer++;

            // Red light "state"
            if (timer < red) {
                if (voltage.getValue() > reallyDryValue) {
                    System.out.println("Soil is dry! Time to water!");
                    pump.setValue(1);
                }
            }
            // Yellow light "state"
            if (timer > red && timer <= yellow) {
                if (voltage.getValue() > moistureThreshold) {
                    System.out.println("Soil is a bit wet! Time to water for a bit!");
                    pump.setValue(1);
                }
            }
            // Green light "state"
            if (timer > yellow && timer <= green) {
                if (voltage.getValue() < saturatedValue) {
                    System.out.println("Soil is wet! No need to water!");
                    pump.setValue(0);
                }
            }

            // State machine restarts
            if (timer == 12) {
                timer = 0;
            }

        }
        catch (Exception ex) {
            System.out.println("error");
        }
    }
}