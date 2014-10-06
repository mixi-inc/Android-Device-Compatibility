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
package jp.mixi.compatibility.android.os;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

/**
 * OS version compatibility to enable strict mode later than {@link Build.VERSION_CODES#FROYO}.
 *
 * @author keishin.yokomaku
 */
public final class StrictModeCompat {
    public static final String TAG = StrictModeCompat.class.getSimpleName();

    private StrictModeCompat() {
    }

    /**
     * Enable default strict mode.
     * Earlier than Gingerbread, this method will do nothing.
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static final void enableDefaults() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            Log.v(TAG, "enabling StrictMode in default parameters");
            StrictMode.enableDefaults();
        }
    }
}