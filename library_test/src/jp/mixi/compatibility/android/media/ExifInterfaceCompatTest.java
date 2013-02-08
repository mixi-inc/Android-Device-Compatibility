package jp.mixi.compatibility.android.media;

import android.test.AndroidTestCase;

public class ExifInterfaceCompatTest extends AndroidTestCase {
    public void testInitialization() throws Exception {
        try {
            ExifInterfaceCompat.newInstance(null);
            fail();
        } catch (NullPointerException e) {
            // it's okay to catch NullPointerException.
        }
    }
}