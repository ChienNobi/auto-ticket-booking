package booking.page;

import booking.constants.Link;
import booking.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReturnTicketPage extends BasePage {
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

    @FindBy(xpath = "//input[contains(@style, '25px')]")
    WebElement firstCheckBox;

    @FindBy(xpath = "//div[@class='adv-right']")
    WebElement continueBtn;

    @FindBy(xpath = "//button[@ng-click='newRequestTraVe()']")
    WebElement confirmBtn;

    public ReturnTicketPage() {
        super();
        this.pageUrl = Link.RETURN_TICKET;
        PageFactory.initElements(driver, this);
    }
}
