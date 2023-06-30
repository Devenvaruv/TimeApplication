package com.example.timeapplication;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.resuable.*;
import javafx.scene.control.TextField;

import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    private Timer timer;
    private timerLogic task;
    private boolean switcher;
    public int tempSeconds = 0;
    private int tempMinutes = 0;
    private int tempHours = 0;
    @FXML
    private TextField goalTextField;

    @FXML
    protected void forGoalTextField() {
        goalTextField.setText("hello stupid");
        goalTextField.setPromptText("hello cockroach");

    }


    @FXML
    private Label timerField;

    @FXML
    private Label timerField2;

    @FXML
    protected void onStartButtonClick() {

        if (switcher) {
            System.out.println("here is timer.cancel located");
            tempSeconds = tempSeconds + task.seconds;
            tempMinutes = tempMinutes + task.minutes;
            tempHours = tempHours + task.hours;
            System.out.println(tempSeconds);
            ExcelDataWriter.exceler(tempSeconds);
            // Pause the timer
            timer.cancel();

        }

        if (!switcher) {
            System.out.println("here is timer resume/start located");
            timer = new Timer();
            task = new timerLogic();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    task.runner();
                    Platform.runLater(() -> timerField.setText(String.format("%02d:%02d:%02d", task.hours + tempHours, task.minutes + tempMinutes, task.seconds + tempSeconds)));
                }
            }, 0, 1000);
        }

        switcher = !switcher;
    }

    @FXML
    protected void onEndButtonClick() {
        if (task.seconds != 0 || tempSeconds != 0) {
            System.out.println("End Button is Pressed");
            task.seconds = 0;
            task.minutes = 0;
            task.hours = 0;
            tempSeconds = 0;
            tempMinutes = 0;
            tempHours = 0;
            timer.cancel();
            timerField.setText(String.format("%02d:%02d:%02d", 0, 0, 0));
            switcher = false;
        }
    }
}
