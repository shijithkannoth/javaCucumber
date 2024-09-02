package cucumberJava.utils;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@smoke",
        features="src/test/java/cucumberJava/features",
        glue={"cucumberJava/stepDef"},
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports/Cucumber.json"},
        monochrome = true
)
public class Runner {
}

