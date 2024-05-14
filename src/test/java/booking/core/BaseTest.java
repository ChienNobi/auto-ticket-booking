package booking.core;

import booking.configs.BrowserConfig;
import booking.helpers.CommonHelper;
import booking.helpers.EnvReader;
import booking.helpers.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.ArrayList;

public class BaseTest {
    public WebDriver driver;
    public EnvReader envReader;
    public ExcelReader excelReader;

    private void initChrome() {
        driver = BrowserConfig.setDriver("en");
        BrowserConfig.setWaitImplicit(SystemDefault.WAIT_IMPLICIT);
    }

    @BeforeSuite
    public void beforeSuit() throws IOException, InterruptedException {
        initChrome();
//        excelReader = new ExcelReader(SystemDefault.TEST_CASE_FILE);
//        envReader = EnvReader.getInstance();
    }

    @AfterClass()
    public void afterClass() {
        BrowserConfig.quit();
    }

//    public <T> void initSearchKeyData(Class<T> type) throws IOException {
//        excelReader.initSheet(SEARCH_DATA_SHEET);
//        testData = excelReader.getTestDataByKey(CommonHelper.getCurrentClassName(type));
//    }
}
