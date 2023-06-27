package com.example.timeapplication;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.resuable.*;

import java.util.Timer;
import java.util.TimerTask;


    public class HelloController {
        private Timer timer;
        private timerLogic task;
        private boolean switcher;
        private int tempSeconds = 0;
        private int tempMinutes = 0;
        private int tempHours = 0;



        @FXML
        private Label timerField;

        @FXML
        protected void onStartButtonClick() {
            if (switcher) {
                System.out.println("here is timer.cancle located" + switcher);
                tempSeconds = tempSeconds + task.seconds;
                tempMinutes = tempMinutes + task.minutes;
                tempHours = tempHours + task.hours;
                // Pause the timer
                timer.cancel();
            } else {
                // Start or resume the timer
                System.out.println("here is timer resume/start located" + switcher);
                timer = new Timer();
                task = new timerLogic();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        task.runner();
                        Platform.runLater(() -> {
                            timerField.setText(String.format("%02d:%02d:%02d", task.hours, task.minutes, task.seconds + tempSeconds));
                        });
                    }
                }, 0, 1000);
            }

            switcher = !switcher;
        }

        @FXML
        protected void onEndButtonClick() {
           timer.cancel();

        }
    }
