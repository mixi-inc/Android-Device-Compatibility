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
package jp.mixi.compatibility.android.hardware.exception;

/**
 * Signals an error on {@link android.hardware.Camera} usage.
 * Especially the case if the camera is locked by another application, or disabled by the device admin.
 * @author keishin.yokomaku
 */
@SuppressWarnings("unused") // public API
public class CameraLockedException extends Exception {
    private static final long serialVersionUID = 6897148507275774935L;

    public CameraLockedException() {
        super();
    }

    public CameraLockedException(String detailMessage) {
        super(detailMessage);
    }

    public CameraLockedException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CameraLockedException(Throwable throwable) {
        super(throwable);
    }
}
