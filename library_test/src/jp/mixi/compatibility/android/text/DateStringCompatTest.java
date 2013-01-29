package jp.mixi.compatibility.android.text;

import java.util.Date;

import jp.mixi.compatibility.android.text.DateStringCompat;

import android.test.AndroidTestCase;

public class DateStringCompatTest extends AndroidTestCase {
    @SuppressWarnings("deprecation")
    public void testformat() {
        Date date = new Date("2013/2/25 7:25:50");
        String format = "yyyy年MM月dd日hh時mm分ss秒";
        String formatterdText = DateStringCompat.format(format, date);
        assertEquals("2013年02月25日07時25分50秒", formatterdText);
    }
}