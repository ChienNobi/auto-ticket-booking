package booking.helpers;

import booking.configs.BrowserConfig;
import org.openqa.selenium.*;

import static java.lang.Thread.sleep;

public class InputHelper {
    private static WebDriver driver = BrowserConfig.getDriver();
    public static JavascriptExecutor js;

    public static void sendKey(WebElement element, String key)  {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(key);
    }

    public static void quickSelect(WebElement element, String value) throws InterruptedException {
        InputHelper.sendKey(element, value);
        sleep(1000);
        // TODO: add wait function
        driver.findElement(By.xpath("//a[@class='ng-scope ng-binding']")).click();
    }
}


