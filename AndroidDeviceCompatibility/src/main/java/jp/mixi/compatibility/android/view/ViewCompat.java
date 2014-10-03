/*
 * Copyright (C) 2012 mixi, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.mixi.compatibility.android.view;

import android.annotation.TargetApi;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;

/**
 * OS version compatibility class for recently added APIs of {@link View} class.
 *
 * @author keishin.yokomaku
 */
public final class ViewCompat {
    public static final String TAG = ViewCompat.class.getSimpleName();

    private ViewCompat() {
    }

    /**
     * Specifies the type of layer backing this view.
     *
     * @param view      Target {@link View}
     * @param layerType One of {@link View#LAYER_TYPE_NONE}, {@link View#LAYER_TYPE_SOFTWARE} or {@link View#LAYER_TYPE_HARDWARE}.
     * @param paint     Optional {@link Paint} instance that controls how the layer is composed on screen.
     * @see View#setLayerType(int, Paint)
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static final void setLayerType(final View view, final int layerType, final Paint paint) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return;
        }
        view.setLayerType(layerType, paint);
    }
}