package booking.core;

import booking.configs.BrowserConfig;
import booking.helpers.CommonHelper;
import booking.helpers.EnvReader;
import booking.helpers.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseTest {
    public WebDriver driver;
    public EnvReader envReader;
    public ExcelReader excelReader;
    public List<Map<String, String>> testData;

    private void initChrome() {
        driver = BrowserConfig.setDriver("en");
        BrowserConfig.setWaitImplicit(SystemDefault.WAIT_IMPLICIT);
    }

    @BeforeSuite
    public void beforeSuit() throws IOException, InterruptedException {
        initChrome();
    }

    @AfterClass()
    public void afterClass() {
        BrowserConfig.quit();
    }

    public void initExcelData(String sheetName, String[] headers) throws IOException {
        excelReader = new ExcelReader(SystemDefault.TEST_DATA_FILE, sheetName);
        testData = excelReader.parseData(headers, true);
        Path sourcePath = Paths.get(SystemDefault.TEST_DATA_FILE);
        Path destinationPath = Paths.get(SystemDefault.TEST_DATA_FILE_BACKUP);
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public void waitCaptcha() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-dialog']")));
    }
}
