package booking.page;

import booking.constants.Link;
import booking.core.BasePage;
import booking.helpers.InputHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class CheckingTicketInfo extends BasePage {
    @FindBy(xpath = "//input[@ng-model='searchData.maVe']")
    WebElement ticketCodeField;

    @FindBy(xpath = "//input[@ng-model='searchData.macTau']")
    WebElement trainNumberField;

    @FindBy(xpath = "//input[@id='gaDi']")
    WebElement startStationField;

    @FindBy(xpath = "//input[@ng-model='searchData.tenGaDen']")
    WebElement endStationField;

    @FindBy(xpath = "//input[@ng-model='searchData.ngayDi']")
    WebElement startDateBtn;

    @FindBy(xpath = "//input[@ng-model='searchData.cmt']")
    WebElement cccdField;

    @FindBy(xpath = "//input[@ng-click='submitForm()']")
    WebElement searchBtn;

    @FindBy(xpath = "//div[@ng-show='isError']")
    WebElement commonErrorMessage;

    @FindBy(xpath = "//div[@ng-show='isInvalid']")
    WebElement ticketInvalidMessage;

    public CheckingTicketInfo() {
        super();
        this.pageUrl = Link.CHECK_TICKET_INFO;
        PageFactory.initElements(driver, this);
    }

    public void selectDate(String date) throws InterruptedException {
        sleep(1000);
        startDateBtn.click();
        sleep(1000);
        driver.findElement(By.xpath("//td/button/span[contains(@class, 'ng-binding') and text() = " + date + "]")).click();
    }

    public void enterTicketInfo(String ticketCode, String trainNumber, String startStation, String endStation, String startDate, String userCccd) throws InterruptedException {
        InputHelper.sendKey(ticketCodeField, ticketCode);
        InputHelper.sendKey(trainNumberField, trainNumber);
        InputHelper.quickSelect(startStationField, startStation);
        InputHelper.quickSelect(endStationField, endStation);
        selectDate(startDate);
        InputHelper.sendKey(cccdField, userCccd);
        clickSearch();
    }

    public String getErrorCommon() {
        String errorMessage = "";
        try {
            errorMessage = commonErrorMessage.getText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return errorMessage;
    }

    public void clickSearch() {
        searchBtn.click();
    }
}
