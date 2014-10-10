package jp.mixi.compatibility.text;

import android.test.AndroidTestCase;
import android.util.Pair;

import java.util.Date;

import jp.mixi.compatibility.android.text.DateStringCompat;

/**
 * Created by Hideyuki.Kikuma on 2014/10/08.
 */
public class DateStringCompatTest extends AndroidTestCase {
    public void testFormat() {
        Date date = new Date("2013/1/9 08:04:05");
        Pair[] array = new Pair[]{
                //  new Pair<String, String>(format, expected),
                new Pair<String, String>("yyyy-MM-dd HH:mm:ss", "2013-01-09 08:04:05"),
                new Pair<String, String>("yyyy/MM/dd HH:mm:ss", "2013/01/09 08:04:05"),
                new Pair<String, String>("MM月dd日", "01月09日"),
        };
        for (Pair<String, String> p : array) {
            assertEquals(p.second, DateStringCompat.format(p.first, date));
        }
    }
}
