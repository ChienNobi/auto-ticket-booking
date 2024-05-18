package booking.core;

import booking.configs.BrowserConfig;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public WebDriver driver;
    public String pageUrl;

    public BasePage() {
        this.driver = BrowserConfig.getDriver();
    }

    public void clickAcceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void clickDismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void openPage() {
        driver.get(this.pageUrl);
    }

    public void refreshPage() {
        openPage();
        driver.navigate().refresh();
    }
}
