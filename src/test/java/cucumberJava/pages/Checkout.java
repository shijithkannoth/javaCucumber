package cucumberJava.pages;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Checkout {
    /*This Class Used the Page Factory Pattern to initialise the page objects */
    /* This Class is used for creating the Webelements of Checkout Page and associated methods to handle the page actions*/

    WebDriver driver;
    public Checkout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "proceedToRetailCheckout")
    WebElement proceedToCheckout;

    @FindBy(xpath = "//input[@*='Address_selectShipToThisAddress']")
    WebElement userThisAddress;

    @FindBy(xpath = "//a[text()='Add a credit or debit card']")
    WebElement addNewCard;

    @FindBy(name = "addCreditCardNumber")
    WebElement cardNumber;

    @FindBy(name = "ppw-accountHolderName")
    WebElement nameOnCard;

    @FindBy(name="ppw-expirationDate_month")
    WebElement cardMonth;

    @FindBy(xpath = "ppw-expirationDate_year")
    WebElement cardYear;

    @FindBy(name="addCreditCardVerificationNumber")
    WebElement cvv;

    @FindBy(name="ppw-widgetEvent:AddCreditCardEvent")
    WebElement addCard;

    @FindBy(xpath = "//*[@class='a-box a-alert a-alert-error']")
    WebElement errorContainer;

    @FindBy(id="nav-cart-count")
    WebElement basketCount;

    @FindBy(id="sc-subtotal-label-buybox")
    WebElement totalItems;

    @FindBy(className = "a-dropdown-prompt")
    List<WebElement> itemsInList;

    @FindBy(name = "quantity")
    List<WebElement> checkoutCount;

    public void clickProceedCheckout(){
        proceedToCheckout.click();
    }

    public void useAddress(){
        userThisAddress.click();
    }

    public void addNewCard(){
        addNewCard.click();
    }

    public void enterCardNumber(String number){
        cardNumber.clear();
        cardNumber.sendKeys(number);
    }

    public void enterCardName(String name){
        nameOnCard.sendKeys(name);
    }

    public void confirmNewCard(){
        addCard.click();
    }


    public void selectMonth(String month) throws InterruptedException {
        Select select = new Select(cardMonth);
        select.selectByVisibleText(month);
        Thread.sleep(1000);
    }

    public void selectYear() throws InterruptedException {
        Select select = new Select(cardYear);
        select.selectByVisibleText("2026");
        Thread.sleep(1000);
    }

    public void enterCvv(String cv) throws InterruptedException {
        cvv.sendKeys(cv);
        Thread.sleep(3000);
    }

    public String getErrorContainer(){
        String errorMessage = errorContainer.getText();
        return  errorMessage;
    }

    public int basketItem(){
        return Integer.parseInt(basketCount.getText());
    }

    public WebElement basket(){
        return basketCount;
    }

    public int subTotal(){
        String totalString = totalItems.getText();
        int total = Integer.parseInt(totalString.split(" ")[1].split("")[1]);
        return total;
    }

    public int totalFromList(){
        int total = 0;
        for(WebElement elm : itemsInList){
            total = total + Integer.parseInt(elm.getText());
        }
        return total;
    }

    public void updateQuantity(){
        WebElement dropdown = checkoutCount.get(0);
        Select select = new Select(dropdown);
        Random random = new Random();
        int number = random.nextInt(9);
        select.selectByValue(String.valueOf(number));
    }

}
