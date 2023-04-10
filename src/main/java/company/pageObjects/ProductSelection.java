package company.pageObjects;

import company.abstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductSelection extends AbstractComponent {

    public ProductSelection(WebDriver driver) {

        super(driver);

    }

    public ShoppingCart addProduct(String searchTerm, String sizeLabel, String colorLabel, String quantiyRequired) {

        searchProduct(searchTerm);

        if (driver.findElement(By.cssSelector(".product-item-link")).getText().contains(searchTerm)) {

            driver.findElement(By.cssSelector(".product-item-link")).click();
        }

        if (sizeLabel != null && colorLabel != null) {

            WebElement size = driver.findElement(By.cssSelector(".swatch-attribute.size div[option-label='" + sizeLabel + "']"));
            size.click();

            WebElement color = driver.findElement(By.cssSelector(".swatch-attribute.color div[option-label='" + colorLabel + "']"));
            color.click();

        }

        WebElement quantityEle = driver.findElement(By.id("qty"));
        quantityEle.clear();
        quantityEle.sendKeys(quantiyRequired);

        WebElement addToCart = driver.findElement(By.id("product-addtocart-button"));

        addToCart.click();

        waitForInvisibilityOf(driver.findElement(By.cssSelector(".loading-mask")));

        ShoppingCart shoppingCart = new ShoppingCart(driver);

        return shoppingCart;

    }

    public void searchProduct(String searchTerm) {

        driver.findElement(By.xpath("//input[@id='search']")).click();

        driver.findElement(By.xpath("//input[@id='search']")).sendKeys(searchTerm);

        driver.findElement(By.xpath("//input[@id='search']")).sendKeys(Keys.ENTER);


    }


    public ShoppingCart updateQuantity(String quantiyRequired) {

        WebElement quantity = driver.findElement(By.id("qty"));

        quantity.clear();

        quantity.sendKeys(String.valueOf(Integer.parseInt(quantiyRequired) - 1));

        driver.findElement(By.id("product-updatecart-button")).click();

        ShoppingCart shoppingCart = new ShoppingCart(driver);

        return shoppingCart;


    }

    public ShoppingCart gotoShoppingCart() {

        waitForVisibilityOf(driver.findElement(By.linkText("shopping cart")));

        driver.findElement(By.linkText("shopping cart")).click();

        ShoppingCart shoppingCart = new ShoppingCart(driver);

        return shoppingCart;

    }

}
