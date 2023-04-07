package company.pageObjects;

import company.abstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentsPage extends AbstractComponent {

    public PaymentsPage(WebDriver driver) {

        super(driver);
    }

    public String placeOrder() {

        waitForInvisibilityOf(driver.findElement(By.cssSelector(".loading-mask")));

        driver.findElement(By.xpath("//span[normalize-space()='Place Order']")).click();

        waitForInvisibilityOf(driver.findElement(By.cssSelector(".loading-mask")));

        return orderConfirmation();

    }

    public String orderConfirmation() {

        return driver.findElement(By.cssSelector(".page-title")).getText();

    }


}
