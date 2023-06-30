package com.example.timeapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.IOException;
import java.time.LocalDateTime;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("custom start?? //pog");
        ExcelDataWriter.exceler(0);
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
    static HelloController controllers = new HelloController();

    public static void exceler(int secs) {
        String filePath = "C:/poiexcel/Writesheet.xlsx";
        String sheetName = " Employee Info ";

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);

            // Determine the row index where you want to add the new data
            int rowIndex = sheet.getLastRowNum() + 1;

            // Create a new row
            Row newRow = sheet.createRow(rowIndex);

            // Set cell values in the new row
            Cell cell1 = newRow.createCell(0);
            cell1.setCellValue(LocalDateTime.now());

            Cell cell2 = newRow.createCell(1);
            cell2.setCellValue(secs);

            Cell cell3 = newRow.createCell(2);
            cell3.setCellValue("Value 322");

            // Save the changes to the Excel file
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }

            System.out.println("Data added successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}