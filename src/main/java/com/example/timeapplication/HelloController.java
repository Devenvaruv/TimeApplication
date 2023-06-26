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



        @FXML
        private Label timerField;

        @FXML
        protected void onStartButtonClick() {
            if (switcher) {
                // Pause the timer
                timer.cancel();
            } else {
                // Start or resume the timer
                timer = new Timer();
                task = new timerLogic();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        task.runner();
                        Platform.runLater(() -> {
                            timerField.setText(String.format("%02d:%02d:%02d", task.hours, task.minutes, task.seconds));
                        });
                    }
                }, 0, 1000);
            }

            switcher = !switcher;
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
