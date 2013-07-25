package jp.mixi.compatibility.android.view;

import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * OS version compatibility class for recently added APIs of {@link View} class.
 * @author keishin.yokomaku
 *
 */
public final class ViewCompat {
    public static final String TAG = ViewCompat.class.getSimpleName();

    private ViewCompat() {}

    /**
     * Specifies the type of layer backing this view. 
     * 
     * @param view Target {@link View}
     * @param layerType One of {@link View#LAYER_TYPE_NONE}, {@link View#LAYER_TYPE_SOFTWARE} or {@link View#LAYER_TYPE_HARDWARE}.
     * @param paint Optional {@link Paint} instance that controls how the layer is composed on screen.
     * @see View#setLayerType(int, Paint)
     */
    public static final void setLayerType(final View view, final int layerType, final Paint paint) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return;
        }
        try {
            Method setLayerType = View.class.getMethod("setLayerType", Integer.TYPE, Paint.class);
            setLayerType.invoke(view, layerType, paint);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "View#setLayerType(int, Paint) is not supported on the device.", e);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Invalid argument for View#setLayerType(int, Paint)", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Illegal access to View#setLayerType(int, Paint).", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}