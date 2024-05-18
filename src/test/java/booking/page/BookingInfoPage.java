package booking.page;

import booking.constants.Link;
import booking.core.BasePage;
import booking.helpers.InputHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//h4[@class='ng-binding']")
    WebElement notFoundMessage;

    public BookingInfoPage() {
        super();
        this.pageUrl = Link.CHECK_BOOKING_INFO;
        PageFactory.initElements(driver, this);
    }

    public String getErrorMessage() {
        String errorMessageText;
        try {
            errorMessageText = errorMessage.getText();
        } catch (Exception e) {
            errorMessageText = notFoundMessage.getText();
        }
        return errorMessageText;

    }


    public void searchBookingInfo(String bookingCode, String email, String phoneNumber) {
        InputHelper.sendKey(bookingCodeField, bookingCode);
        InputHelper.sendKey(emailField, email);
        InputHelper.sendKey(phoneField, phoneNumber);
        searchBtn.click();
    }
}
