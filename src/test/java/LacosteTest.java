import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LacosteTest {
    private static WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void testAmountOfAddedItemsToCart()  throws InterruptedException {
        webDriver.get("https://www.lacoste.pl/en/bh2153/53s-1/");
        String EXPECTED_AMOUNT = "1";

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button[@class='cookie-consent-accept js-cookie-consent-accept']"))).click();

        WebElement openSelectSize = webDriver.findElement(By.xpath(
                "//button[@class='lc-button lc-button--wide md-px-0 js-sidepopup-trigger' and @data-target='product-sizes']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(openSelectSize));
        openSelectSize.click();

        WebElement buttonSize = webDriver.findElement(By.xpath(
                "//a[@data-value='T48']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonSize));
        buttonSize.click();

        Thread.sleep(3000);

        WebElement buttonAddToCart = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='product-button-container l-pt-1']")));
        buttonAddToCart.click();

        WebElement amountOfProductsInCart = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//span[@class='basket-icon-badge js-total-quantity']")));

        Assert.assertEquals(amountOfProductsInCart.getText(), EXPECTED_AMOUNT);
    }

     @Test
     public void testCheckingSize()  throws InterruptedException {
         webDriver.get("https://www.lacoste.pl/en/bh2153/53s-1");
         String EXPECTED_COLOR_SIZE = "BLACK, Size: 48";

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button[@class='cookie-consent-accept js-cookie-consent-accept']"))).click();

        WebElement openSelectSize = webDriver.findElement(By.xpath(
                "//button[@class='lc-button lc-button--wide md-px-0 js-sidepopup-trigger' and @data-target='product-sizes']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(openSelectSize));
        openSelectSize.click();

        WebElement buttonSize = webDriver.findElement(By.xpath(
                "//a[@data-value='T48']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonSize));
        buttonSize.click();

        Thread.sleep(3000);

        WebElement buttonAddToCart = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='product-button-container l-pt-1']")));
        buttonAddToCart.click();

         WebElement buttonOpenCart = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                 "//span[@class='basket-icon-wrapper']")));
         buttonOpenCart.click();

         //ХМММ, как сюда вписать размер
         WebElement sizeOfItem = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                 "//div[@class='option']")));

         Assert.assertEquals(sizeOfItem.getText(), EXPECTED_COLOR_SIZE);
     }
}
