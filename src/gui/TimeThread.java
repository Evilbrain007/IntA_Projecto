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

    TimeThread(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.done = false;
        this.t = new Thread(this);
        this.t.start();
    }

    public void run() {
        Calendar beginning = Calendar.getInstance();

        while (!done) {

            Duration duration = Duration.between(beginning.toInstant(), Calendar.getInstance().toInstant());
            mainFrame.setTime("Time Computing: "
                    + duration.getSeconds() / 60 / 60 + ":"
                    + duration.getSeconds() / 60 + ":"
                    + duration.getSeconds() + "\n");

            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}