package company.stepDefinitions;

import company.pageObjects.CheckOutPage;
import company.pageObjects.PaymentsPage;
import company.pageObjects.ProductSelection;
import company.pageObjects.ShoppingCart;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StepDefs {

    ProductSelection productSelection = new ProductSelection(BaseTest.driver);
    ShoppingCart shoppingCart;
    PaymentsPage paymentsPage;
    CheckOutPage checkout;
    String orderConfirmation;

    @When("I add product {string} of size {string}, color {string} and quantity {string}")
    public void iAddProductOfSizeColorAndQuantity(String product, String size, String color, String quantityRequired) {
        if (size.isEmpty() && color.isEmpty()) {
            shoppingCart = productSelection.addProduct(product, null, null, quantityRequired);
        } else {
            shoppingCart = productSelection.addProduct(product, size, color, quantityRequired);
        }
    }

    @And("I checkout the product by adding shipping address")
    public void i_checkout_the_product_by_adding_shipping_address() {
        checkout = shoppingCart.proceedToCheckout();
        paymentsPage = checkout.addShippingAddress();
    }

    @And("I go to home page")
    public void i_go_to_home_page() {
        paymentsPage.gotoHomePage();
    }

    @Then("I make sure order amount is {string}")
    public void i_make_sure_order_amount_is(String amount) {
        Assert.assertEquals(shoppingCart.orderAmount(), amount);
    }

    @And("I update quantity of item to {string}")
    public void i_update_quantity_of_item_to(String quantity) {
        shoppingCart.updateQuantity("3");
    }

    @And("I checkout the product by using existing shipping address")
    public void i_checkout_the_product_by_using_existing_shipping_address() {
        checkout = shoppingCart.proceedToCheckout();
        paymentsPage = checkout.proceedToPaymentWithoutAddress();
    }

    @And("I place the order")
    public void i_place_the_order() {
        orderConfirmation = paymentsPage.placeOrder();
    }

    @And("Make sure order is successful")
    public void make_sure_order_is_successful() {
        Assert.assertEquals(orderConfirmation, "Thank you for your purchase!");
    }


}
