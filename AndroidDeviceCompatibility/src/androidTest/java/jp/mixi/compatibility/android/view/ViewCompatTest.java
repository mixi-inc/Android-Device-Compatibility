package jp.mixi.compatibility.android.view;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.test.AndroidTestCase;
import android.view.View;

/**
 * Created by Hideyuki.Kikuma on 2014/10/03.
 *
 * @author Hideyuki.Kikuma
 */
public class ViewCompatTest extends AndroidTestCase {
    public void testSetLayerType() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) return;
        MockView view = new MockView(getContext());
        Paint paint = new Paint();
        ViewCompat.setLayerType(view, View.LAYER_TYPE_HARDWARE, paint);
        assertEquals(View.LAYER_TYPE_HARDWARE, view.mLayerType);
        assertEquals(paint, view.mPaint);
        assertTrue(view.mCalled);

    }

    public void testCallSetLayerType() {
        MockView view = new MockView(getContext());
        ViewCompat.setLayerType(view, View.LAYER_TYPE_HARDWARE, null);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            assertFalse(view.mCalled);
        } else {
            assertTrue(view.mCalled);
        }
    }

    private class MockView extends View {
        int mLayerType;
        Paint mPaint;
        boolean mCalled = false;

        public MockView(Context context) {
            super(context);
        }

        @Override
        public void setLayerType(int layerType, Paint paint) {
            mCalled = true;
            mLayerType = layerType;
            mPaint = paint;
        }
    }
}
