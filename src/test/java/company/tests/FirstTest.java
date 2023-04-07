package company.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://magento.softwaretestingboard.com/");

        String searchTerm = "endurance";

        driver.findElement(By.id("search")).sendKeys(searchTerm);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='Search']")));

        driver.findElement(By.cssSelector("button[title='Search']")).click();

        String banner = driver.findElement(By.cssSelector(".base")).getText();

        String extractBanner = banner.split(" '")[1].split("'")[0];

        Assert.assertEquals(extractBanner.toLowerCase(), searchTerm.toLowerCase());

        List<WebElement> foundProducts = driver.findElements(By.cssSelector("li.product-item"));

        for (WebElement element : foundProducts) {

            try {

                if (element.findElement(By.cssSelector("strong.product-item-name")).getText().equals("Gwyn Endurance Tee")) {

                    element.click();
                }

            } catch (StaleElementReferenceException ignored) {

            }

        }

        String sizeLabel = "M";

        String colorLabel = "Green";

        String quantiyRequired = "4";

        WebElement size = driver.findElement(By.cssSelector(".swatch-attribute.size div[option-label='"+sizeLabel+"']"));

        size.click();

        WebElement color = driver.findElement(By.cssSelector(".swatch-attribute.color div[option-label='"+colorLabel+"']"));

        color.click();

        WebElement quantiy = driver.findElement(By.id("qty"));

        quantiy.clear();

        quantiy.sendKeys(quantiyRequired);

        WebElement addToCart = driver.findElement(By.id("product-addtocart-button"));

        addToCart.click();

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".loading-mask"))));

        WebElement counterNumber= driver.findElement(By.cssSelector(".counter-number"));

        Assert.assertEquals(counterNumber.getText(),quantiyRequired);

        counterNumber.click();

        WebElement proceedToCheckOut = driver.findElement(By.cssSelector("#top-cart-btn-checkout"));

        proceedToCheckOut.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".checkout-container"))));










    }
}


