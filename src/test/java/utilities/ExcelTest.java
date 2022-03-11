package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {

    public static void main(String[] args) {

        String path="C:\\Users\\atays\\IdeaProjects\\MindtekCucumberAutomation\\src\\test\\resources\\data\\TestData.xlsx";

        // checked exception handled;

        try {
            FileInputStream input = new FileInputStream(path);   //object in order to talk to that Excel;
            Workbook book = new XSSFWorkbook(input);
            Sheet page =book.getSheet("Sheet1"); //---> telling which sheet we are  going to read;
            String name1=page.getRow(1).getCell(0).toString();//providing row index and column index
            System.out.println(name1);

            String position1=page.getRow(2).getCell(2).toString();
            System.out.println(position1);

            page.createRow(3).createCell(0).setCellValue("Kim");
            page.getRow(3).createCell(1).setCellValue("Yan");


            FileOutputStream output = new FileOutputStream(path);//object to send data to that path
            book.write(output);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ExcelUtils.readExcel("TestData","Sheet1");
        System.out.println(ExcelUtils.getValue(2,3));

        ExcelUtils.setValue(3,2,"Scrum Master");
        ExcelUtils.setValue(3,3,100000);




    }

}
