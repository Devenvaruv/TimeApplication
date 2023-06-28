package com.example.resuable;

public class timerLogic {
    public int seconds = 0;
    public int minutes = 0;
    public int hours = 0;

    public void runner() {
        if (seconds >= 60) {
            if (minutes >= 60) {
                hours++;
                minutes = 0;
            }
            minutes++;
            seconds = 0;
            if (hours >= 24) {
                hours = 0;
            }
        }
        seconds++;
    }

}

