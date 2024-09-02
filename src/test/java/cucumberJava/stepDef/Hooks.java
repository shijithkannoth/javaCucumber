package cucumberJava.stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static cucumberJava.utils.Base.driver;

public class Hooks{

    @After(order = 0)
    public void tearDown(){

        if(driver != null){
            driver.quit();
            System.out.println("I am inside the After All");
        }
    }

    @Before
    public void setup(){
        System.out.println("<<> DONE HERE >>>>>>>");
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
