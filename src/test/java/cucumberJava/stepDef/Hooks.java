package cucumberJava.stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import static cucumberJava.utils.Base.*;

public class Hooks{

    @After(order = 0)
    public void tearDown(){

        if(driver != null){
            driver.quit();
        }
    }

    @Before
    public void setup() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/java/cucumberJava/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("Chrome")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    }


    @After(order = 1)
    public void takeScreenShot(Scenario scenario){
        if(scenario.isFailed()){
            String imageName = scenario.getName();
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte [] screenshot =ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", imageName);
        }
    }
}
