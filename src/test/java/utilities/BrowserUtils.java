package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class BrowserUtils {

     public static void scroll(int pixels){

        // This method will croll page
         WebDriver driver=Driver.getDriver();
         pixels=5800;
         JavascriptExecutor js=((JavascriptExecutor)driver);
         js.executeScript("window.scrollBy(0,"+pixels+")");
     }
     //Method for HoverOver
     public static void hoverOver(WebElement element){
         Actions actions = new Actions(Driver.getDriver());
         actions.moveToElement(element).perform();
     }

     public static void selectDropdownByValue(WebElement element,String value){
         Select select=new Select(element);
         select.selectByValue(value);
     }
    public static void selectByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /**
     * This method will explicitly wait for element to be visible;
     * @param element
     */
     public static void waitElementToBeVisible(WebElement element){
         WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);
         wait.until(ExpectedConditions.visibilityOf(element));
     }
    public static void waitElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void takeScreenshot(String name) throws IOException {
         WebDriver driver=Driver.getDriver();
         File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         String path=System.getProperty("user.dir")+"\\src\\test\\recources\\screenshots/"+name+System.currentTimeMillis()+".png";
         File file=new File(path);
        FileUtils.copyFile(screenshot,file);
     }

}
