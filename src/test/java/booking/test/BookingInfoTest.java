package booking.test;

import booking.core.BaseTest;
import booking.core.SystemDefault;
import booking.helpers.CommonHelper;
import booking.helpers.ExcelWriter;
import booking.page.BookingInfoPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class BookingInfoTest extends BaseTest {
    private BookingInfoPage page;
    @BeforeClass
    public void setUp() throws IOException {
        initExcelData("BookingInfo", new String[]{"booking_code", "email", "phone_number", "expected"});
        page = new BookingInfoPage();
    }

    @Test
    public void search() throws InterruptedException, IOException {
        page.openPage();
        for (int i = 0; i < testData.size(); i++) {
            sleep(2000);
            Map<String, String> data = testData.get(i);
            System.out.println("Current row " + data.get("row_index") + ": " + data.get("booking_code") + "-" + data.get("email") + "-" + data.get("phone_number"));
            try {
                page.searchBookingInfo(data.get("booking_code"), data.get("email"), data.get("phone_number"));
                sleep(1000);
                waitCaptcha();
                String errorMessage = page.getErrorMessage();
                String result = "Fail";

                System.out.println("Error message: " + errorMessage);
//                if(CommonHelper.isEmptyString(errorMessage) || errorMessage.equals(data.get("expected"))) {
//                    result = "Pass";
//                }
//
//                System.out.println("Result: " + result);
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 4, errorMessage);
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 5, CommonHelper.getCurrentLocalDateTime());

                page.refreshPage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 4, "Fail");
            }
        }

        excelReader.closeFile(); // must close file input for write
        ExcelWriter excelWriter = new ExcelWriter(SystemDefault.TEST_DATA_FILE);
        excelWriter.writeByOtherData(excelReader.getWorkbook());
    }
}
