package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;

public class TimerTask {
    static int count = 0;

    public static void runner() {
        Timer timer = new Timer();
        java.util.TimerTask task = new java.util.TimerTask() {
            public void run() {
                try {
                    new Controller().controller();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                count++;
            }
        };
        timer.schedule(task, 0, 3600000);
    }
}
