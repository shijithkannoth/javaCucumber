package cucumberJava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
    WebDriver driver;
    public Dashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="twotabsearchtextbox")
    WebElement searchBar;

    @FindBy(id="a-autoid-1-announce")
    WebElement firstAddBasket;

    @FindBy(id="nav-search-submit-button")
    WebElement searchButton;

    @FindBy(id="nav-cart-text-container")
    WebElement basket;



    public void enterSearchBar(String product){
        searchBar.sendKeys(product);
        searchButton.click();
    }

    public void addToBasket(){
        firstAddBasket.click();
    }

    public void goToBasket(){
        basket.click();
    }


}
