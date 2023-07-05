package com.example.timeapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("custom start?? //pog");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Timer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}



class ExcelDataWriter {
    static String filePath = "C:/poiexcel/Writesheet.xlsx";
    static String sheetName = " Employee Info ";

//    public static void exceler(String secs) {
//        LocalDateTime dateTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//
//        try (FileInputStream fileInputStream = new FileInputStream(filePath);
//             Workbook workbook = WorkbookFactory.create(fileInputStream)) {
//
//            Sheet sheet = workbook.getSheet(sheetName);
//
//            // Determine the row index where you want to add the new data
//            int rowIndex = sheet.getLastRowNum() + 1;
//
//            // Create a new row
//            Row newRow = sheet.createRow(rowIndex);
//
//            Cell cell2 = newRow.createCell(1);
//            cell2.setCellValue(dateTime.format(formatter));
//
//            Cell cell3 = newRow.createCell(2);
//            cell3.setCellValue(secs);
//
//            // Save the changes to the Excel file
//            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
//                workbook.write(fileOutputStream);
//            }
//
//            System.out.println("Data added successfully.");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void setGoalTextFieldExcel(String goal) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);

            // Determine the row index where you want to add the new data
            int rowIndex = sheet.getLastRowNum() + 1;

            // Create a new row
            Row newRow = sheet.createRow(rowIndex);

            // Set cell values in the new row
            Cell cell1 = newRow.createCell(0);
            cell1.setCellValue(goal);


            // Save the changes to the Excel file
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }

            System.out.println("Goal Data added successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setTimerFieldLabelExcel(String timerField) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);

            // Determine the row index where you want to add the new data
            int rowIndex = sheet.getLastRowNum() + 1;

            // Create a new row
            Row newRow = sheet.createRow(rowIndex);

            // Set cell values in the new row
            Cell cell1 = newRow.createCell(1);
            cell1.setCellValue(timerField);


            // Save the changes to the Excel file
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }

            System.out.println("Timer Data added successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getGoalTextFieldExcel() {

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

            System.out.println("Goal Data Got successfully.");

            // Return the retrieved data
            return goal;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getTimerFieldLabelExcel() {

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);

            // Get the last row with data
            int lastRowIndex = sheet.getLastRowNum();

            // Assuming the data you want to retrieve is in the last row
            Row lastRow = sheet.getRow(lastRowIndex);

            // Assuming the goal is in the first cell of the row
            Cell goalCell = lastRow.getCell(1);

            String timerField = goalCell.getStringCellValue();

            System.out.println("Timer Data Got successfully.");
            // Return the retrieved data
            return String.valueOf(timerField);



        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



}