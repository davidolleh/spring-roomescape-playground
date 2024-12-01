package roomescape.util;

import java.time.format.DateTimeFormatter;

public class CustomDateTimeFormat {
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
}
