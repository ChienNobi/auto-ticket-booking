package booking.test;

import booking.core.BaseTest;
import booking.core.SystemDefault;
import booking.helpers.CommonHelper;
import booking.helpers.ExcelWriter;
import booking.page.TrainStartTimePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import static java.lang.Thread.sleep;

public class TrainStartTest extends BaseTest {
    private TrainStartTimePage page;

    @BeforeClass
    public void setUp() throws IOException {
        initExcelData("TrainStartTime", new String[]{"start_station", "destination_station", "start_time", "expected"});
        page = new TrainStartTimePage();
    }

    @Test
    public void search() throws InterruptedException, IOException {
        page.openPage();
        for (int i = 0; i < testData.size(); i++) {
            sleep(2000);
            Map<String, String> data = testData.get(i);
            System.out.println("Current row " + data.get("row_index") + ": " + data.get("start_station") + "-" + data.get("destination_station") + "-" + data.get("start_time"));
            try {
                Calendar calendar = CommonHelper.validateDateTime(data.get("start_time"));
                if(calendar == null) {
                    excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 4, "Invalid datetime format: dd/MM/yyyy");
                    excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 5, CommonHelper.getCurrentLocalDateTime());
                    continue;
                }
                page.enterData(data.get("start_station"), data.get("destination_station"), String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                sleep(1000);
                String result = page.getMessage();

                if(CommonHelper.isEmptyString(result)) {
                    result = "Pass";
                }

                System.out.println("Result " + result);
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 4, result);
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 5, CommonHelper.getCurrentLocalDateTime());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 4, "Fail");
            }
            page.refreshPage();
        }

        excelReader.closeFile();
        ExcelWriter excelWriter = new ExcelWriter(SystemDefault.TEST_DATA_FILE);
        excelWriter.writeByOtherData(excelReader.getWorkbook());
    }
}
