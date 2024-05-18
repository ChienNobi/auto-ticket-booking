package booking.core;


import booking.helpers.CommonHelper;
import booking.helpers.EnvReader;

public class SystemDefault {
    private static final EnvReader envReader = EnvReader.getInstance();

    public static final int WAIT_DEFAULT = Integer.parseInt(envReader.getProperty("WAIT_DEFAULT"));
    public static final int WAIT_IMPLICIT = Integer.parseInt(envReader.getProperty("WAIT_IMPLICIT"));
    public static final int WAIT_EXPLICIT = Integer.parseInt(envReader.getProperty("WAIT_EXPLICIT"));
    public static final int WAIT_PAGE_LOADED = Integer.parseInt(envReader.getProperty("WAIT_PAGE_LOADED"));
    public static final int WAIT_STEP_SLEEP = Integer.parseInt(envReader.getProperty("WAIT_STEP_SLEEP"));
    public static final int WAIT_API_CALLING = Integer.parseInt(envReader.getProperty("WAIT_API_CALLING"));
    public static final int WAIT_FILE_DOWNLOAD = Integer.parseInt(envReader.getProperty("WAIT_FILE_DOWNLOAD"));
    public static final int WAIT_ELEMENT_LOADING = Integer.parseInt(envReader.getProperty("WAIT_ELEMENT_LOADING"));
    public static final int WAIT_REDIRECT = Integer.parseInt(envReader.getProperty("WAIT_REDIRECT"));

    public static final String DOWNLOAD_PATH = CommonHelper.getCurrentDir() + envReader.getProperty("DOWNLOAD_FOLDER");
    public static final String RESOURCE_PATH = CommonHelper.getCurrentDir() + envReader.getProperty("RESOURCE_FOLDER");
    public static final String TEST_CASE_FILE = RESOURCE_PATH + envReader.getProperty("TEST_CASE_FILE");
    public static final String TEST_DATA_FILE = RESOURCE_PATH + "data/" + envReader.getProperty("TEST_DATA_FILE");
    public static final String TEST_DATA_FILE_BACKUP = RESOURCE_PATH + "data/" + envReader.getProperty("TEST_DATA_FILE_BACKUP");
}
