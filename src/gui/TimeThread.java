package gui;

import java.time.Duration;
import java.util.Calendar;

/**
 * Created by Migue on 15/05/2017.
 */
public class TimeThread implements Runnable {
    private volatile Thread t;
    private boolean done = false;
    private MainFrame mainFrame;
    private Duration duration;

    TimeThread(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }


    public void startTime() {
        this.done = false;
        this.t = new Thread(this);
        this.t.start();
    }

    public void run() {
        Calendar beginning = Calendar.getInstance();

        while (!done && Thread.currentThread()==this.t) {

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

        if(this.t != null) {
            mainFrame.setTime("Time Computing: "
                    + duration.getSeconds() / 60 / 60 + ":"
                    + ((duration.getSeconds() / 60) % 60) + ":"
                    + (duration.getSeconds() % 60) + ":"
                    + (duration.getNano()) + "\n");
        }
    }
}
