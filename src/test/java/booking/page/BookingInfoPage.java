package booking.page;

import booking.constants.Link;
import booking.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingInfoPage extends BasePage {
    @FindBy(xpath = "//input[@ng-model='bookingCode']")
    WebElement bookingCodeField;

    @FindBy(xpath = "//input[@ng-model='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@ng-model='mobile']")
    WebElement phoneField;

    @FindBy(xpath = "//a[contains(@class, 'btn-primary')]")
    WebElement searchBtn;

    @FindBy(xpath = "//span[@ng-show='bookingCodeError' and not(contains(@class, 'ng-hide'))]")
    WebElement errorMessage;

    public BookingInfoPage() {
        super();
    }

    public void openPage() {
        driver.get(Link.CHECK_BOOKING_INFO);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
