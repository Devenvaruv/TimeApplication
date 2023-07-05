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
    private timerLogic task;
    private boolean switcher;
    public int tempSeconds = 0;
    private int tempMinutes = 0;
    private int tempHours = 0;

    @FXML
    private Label timerField;

    @FXML
    private TextField goalTextField;

    @FXML
    protected void setGoalTextField() {
        String updatedGoal = goalTextField.getText();
        ExcelDataWriter.setGoalTextFieldExcel(updatedGoal);
    }

    @FXML
    protected void onStartButtonClick() {

        if (switcher) {
            System.out.println("here is timer.cancel located");
            tempSeconds = tempSeconds + task.seconds;
            tempMinutes = tempMinutes + task.minutes;
            tempHours = tempHours + task.hours;
            System.out.println(tempSeconds);
            ExcelDataWriter.setTimerFieldLabelExcel(String.format("%02d:%02d:%02d", task.hours + tempHours, task.minutes + tempMinutes, task.seconds + tempSeconds));

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
