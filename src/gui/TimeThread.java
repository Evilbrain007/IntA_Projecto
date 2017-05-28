package gui;

import java.time.Duration;
import java.util.Calendar;

/**
 * Created by Migue on 15/05/2017.
 */
public class TimeThread implements Runnable {
    private Thread t;
    private boolean done = false;
    private MainFrame mainFrame;
    private Duration duration;

    TimeThread(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.done = false;
        this.t = new Thread(this);
        this.t.start();
    }

    public void run() {
        Calendar beginning = Calendar.getInstance();

        while (!done) {

            duration = Duration.between(beginning.toInstant(), Calendar.getInstance().toInstant());
            mainFrame.setTime("Time Computing: "
                    + duration.getSeconds() / 60 / 60 + ":"
                    + ((duration.getSeconds() / 60) % 60) + ":"
                    + (duration.getSeconds() % 60) + "\n");

            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDone() {
        this.done = true;
        mainFrame.setTime("Time Computing: "
                + duration.getSeconds() / 60 / 60 + ":"
                + ((duration.getSeconds() / 60) % 60) + ":"
                + (duration.getSeconds() % 60) + ":"
                + (duration.getNano() % 10^9) + "\n");
    }
}
