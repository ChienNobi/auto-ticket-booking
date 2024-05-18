package booking.page;

import booking.constants.Link;
import booking.core.BasePage;
import booking.helpers.InputHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class TrainStartTimePage extends BasePage {
    @FindBy(xpath = "//*[@id='txtgadi']")
    WebElement startStationField;

    @FindBy(xpath = "//input[@ng-model='gaden.TenGa']")
    WebElement destinationStationField;

    @FindBy(xpath = "//input[@type='datetime']")
    WebElement timeStartField;

    @FindBy(xpath = "//div[@ng-show='!isLoading']")
    WebElement noResultMessage;


    public TrainStartTimePage() {
        super();
        this.pageUrl = Link.TRAIN_START_TIME;
        PageFactory.initElements(driver, this);
    }

    public void enterData(String startStation, String destinationStation, String timeStart) throws InterruptedException {
        InputHelper.quickSelect(startStationField, startStation);
        sleep(1000);
        InputHelper.quickSelect(destinationStationField, destinationStation);
        sleep(1000);
        timeStartField.click();
        sleep(1000);
        WebElement btn = driver.findElement(By.xpath("//button//span[text() = "+ timeStart +"]"));
        btn.click();
        sleep(5000);
    }

    public String getMessage() {
        String errorMessage = "";
        try {
            errorMessage = noResultMessage.getText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return errorMessage;
    }
}
