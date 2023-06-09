package company.pageObjects;

import company.abstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ShoppingCart extends AbstractComponent {

    public ShoppingCart(WebDriver driver) {

        super(driver);

    }

    public void openCart()  {

        waitTillPresenceOfElementLocated(By.xpath("//div[@class='minicart-wrapper']"));

        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='minicart-wrapper']"))).build().perform();

        actions.click().build().perform();

    }

    public ProductSelection updateCartEditItem()  {

        openCart();

        driver.findElement(By.cssSelector("a[title*='Edit item']")).click();

        ProductSelection productSelection = new ProductSelection(driver);

        return productSelection;

    }

    public void updateQuantity(String updatedQuantiy)  {

        openCart();

        WebElement quantity = driver.findElement(By.cssSelector(".item-qty.cart-item-qty"));

        quantity.sendKeys(Keys.CONTROL + "a");

        quantity.sendKeys(Keys.DELETE);

        quantity.sendKeys(updatedQuantiy);

        driver.findElement(By.cssSelector("button[title='Update']")).click();


    }

    public void viewAndEditCart()  {

        openCart();

        driver.findElement(By.cssSelector(".action.viewcart")).click();

        waitForVisibilityOf(driver.findElement(By.xpath("//td[@data-th='Order Total']")));

    }

    public void updateDeletetItem() {

        // Not implemented
    }

    public String viewCartAmount(){

        openCart();

        return driver.findElement(By.cssSelector(".subtotal .price")).getText().split("\\$")[1];

    }


    public CheckOutPage proceedToCheckout()  {

        openCart();

        driver.findElement(By.cssSelector(".action.primary.checkout")).click();

        waitForVisibilityOf(driver.findElement(By.cssSelector("#shipping")));

        CheckOutPage checkOutPage = new CheckOutPage(driver);

        return checkOutPage;

    }

    public String orderAmount()  {

        openCart();

        waitTillPresenceOfElementLocated(By.cssSelector(".action.viewcart"));

        driver.findElement(By.cssSelector(".action.viewcart")).click();

        waitForVisibilityOf(driver.findElement(By.xpath("//td[@data-th='Order Total']")));

        return driver.findElement(By.xpath("//td[@data-th='Order Total']")).getText().split("\\$")[1];

    }

}
