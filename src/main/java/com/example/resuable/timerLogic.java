package com.example.resuable;
import java.util.Timer;
import java.util.TimerTask;

public class timerLogic extends TimerTask {
    public int seconds = 0;
    public int minutes = 0;
    public int hours = 0;

    @Override
    public void run() {
        if (seconds >= 60) {
            if (minutes >= 60) {
                hours++;
                minutes = 0;
            }
            minutes++;
            seconds = 0;
            if(hours >= 24){ hours = 0;}
        }
        seconds++;
        //System.out.printf("TIME: %02d:%02d:%02d\n", hours, minutes, seconds);
    }

    public static void mainRun() {
        Timer timer = new Timer();
        timerLogic task = new timerLogic();
        timer.schedule(task, 0, 1000);
    }
}

