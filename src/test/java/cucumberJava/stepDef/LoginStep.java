package cucumberJava.stepDef;

import cucumberJava.pages.Dashboard;
import cucumberJava.pages.Login;
import cucumberJava.utils.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep extends Base {
    //    WebDriver driver = null;
    Login login = new Login(driver);
    Dashboard dashboard = new Dashboard(driver);

    @Given("I am on the login page")
    public void i_am_on_the_login_page() throws InterruptedException {
        login.navigateUrl();

    }

    @When("I enter the username and password")
    public void signin_with_username_password() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Login Pge name and password");
        login.goToLogin();
        Thread.sleep(3000);
        login.enterEmail(prop.getProperty("email"));
        login.clickOnContinue();
        login.enterPassword(prop.getProperty("password"));
    }

    @When("I click on login button")
    public void i_click_on_login_button() {
        login.clickSignIn();
    }

    @Then("I should be able to login successfully")
    public void i_should_be_able_to_login_successfully() {
        login.verifySuccessLogin();
    }

    @When("I search {string} in search bar")
    public void i_search_in_search_bar(String product) {
        dashboard.enterSearchBar(product);
    }
    @When("add first product to the basket")
    public void add_first_product_to_the_basket() {
        dashboard.addToBasket();
    }


    @And("I go to the basket")
    public void iGoToTheBasket() {
        dashboard.goToBasket();
    }
}
