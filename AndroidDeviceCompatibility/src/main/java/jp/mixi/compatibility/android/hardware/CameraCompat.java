/*
 * Copyright (C) 2014 mixi, Inc. All rights reserved.
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
package jp.mixi.compatibility.android.hardware;

import android.annotation.TargetApi;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;

/**
 * Utility class for the use of hardware {@link android.hardware.Camera}.
 * @author keishin.yokomaku
 */
@SuppressWarnings("unused")
public final class CameraCompat {
    public static final String TAG = CameraCompat.class.getSimpleName();
    private CameraCompat() {}

    /**
     * Tries to open the camera.
     * If the camera is in use by another application, this will return null.
     * @param cameraId to open
     * @return {@link android.hardware.Camera} instance if available. null if not available for the process.
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static Camera tryOpen(int cameraId) {
        try {
            return Camera.open(cameraId);
        } catch (RuntimeException e) { // if someone has a lock for hardware camera.
            Log.e(TAG, "someone keeps using a camera, so we cannot open the camera.", e);
            return null;
        }
    }
}
