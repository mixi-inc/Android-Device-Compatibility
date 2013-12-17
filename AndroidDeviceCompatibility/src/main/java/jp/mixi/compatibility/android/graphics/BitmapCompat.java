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