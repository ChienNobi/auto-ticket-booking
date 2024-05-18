package booking.helpers;

import booking.constants.Common;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

public class CommonHelper {
    public static String detectUrl(String bodyText) throws IOException {
        if(bodyText.isBlank() || bodyText.isEmpty()) {
            return "";
        }
        Matcher matcher = Common.urlPattern.matcher(bodyText);
        return matcher.find() ? matcher.group() : "";
    }

    public static List<String> detectListUrl(String bodyText) throws IOException {
        Matcher matcher = Common.urlPattern.matcher(bodyText);
        List<String> listUrl = new ArrayList<>();
        while (matcher.find()) {
            String match = matcher.group();
            listUrl.add(match);
        }
        return listUrl;
    }

    public static String detectAuthKey(String bodyText) throws IOException {
        if(bodyText.isBlank() || bodyText.isEmpty()) {
            return "";
        }
        Matcher matcher = Common.authKeyPattern.matcher(bodyText);
        return matcher.find() ? matcher.group() : "";
    }


    public static boolean verifyLowerCase(String bodyText) throws IOException {
        Matcher matcher = Common.requireLowerCasePattern.matcher(bodyText);
        return matcher.find();
    }

    /**
     * @return get current directory
     */
    public static String getCurrentDir() {
        return System.getProperty("user.dir") + File.separator;
    }

    public static <T> String getCurrentClassName(Class<T> type) {
        return type.getSimpleName();
    }

    public static boolean isEmptyString(String text) {
        return text == null || text.isBlank() || text.isEmpty();
    }

    public static String getCurrentLocalDateTime(String pattern) {
        LocalDateTime localDate = LocalDateTime.now();
        return String.valueOf(DateTimeFormatter.ofPattern(pattern).format(localDate));
    }

    public static String getCurrentLocalDateTime() {
        return getCurrentLocalDateTime("dd-MM-yy HH:mm");
    }

    public static Calendar validateDateTime(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }
}
