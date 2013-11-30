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
package jp.mixi.compatibility.android.webkit;

import android.webkit.WebView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Compatibility class for the issue of various implementations of WebView.
 */
public final class WebViewCompat {
    /**
     * Do not instantiate this.
     */
    private WebViewCompat() {}

    /**
     * Workaround for a bug of http://code.google.com/p/android/issues/detail?id=4401
     *
     * Some devices cannot render with a un-url-encoded html text,
     * as {@link WebView#loadData(String, String, String)} will load html data as a data scheme url,
     * so we need to url-encode html text before loading html data.
     * @param webview a webview to load the data.
     * @param data the html data.
     * @param mimeType a mime type of the data.
     * @param encoding an encoding of the data.
     * @throws UnsupportedEncodingException if a specified encoding is not supported.
     */
    public static final void loadData(WebView webview, String data, String mimeType, String encoding) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(data, encoding);
        webview.loadData(encoded, mimeType, encoding);
    }
}