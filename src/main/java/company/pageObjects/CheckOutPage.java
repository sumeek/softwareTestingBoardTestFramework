package company.pageObjects;

import company.abstractComponent.AbstractComponent;
import company.utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends AbstractComponent {

    public CheckOutPage(WebDriver driver) {

        super(driver);
    }

    public PaymentsPage addShippingAddress() {

        Utilities utilities = new Utilities();

        String randomEmail = utilities.generateRandomText();

        driver.findElement(By.cssSelector("#customer-email-fieldset input[type='email']")).sendKeys(randomEmail + "@testng.com");

        waitForVisibilityOf(driver.findElement(By.cssSelector("#customer-email-fieldset input[type='email'] ")));

        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("test");

        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("test");

        driver.findElement(By.cssSelector("input[name='street[0]']")).sendKeys("test");

        driver.findElement(By.cssSelector("input[name='telephone']")).sendKeys("9190959697");

        WebElement country = driver.findElement(By.cssSelector("select[name='country_id']"));
        Select select1 = new Select(country);
        select1.selectByValue("IN");


        WebElement state = driver.findElement(By.cssSelector("select[name='region_id']"));
        Select select2 = new Select(state);
        select2.selectByValue("553");

        driver.findElement(By.cssSelector("input[name='postcode']")).sendKeys("411045");

        driver.findElement(By.cssSelector("input[name='city']")).sendKeys("test");

        waitForInvisibilityOf(driver.findElement(By.xpath("//td[@id='label_method_bestway_tablerate']")));

        return proceedToPaymentWithoutAddress();

    }

    public PaymentsPage proceedToPaymentWithoutAddress(){

        driver.findElement(By.cssSelector("span[data-bind*='Next']")).click();

        waitForInvisibilityOf(driver.findElement(By.cssSelector(".loading-mask")));

        PaymentsPage paymentsPage = new PaymentsPage(driver);

        return paymentsPage;

    }

}
