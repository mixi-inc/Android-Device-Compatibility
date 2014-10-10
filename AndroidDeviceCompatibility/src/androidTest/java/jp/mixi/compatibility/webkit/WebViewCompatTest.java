package jp.mixi.compatibility.webkit;

import android.content.Context;
import android.test.AndroidTestCase;
import android.webkit.WebView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import jp.mixi.compatibility.android.webkit.WebViewCompat;

/**
 * Created by Hideyuki.Kikuma on 2014/10/08.
 */
public class WebViewCompatTest extends AndroidTestCase {
    private static final String DATA = "日本語";
    private static final String MIME_TYPE = "text/plain";

    public void testLoadData() throws Exception {
        String[] encodings = new String[]{
                "shift_JIS",
                "shiftJIS",
                "utf-8",
                "utf8",
                "big5",
                "iso-10646-ucs-2",
                "utf-16",
        };
        for (String encoding : encodings) {
            DummyWebView webView = new DummyWebView(getContext());
            WebViewCompat.loadData(webView, DATA, MIME_TYPE, encoding);
            assertEquals(encoding, DATA, webView.mDecodeData);
            assertEquals(MIME_TYPE, webView.mMimeType);
            assertEquals(encoding, webView.mEncoding);
        }
    }

    private class DummyWebView extends WebView {
        private String mDecodeData;
        private String mMimeType;
        private String mEncoding;

        public DummyWebView(Context context) {
            super(context);
        }

        @Override
        public void loadData(String data, String mimeType, String encoding) {
            try {
                mDecodeData = URLDecoder.decode(data, encoding);
            } catch (UnsupportedEncodingException e) {
                fail();
            }
            mMimeType = mimeType;
            mEncoding = encoding;
        }
    }
}
