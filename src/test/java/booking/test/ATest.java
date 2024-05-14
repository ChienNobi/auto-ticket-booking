package booking.test;

import booking.core.BaseTest;
import booking.page.BookingInfoPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class ATest extends BaseTest {
    private BookingInfoPage page;

    @BeforeClass
    public void setUp() throws IOException {
//        initExcelData();
//        Path sourcePath = Paths.get(SystemDefault.TEST_DATA_ROLE_FILE);
//        Path destinationPath = Paths.get(SystemDefault.TEST_DATA_ROLE_FILE_BACKUP);
//        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        page = new BookingInfoPage();
    }

    @Test
    public void search() {
        driver.get("https://translate.google.com/?hl=vi&sl=en&tl=vi&text=Prefer%20writing%20dumb%20code%20over%20clever%20code&op=translate");
    }
}
