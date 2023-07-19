package com.example.timeapplication;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        seconds = Integer.parseInt(Objects.requireNonNull(ExcelDataWriter.getTimerFieldLabelExcel()).substring(6,8));
        minutes = Integer.parseInt(ExcelDataWriter.getTimerFieldLabelExcel().substring(3,5));
        hours = Integer.parseInt(ExcelDataWriter.getTimerFieldLabelExcel().substring(0,2));
        onStartButtonClick();

        if(ExcelDataWriter.getGoalTextFieldExcel() != null) {
            goalTextField.setText(ExcelDataWriter.getGoalTextFieldExcel());
        }else {
            goalTextField.setText("");
        }

        if(ExcelDataWriter.getTimerFieldLabelExcel() != null) {
            timerField.setText(String.valueOf(ExcelDataWriter.getTimerFieldLabelExcel()));
        }else {
            timerField.setText("00:00:00");
        }
    }

    private Timer timer;
    private boolean switcher = true;
    public static int seconds = 0; //I need to figure out how to send the current second count when the program is closed.
    public static int minutes = 0;// Done made them static
    public static int hours = 0;

    @FXML
    private Label timerField;

    @FXML
    private TextField goalTextField;

    @FXML
    protected void setGoalTextField() {
        String updatedGoal = goalTextField.getText();
        ExcelDataWriter.setBothFieldLabelExcel(ExcelDataWriter.getTimerFieldLabelExcel(), updatedGoal);
    }

    @FXML
    protected void onStartButtonClick() {

        if (switcher) {
            System.out.println("here clock is started");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    myTimer();
                    Platform.runLater(() -> timerField.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds)));
                }
            }, 0, 1000);
        }
        if (!switcher) {
            System.out.println("here clock is paused");
            System.out.println(seconds);
            ExcelDataWriter.setBothFieldLabelExcel(String.format("%02d:%02d:%02d",hours,minutes,seconds), ExcelDataWriter.getGoalTextFieldExcel());
            // Pause the timer
            timer.cancel();
        }

        switcher = !switcher;

    }

    @FXML
    protected void onEndButtonClick() {

        if (seconds != 0) {
            System.out.println("End Button is Pressed");
            seconds = 0;
            minutes = 0;
            hours = 0;
            timer.cancel();
            timerField.setText(String.format("%02d:%02d:%02d", 0, 0, 0));
            switcher = true;
        }
    }

    public void myTimer() {
        if (seconds >= 59) {
            if (minutes >= 59) {
                hours++;
                minutes = 0;
            }
            minutes++;
            seconds = 0;
            if (hours >= 23) {
                hours = 0;
            }
        }
        seconds++;
    }

    public void saveOnClose() {
        ExcelDataWriter.setBothFieldLabelExcel(String.format("%02d:%02d:%02d",hours,minutes,seconds), ExcelDataWriter.getGoalTextFieldExcel());
        System.out.println(seconds);
    }

}
