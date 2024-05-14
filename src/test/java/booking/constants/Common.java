package booking.constants;

import java.util.regex.Pattern;

public class Common {
    public static final Pattern urlPattern =
            Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&//=]*)", Pattern.CASE_INSENSITIVE);

    public static final Pattern authKeyPattern = Pattern.compile("[0-9]{6}+");

    public static final Pattern requireLowerCasePattern = Pattern.compile(".*[a-z].*");//[^a-z]+
}
