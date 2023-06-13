package com.example.timeapplication;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onStartButtonClick() {
        welcomeText.setText("code for starting the timer");
    }
    @FXML
    protected void onEndButtonClick() {
       int i = 1;
       for (int j = 0;j < i; j++){
           welcomeText.setText("code for ending the timer");
       }

    }
}