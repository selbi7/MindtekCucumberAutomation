package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    /**
     * Method will set up driver and return it.
     */
    static WebDriver driver;
    public static WebDriver getDriver(){
        String driverType=ConfigReader.getProperty("browser");
        if(driver==null||((RemoteWebDriver)driver).getSessionId()==null){
            if(driverType.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
            }else if(driverType.equalsIgnoreCase("edge")){
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
            }else if(driverType.equalsIgnoreCase("ie")){
                WebDriverManager.iedriver().setup();
                driver=new InternetExplorerDriver();
            }

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }

}
