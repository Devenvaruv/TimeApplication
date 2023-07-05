package com.example.timeapplication;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {


    @FXML
    private TextField goalTextField;

    @FXML
    public Label test1 = new Label();

    @FXML
    public void forTest1(){
        test1.setText("what the fuck did you say to me");
    }




    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("custom start?? //pog");

        //ExcelDataWriter.retrieveDataFromExcel();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Timer");
        stage.setScene(scene);
        stage.show();

        //ExcelDataWriter.exceler("0");
//        if(ExcelDataWriter.retrieveDataFromExcel() != null){
//            HelloController hell = new HelloController();
//            //hell.forGoalTextField();
//
//            forTest1();
//
//            System.out.println(ExcelDataWriter.retrieveDataFromExcel());
//        }
    }

    public static void main(String[] args) {
        launch();

    }
}

class ExcelDataWriter {

    static HelloController hellocontroller = new HelloController();


    @FXML
    public static TextField goalTextField;

    public static void exceler(String secs) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String filePath = "C:/poiexcel/Writesheet.xlsx";
        String sheetName = " Employee Info ";
        //hellocontroller.forGoalTextField();

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);

            // Determine the row index where you want to add the new data
            int rowIndex = sheet.getLastRowNum() + 1;

            // Create a new row
            Row newRow = sheet.createRow(rowIndex);

            // Set cell values in the new row
            Cell cell1 = newRow.createCell(0);
            cell1.setCellValue("Goal:1");


            Cell cell2 = newRow.createCell(1);
            cell2.setCellValue(dateTime.format(formatter));

            Cell cell3 = newRow.createCell(2);
            cell3.setCellValue(secs);

            // Save the changes to the Excel file
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }

            System.out.println("Data added successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String retrieveDataFromExcel() {
        String filePath = "C:/poiexcel/Writesheet.xlsx";
        String sheetName = " Employee Info ";

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);

            // Get the last row with data
            int lastRowIndex = sheet.getLastRowNum();

            // Assuming the data you want to retrieve is in the last row
            Row lastRow = sheet.getRow(lastRowIndex);

            // Assuming the goal is in the first cell of the row
            Cell goalCell = lastRow.getCell(0);
            String goal = goalCell.getStringCellValue();

            // Assuming the time is in the second cell of the row
            Cell timeCell = lastRow.getCell(1);
            String time = timeCell.getStringCellValue();

            // Assuming the secs value is in the third cell of the row
            Cell secsCell = lastRow.getCell(2);
            String secs = secsCell.getStringCellValue();

            // Return the retrieved data
            return "Goal: " + goal + ", Time: " + time + ", Secs: " + secs;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}