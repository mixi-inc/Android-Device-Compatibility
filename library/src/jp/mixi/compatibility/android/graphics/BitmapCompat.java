package jp.mixi.compatibility.android.graphics;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;

/**
 * OS version compatibility of bitmap API.
 * @author keishin.yokomaku
 */
public final class BitmapCompat {
    private BitmapCompat() {}

    /**
     * Returns the number of bytes used to store the pixels.
     * @param bitmap to be calculated byte count.
     * @return bytes used to store the pixels.
     */
    @SuppressLint("NewApi")
    public static final int getByteCount(Bitmap bitmap) {
        // Available from API Level 12
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        } else {
            // Back-port for the earlier releases
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }
}