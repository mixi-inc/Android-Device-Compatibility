package jp.mixi.compatibility.android.text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Date formatting issues workaround depends on a device specific implementation.
 */
public final class DateStringCompat {
    /**
     * Do not instantiate this class.
     */
    private DateStringCompat() {}

    /**
     * Some HTC devices return wrong formatted text from DateFormat.
     * ex. DateFormat.format("MM月DD日", new Date("2013/1/25"))
     *   => expected:<01月25[日]> but was:<01月25[]>
     * As a workaround for HTC device's bug,
     * this function uses {@link SimpleDateFormat} for now.
     *
     * @param format please refer to the {@link SimpleDateFormat}
     * @param date to be formatted
     * @return formatted string
     */
    public static String format(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.format(date);
    }
}