package cucumberJava.stepDef;

import cucumberJava.pages.Checkout;
import cucumberJava.pages.Dashboard;
import cucumberJava.pages.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static cucumberJava.utils.Base.driver;

public class CheckoutStep {

    Login login = new Login(driver);
    Dashboard dashboard = new Dashboard(driver);
    Checkout checkout = new Checkout(driver);

    @When("I click on proceed to checkout")
    public void i_click_on_proceed_to_checkout() throws InterruptedException {
        checkout.clickProceedCheckout();
        Thread.sleep(2000);
    }
    @When("select the existing address from the saved list")
    public void select_the_existing_address_from_the_saved_list() {
        checkout.useAddress();
    }
    @When("I select payment card method")
    public void i_select_payment_card_method() {
        checkout.addNewCard();
    }

    @When("I enter {string} {string} {string}")
    public void i_enter(String string, String string2, String string3) throws InterruptedException {
        checkout.enterCardNumber(string);
        checkout.enterCardName(string2);
        checkout.enterCvv(string3);
        checkout.selectMonth("12");
//        checkout.selectYear();

    }

    @When("I validate the card details")
    public void i_validate_the_card_details() throws InterruptedException {
        checkout.confirmNewCard();
        Thread.sleep(4000);
    }

    @Then("I should see {string} messages")
    public void iShouldSeeMessages(String error) {

    }

    @And("I validate the card with below data")
    public void iValidateTheCardWithBelowData(DataTable dataTable) throws InterruptedException {
        driver.switchTo().frame(0);
        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> user : users) {
            String cardNumber = user.get("card-number");
            String name = user.get("name");
            String cvv = user.get("cvv");
            String error = user.get("error");
            String month = user.get("month");
            checkout.enterCardNumber(user.get("card-number"));
            checkout.enterCardName(user.get("name"));
            checkout.enterCvv(user.get("cvv"));
            checkout.selectMonth(user.get("month"));
            checkout.confirmNewCard();
            Thread.sleep(4000);
            String message = checkout.getErrorContainer();
            System.out.println(message);
            Assert.assertTrue(message.contains(user.get("error")));
        }
    }

    @Then("I check if basket is empty")
    public void i_check_if_basket_is_empty(){
        if(checkout.basketItem()>=1){
            checkout.basket().click();
        } else {
            dashboard.enterSearchBar("Badmition");
            dashboard.addToBasket();
            checkout.basket().click();
        }
    }

    /* This method is to validate the Number of Items added in the Basket and
    verify the same with Count of Number of Items in the List*/
    public void validateSubTotal(){
        int subTotal = checkout.subTotal();
        int totalList = checkout.totalFromList();
        Assert.assertTrue("Total Does not match", subTotal == totalList );
    }

    @When("I go to Basket and verify the product added")
    public void i_go_to_basket_and_verify_the_product_added() {
        dashboard.goToBasket();
        validateSubTotal();

    }

    @Then("I modify the number of Items")
    public void iModifyTheNumberOfItems() throws InterruptedException {
        checkout.updateQuantity();
        Thread.sleep(5000);
    }

    @Then("I verify the updated items in the checkout page")
    public void iVerifyTheUpdatedItemsInTheCheckoutPage() {
        validateSubTotal();
    }
}
