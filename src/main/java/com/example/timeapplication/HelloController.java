package com.example.timeapplication;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.resuable.*;
import javafx.scene.control.TextField;

import java.util.Timer;
import java.util.TimerTask;

import java.io.File;
import java.io.FileOutputStream;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class HelloController {
    private Timer timer;
    private timerLogic task;
    private boolean switcher;
    private int tempSeconds = 0;
    private int tempMinutes = 0;
    private int tempHours = 0;

    @FXML
    private TextField goalTextField;

    @FXML
    protected void forGoalTextField() {
        goalTextField.setText("hello CCOnasdfonsdoignasofbioasnfnioabs");
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

public class WriteDataToExcel {
    public static void main(String[] args) throws Exception {

        //Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet spreadsheet = workbook.createSheet( " Employee Info ");

        //Create row object
        XSSFRow row;

        //This data needs to be written (Object[])
        Map < String, Object[] > empinfo = new TreeMap < String, Object[] >();
        empinfo.put( "1", new Object[] {
                "EMP ID", "EMP NAME", "DESIGNATION" });

        empinfo.put( "2", new Object[] {
                "tp01", "Gopal", "Technical Manager" });

        empinfo.put( "3", new Object[] {
                "tp02", "Manisha", "Proof Reader" });

        empinfo.put( "4", new Object[] {
                "tp03", "Masthan", "Technical Writer" });

        empinfo.put( "5", new Object[] {
                "tp04", "Satish", "Technical Writer" });

        empinfo.put( "6", new Object[] {
                "tp05", "Krishna", "Technical Writer" });

        //Iterate over data and write to sheet
        Set < String > keyid = empinfo.keySet();
        int rowid = 0;

        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object [] objectArr = empinfo.get(key);
            int cellid = 0;

            for (Object obj : objectArr){
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }
        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(
                new File("C:/poiexcel/Writesheet.xlsx"));

        workbook.write(out);
        out.close();
        System.out.println("Writesheet.xlsx written successfully");
    }
}