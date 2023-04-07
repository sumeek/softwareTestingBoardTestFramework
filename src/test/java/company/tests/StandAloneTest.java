package company.tests;
import company.pageObjects.CheckOutPage;
import company.pageObjects.PaymentsPage;
import company.pageObjects.ShoppingCart;
import company.testComponent.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest {

    @Test(groups = {"workflow"})

    public void shopping() throws InterruptedException {

        //Add Product
        ShoppingCart shoppingCart= productSelection.addProduct("Gwyn Endurance Tee", "M", "Green", "4");

        //Proceed to Checkout
        CheckOutPage checkout = shoppingCart.proceedToCheckout();

        //Add Shipping Address
        PaymentsPage paymentsPage = checkout.addShippingAddress();

        //Goto homePage again
        paymentsPage.gotoHomePage();

        //Assertion
        Assert.assertEquals(shoppingCart.orderAmount(),"92.00");

        //Update Quantity
        shoppingCart.updateQuantity("3");

        //Add new product
        productSelection.addProduct("Gwyn Endurance Tee","S", "Yellow", "1");

        //Add another product
        productSelection.addProduct("Quest Lumaflex™ Band","1");

        //Assertion
        Assert.assertEquals(shoppingCart.orderAmount(),"140.00");

        //Proceed to Checkout
        checkout= shoppingCart.proceedToCheckout();
        paymentsPage = checkout.proceedToPaymentWithoutAddress();

        //Place Order
        String orderConfirmation= paymentsPage.placeOrder();

        //Assertion
        Assert.assertEquals(orderConfirmation,"Thank you for your purchase!");

   }
}



