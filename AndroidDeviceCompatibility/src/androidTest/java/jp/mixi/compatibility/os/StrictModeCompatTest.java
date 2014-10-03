package jp.mixi.compatibility.os;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.test.AndroidTestCase;

import jp.mixi.compatibility.android.os.StrictModeCompat;

/**
 * Created by Hideyuki.Kikuma on 2014/10/04.
 */
public class StrictModeCompatTest extends AndroidTestCase {
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void testEnableDefaults() {
        StrictModeCompat.enableDefaults();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) return;
        assertEquals(
                new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build().toString(),
                StrictMode.getThreadPolicy().toString());

        assertEquals(new StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build().toString(),
                StrictMode.getVmPolicy().toString());
    }
}
