package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
         static  Sheet sheet;
         static String path;
         static Workbook book;
        /*
        .readExcel(name,sheetName);--which excel and sheet we gonna read
        .getValue(row,cell);  -- which value we need to get
        .setValue(row,cell);  --  to set value
         */

    public static void readExcel(String name, String sheetName){
        path="C:\\Users\\atays\\IdeaProjects\\MindtekCucumberAutomation\\src\\test\\resources\\data\\"+name+".xlsx";

        try {
            FileInputStream input = new FileInputStream(path);
            book = new XSSFWorkbook(input);
            sheet = book.getSheet(sheetName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(int row, int cell){
         return sheet.getRow(row).getCell(cell);

    }

       public static void setValue(int row, int cell, String value){
        int numberOfRows=sheet.getPhysicalNumberOfRows();
        if(numberOfRows>row){
            sheet.getRow(row).createCell(cell).setCellValue(value);
        }else{
            sheet.createRow(row).createCell(cell).setCellValue(value);
        }
           FileOutputStream output=null;
           try {
               output= new FileOutputStream(path);
               book.write(output);
           } catch (IOException e) {
               e.printStackTrace();
           }finally {
               try {
                   output.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

       }

    public static void setValue(int row, int cell, double value){
        int numberOfRows=sheet.getPhysicalNumberOfRows();
        if(numberOfRows>row){
            sheet.getRow(row).createCell(cell).setCellValue(value);
        }else{
            sheet.createRow(row).createCell(cell).setCellValue(value);
        }
        FileOutputStream output=null;
        try {
            output= new FileOutputStream(path);
            book.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
