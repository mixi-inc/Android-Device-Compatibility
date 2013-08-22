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
package jp.mixi.compatibility.android.support;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Build;

import javax.inject.Inject;

/**
 * An utility class for handling device features.
 * 
 * @author yuki.fujisaki
 * 
 */
public class HardwareFeatureSupportHelper {

    private final PackageManager mPackageManager;

    @Inject
    public HardwareFeatureSupportHelper(PackageManager packageManager) {
        mPackageManager = packageManager;
    }

    /**
     * Returns whether camera is available on the device.
     * 
     * @param pm
     *            {@link PackageManager} instance which can be retrieved via
     *            {@link Context}.
     * @return true if the device has any camera.
     */
    public boolean hasCamera() {
        return mPackageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    /**
     * Check whether the device has a facing back camera.
     * 
     * This is useful to avoid receiving null on {@lin Camera#open()} call.
     * 
     * @return true if facing back camera is available, false otherwise.
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public boolean hasFacingBackCamera() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            return true;
        }

        int numberOfCameras = Camera.getNumberOfCameras();
        CameraInfo cameraInfo = new CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK) {
                return true;
            }
        }
        return false;
    }
}