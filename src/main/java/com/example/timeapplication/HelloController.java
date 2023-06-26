package com.example.timeapplication;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.resuable.*;

import java.util.Timer;
import java.util.TimerTask;


    public class HelloController {

        @FXML
        private Label timerField;

        @FXML
        protected void onStartButtonClick() {
            Timer timer = new Timer();
            timerLogic task = new timerLogic();

            TimerTask updateTask = new TimerTask() {
                @Override
                public void run() {
                    // Update the timerField text with the formatted time
                    task.run();
                    Platform.runLater(() -> {
                        timerField.setText(String.format("%02d:%02d:%02d", task.hours, task.minutes, task.seconds));
                    });
                }
            };

            // Set the initial text value
            timerField.setText(String.format("%02d:%02d:%02d", task.hours, task.minutes, task.seconds));

            // Schedule the updateTask to run every second
            timer.schedule(updateTask, 0, 1000);
        }

        @FXML
        protected void onEndButtonClick() {
            timerLogic timerlogic = new timerLogic();
            int i = 1;
            for (int j = 0; j < i; j++) {
                timerField.setText("timerLogic.dend()");


            }

        }
    }
