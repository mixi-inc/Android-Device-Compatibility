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
package jp.mixi.compatibility.com.felicanetworks.mfc;

import android.os.Build;

/**
 * Device-specific compatibility class for FeliCa implementation.
 *
 * @author keishin.yokomaku
 */
public final class FelicaCompat {
    public static final String[] FELICA_PUSH_UNSUPPORTED_MODELS = new String[] {
            "IS04", "T-01C"
    };

    /**
     * Do not instantiate this class.
     */
    private FelicaCompat() {}

    /**
     * Checks whether FeliCa push feature is available or not.
     * FeliCa push feature is not available on some devices, but we cannot detect it at runtime.
     *
     * @return true if FeliCa push feature is available, false otherwise.
     */
    public static boolean isFeliCaPushAvailable() {
        for (String model : FELICA_PUSH_UNSUPPORTED_MODELS) {
            if (model.equals(Build.MODEL)) return false;
        }
        return true;
    }
}