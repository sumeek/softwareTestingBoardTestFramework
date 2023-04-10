package company.abstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    public WebDriver driver;
    public WebDriverWait wait;


    public AbstractComponent(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void gotoHomePage() {

        WebElement logo = driver.findElement(By.cssSelector(".logo"));

        logo.click();

        waitForElementToBeClickable(driver.findElement(By.cssSelector(".navigation")));

    }


    public void waitForElementToBeClickable(WebElement webElement) {

        wait.until(ExpectedConditions.elementToBeClickable(webElement));

    }


    public void waitForInvisibilityOf(WebElement webElement) {

        wait.until(ExpectedConditions.invisibilityOf(webElement));

    }

    public void waitForVisibilityOf(WebElement webElement) {

        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitTilljsReturnsValue(String script){

        wait.until(ExpectedConditions.jsReturnsValue(script));

    }

    public void waitTillPresenceOfElementLocated(By locator){

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));


    }

}
