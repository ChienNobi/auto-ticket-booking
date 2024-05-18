package booking.test;

import booking.core.BaseTest;
import booking.core.SystemDefault;
import booking.helpers.CommonHelper;
import booking.helpers.ExcelWriter;
import booking.page.CheckingTicketInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import static java.lang.Thread.sleep;

public class CheckingTicketInfoTest extends BaseTest {
    private CheckingTicketInfo page;
    @BeforeClass
    public void setUp() throws IOException {
        initExcelData("TicketChecking", new String[]{"ticket_code", "train_number", "start_station", "end_station", "start_time", "user_cccd"});
        page = new CheckingTicketInfo();
    }

    @Test
    public void search() throws InterruptedException, IOException {
        page.openPage();
        for (int i = 0; i < testData.size(); i++) {
            sleep(1000);
            Map<String, String> data = testData.get(i);
            System.out.println("Current row " + data.get("row_index") + ": " + data.get("ticket_code") + "-"
                    + data.get("train_number") + "-" + data.get("start_station") + "-" + data.get("end_station") + "-" + data.get("start_time") + "-" + data.get("user_cccd"));
            try {
                Calendar calendar = CommonHelper.validateDateTime(data.get("start_time"));
                if(calendar == null) {
                    excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 7, "Invalid datetime format: dd/MM/yyyy");
                    excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 8, CommonHelper.getCurrentLocalDateTime());
                    continue;
                }
                page.enterTicketInfo(data.get("ticket_code"), data.get("train_number"), data.get("start_station"),
                        data.get("end_station"), String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), data.get("user_cccd"));
                sleep(1000);
                waitCaptcha();
                page.clickSearch();
                String result = page.getErrorCommon();
                if(CommonHelper.isEmptyString(result)) {
                    result = "Pass";
                }
                System.out.println("Result: " + result);
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 7, result);
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 8, CommonHelper.getCurrentLocalDateTime());
                page.refreshPage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 7, "Fail");
            }

            excelReader.closeFile(); // must close file input for write
            ExcelWriter excelWriter = new ExcelWriter(SystemDefault.TEST_DATA_FILE);
            excelWriter.writeByOtherData(excelReader.getWorkbook());
        }
    }
}
