package com.example.resuable;

import java.util.Timer;
import java.util.TimerTask;

class Helper extends TimerTask
{
    public static int i = 0;
    public void run()
    {
        System.out.println("Timer ran " + ++i);
    }
}

 class Test
{
    public static void main(String[] args)
    {

        Timer timer = new Timer();
        TimerTask task = new Helper();

        timer.schedule(task, 500, 8000);

    }
}

public class timerLogic {
    private int seconds = 00;
    private int minutes = 00;
    private int hours = 00;

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public static void start() {
        timerLogic timerlogic = new timerLogic();
        System.out.println(timerlogic.hours + ":" + timerlogic.hours + ":" + timerlogic.minutes);
    }

    public void end() {
        System.out.println("end clock logic from here");
    }

}
