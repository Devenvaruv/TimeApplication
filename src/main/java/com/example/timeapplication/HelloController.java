package com.example.timeapplication;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.example.resuable.*;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tempSeconds = Integer.parseInt(ExcelDataWriter.getTimerFieldLabelExcel().substring(6,8));
        if(ExcelDataWriter.getGoalTextFieldExcel() != null) {
            goalTextField.setText(ExcelDataWriter.getGoalTextFieldExcel());
        }else {
            goalTextField.setText("");
        }
        if(ExcelDataWriter.getGoalTextFieldExcel() != null) {
            timerField.setText(String.valueOf(ExcelDataWriter.getTimerFieldLabelExcel()));
        }else {
            timerField.setText("00:00:00");
        }
    }

    private Timer timer;
    private boolean switcher;
    public int tempSeconds;
    public int tempMinutes = 0;
    public int tempHours = 0;
    public int seconds = 0;
    public int minutes = 0;
    public int hours = 0;

    @FXML
    private Label timerField;

    @FXML
    private TextField goalTextField;

    @FXML
    protected void setGoalTextField() {
        String updatedGoal = goalTextField.getText();
        //ExcelDataWriter.setGoalTextFieldExcel(updatedGoal);
        ExcelDataWriter.setBothFieldLabelExcel(ExcelDataWriter.getTimerFieldLabelExcel(), updatedGoal);
    }

    @FXML
    protected void onStartButtonClick() {

        if (switcher) {
            System.out.println("here is timer.cancel located");
            tempSeconds = tempSeconds + seconds;
            tempMinutes = tempMinutes + minutes;
            tempHours = tempHours + hours;
            System.out.println(tempSeconds);
            //ExcelDataWriter.setTimerFieldLabelExcel(String.format("%02d:%02d:%02d",tempHours,tempMinutes,tempSeconds));
            ExcelDataWriter.setBothFieldLabelExcel(String.format("%02d:%02d:%02d",tempHours,tempMinutes,tempSeconds), ExcelDataWriter.getGoalTextFieldExcel());

            // Pause the timer
            timer.cancel();
        }

        if (!switcher) {
            System.out.println("here is timer resume/startin located");
            if(tempSeconds >= 60){
                tempSeconds = 0;
                if(seconds != 0){
                    seconds = 0;
                }
                tempMinutes++;
            }
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    myTimer();
                    Platform.runLater(() -> timerField.setText(String.format("%02d:%02d:%02d", hours + tempHours, minutes + tempMinutes, seconds + tempSeconds)));
                }
            }, 0, 1000);
        }

        switcher = !switcher;
    }

    @FXML
    protected void onEndButtonClick() {

        if (seconds != 0 || tempSeconds != 0) {
            System.out.println("End Button is Pressed");
            seconds = 0;
            minutes = 0;
            hours = 0;
            tempSeconds = 0;
            tempMinutes = 0;
            tempHours = 0;
            timer.cancel();
            timerField.setText(String.format("%02d:%02d:%02d", 0, 0, 0));
            switcher = false;
        }
    }

    public void myTimer() {
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
