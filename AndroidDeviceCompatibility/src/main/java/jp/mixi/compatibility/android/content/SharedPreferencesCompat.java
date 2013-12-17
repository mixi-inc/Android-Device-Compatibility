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
package jp.mixi.compatibility.android.content;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Workaround helper for the issue of {@link SharedPreferences} with Galaxy S.
 */
public final class SharedPreferencesCompat {
    public static final String TAG = SharedPreferencesCompat.class.getSimpleName();
    private static final String SAMSUNG_PREF_DIR_BASE = "/dbdata/databases";
    private static Boolean sIsNeedPreferenceWorkaround = null;

    /**
     * Do not instantiate this class.
     */
    private  SharedPreferencesCompat() {}

    /**
     * As a workaround for Galaxy S /dbdata/databases permission bug, this
     * function will make an injection to the implementation of Context to
     * change a directory used by SharedPreferences. Called from
     * {@link YourApplication#onCreate()} when the application starts.
     *
     * @param context injection target context.
     */
    public static void injectPrefencesDirIfNeeded(Context context) {
        if (needPreferenceWorkaround(context))
            injectPreferencesDir(context);
    }

    /**
     * Wrapper method of getting default shared preferences.
     *
     * @param context a context
     * @return the default shared preferences.
     */
    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    /**
     * Wrapper method of getting shared preferences.
     *
     * @param context a context
     * @param name a preference name.
     * @param mode operating mode.
     * @return a {@link SharedPreferences} for a specified name.
     */
    public static SharedPreferences getSharedPreferences(Context context, String name, int mode) {
        return context.getApplicationContext().getSharedPreferences(name, mode);
    }

    /**
     * Checks whether we should inject to the implementation of Context for workaround or not.
     *
     * @param context a context that should be investigated.
     * @return true if we've lost a permission to deal with shared preferences directory.
     */
    public static boolean needPreferenceWorkaround(Context context) {
        if (sIsNeedPreferenceWorkaround == null) {
            File baseDir = new File(SAMSUNG_PREF_DIR_BASE, context.getPackageName());
            boolean result = baseDir.exists() && !baseDir.canWrite();
            if (result)
                Log.v(TAG, "need /dbdata workaround");
            sIsNeedPreferenceWorkaround = result;
        }
        return sIsNeedPreferenceWorkaround.booleanValue();
    }

    /**
     * Injects {@link SharedPreferences} directory to enable using proper {@link SharedPreferences}.
     *
     * @param context a context.
     */
    private static void injectPreferencesDir(Context context) {
        if (ContextWrapper.class.isInstance(context)) {
            ContextWrapper wrapper = (ContextWrapper) context;
            context = wrapper.getBaseContext();
        }

        // inject to overwrite inner ContextImpl#mPreferencesDir
        try {
            Class<?> clazz = Class.forName("android.app.ContextImpl");

            Field field = clazz.getDeclaredField("mPreferencesDir");
            field.setAccessible(true);
            if (field.get(context) != null)
                return;

            // build shared_prefs path and try to overwrite
            Method getDataDirFile = clazz.getDeclaredMethod("getDataDirFile");
            getDataDirFile.setAccessible(true);
            File dataDir = (File) getDataDirFile.invoke(context);

            field.set(context, new File(dataDir, "shared_prefs"));
        } catch (Exception ignored) {
            Log.w(TAG, "failed to apply Samsung Galaxy broken /dbdata workaround.", ignored);
        }
    }
}