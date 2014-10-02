package jp.mixi.compatibility.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;

import jp.mixi.compatibility.android.graphics.BitmapCompat;
import jp.mixi.compatibility.test.R;

/**
 * Created by kikuma on 2014/10/02.
 */
public class BitmapCompatTest extends AndroidTestCase {
    Resources mResources;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mResources = getContext().getResources();
    }

    public void testGetByteCount() {
        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.sample);
        assertEquals(bitmap.getRowBytes() * bitmap.getHeight(), BitmapCompat.getByteCount(bitmap));
    }

    public void testGetByteCountBig() {
        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.sample_big);
        assertEquals(bitmap.getRowBytes() * bitmap.getHeight(), BitmapCompat.getByteCount(bitmap));
    }

}
