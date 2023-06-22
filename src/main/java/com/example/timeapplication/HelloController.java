package com.example.timeapplication;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.resuable.*;

public class HelloController {

    @FXML
    private Label timerField;

    @FXML
    protected void onStartButtonClick() {
        timerLogic timerlogic = new timerLogic();
            timerField.setText(String.valueOf(timerlogic.getHours()) + ":" + String.valueOf(timerlogic.getMinutes()) + ":" + String.valueOf(timerlogic.getSeconds()));
            timerLogic.start();

    }
    @FXML
    protected void onEndButtonClick() {
        timerLogic timerlogic = new timerLogic();
       int i = 1;
       for (int j = 0;j < i; j++){
           timerField.setText("timerLogic.end()");
           timerlogic.end();

       }

    }
}