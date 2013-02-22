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
package jp.mixi.compatibility.android.media;

import android.media.ExifInterface;

import java.io.IOException;

/**
 * Bug fixture for ExifInterface constructor.
 */
public final class ExifInterfaceCompat {
    /**
     * Do not instantiate this class.
     */
    private ExifInterfaceCompat() {}

    /**
     * Creates new instance of {@link ExifInterface}.
     * Original constructor won't check filename value, so if null value has been passed,
     * the process will be killed because of SIGSEGV.
     * Google Play crash report system cannot perceive this crash, so this method will throw {@link NullPointerException} when the filename is null.
     *
     * @param filename a JPEG filename.
     * @return {@link ExifInterface} instance.
     * @throws IOException something wrong with I/O.
     */
    public static final ExifInterface newInstance(String filename) throws IOException {
        if (filename == null) throw new NullPointerException("filename should not be null");
        return new ExifInterface(filename);
    }
}
