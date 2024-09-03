package cucumberJava.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static cucumberJava.utils.Base.prop;

public class Login {

    /*This Class Used the Page Factory Pattern to initialise the page objects */
    /* This Class is used for creating the Webelements of Login Page and associated methods to handle the page actions*/

    WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ap_email")
    WebElement emailAddress;

    @FindBy(id = "ap_password")
    WebElement password;

    @FindBy(id = "signInSubmit")
    WebElement signIbButton;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "nav-link-accountList-nav-line-1")
    WebElement navigateToLogin;

    @FindBy(id = "sp-cc-accept")
    WebElement acceptCookies;

    @FindBy(id = "nav-link-accountList-nav-line-1")
    WebElement successLogin;

    public void enterEmail(String username) {
        emailAddress.sendKeys(username);
    }

    public void enterPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void goToLogin() {
        navigateToLogin.click();
    }

    public void navigateUrl() throws InterruptedException {
        driver.navigate().to(prop.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(1000);
        acceptCookies.click();
    }

    public void clickOnContinue() {
        continueButton.click();
    }

    public void clickSignIn() {
        signIbButton.click();
    }

    public void verifySuccessLogin() {
        String actual = successLogin.getText();
        Assert.assertTrue("Login Success with Username is not found", actual.contains("Shijith"));
    }

}
