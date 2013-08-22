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

import android.os.Build;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * OS version compatibility to enable strict mode later than {@link Build#VERSION_CODES#FROYO}.
 * @author keishin.yokomaku
 */
public final class StrictModeCompat {
    public static final String TAG = StrictModeCompat.class.getSimpleName();

    private StrictModeCompat() {}

    /**
     * Enable default strict mode.
     * Earlier than Gingerbread, this method will do nothing.
     */
    public static final void enableDefaults() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            Log.v(TAG, "enabling StrictMode in default parameters");
            try {
                Class<?> strictMode = Class.forName("android.os.StrictMode");
                Method defaults = strictMode.getMethod("enableDefaults");
                defaults.invoke(null);
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "StrictMode not found on the platform.", e);
            } catch (SecurityException e) {
                Log.e(TAG, "cannot call StrictMode.enableDefaults", e);
            } catch (NoSuchMethodException e) {
                Log.e(TAG, "cannot call StrictMode.enableDefaults", e);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "cannot call StrictMode.enableDefaults", e);
            } catch (IllegalAccessException e) {
                Log.e(TAG, "cannot call StrictMode.enableDefaults", e);
            } catch (InvocationTargetException e) {
                Log.e(TAG, "cannot call StrictMode.enableDefaults", e);
            }
        }
    }
}
