package jp.mixi.compatibility.android.provider;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;

/**
 * @author jfsso
 * @author KeithYokoma
 */
@SuppressWarnings("unused")
public final class TelephonyCompat {
    private TelephonyCompat() {
        throw new AssertionError();
    }

    public static final class Sms {
        public static final String TAG = Sms.class.getSimpleName();
        public static final String URI_CONTENT_SMS = "content://sms";
        public static final int TYPE_INBOX = 1;
        public static final int TYPE_SENT = 2;
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_BODY = "body";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_SUBJECT = "subject";
        public static final String COLUMN_THREAD_ID = "thread_id";
        public static final String COLUMN_PERSON = "person";
        public static final String DEFAULT_SORT_ORDER = "date DESC";

        private Sms() {
            throw new AssertionError();
        }

        @SuppressLint("NewApi") // good to go with our compatibility
        public static Uri getContentUri() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                return Telephony.Sms.CONTENT_URI;
            else
                return Uri.parse(URI_CONTENT_SMS);
        }

        @SuppressLint("NewApi") // we port from the newer api
        public static String getDefaultSortOrder() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                return Telephony.Sms.DEFAULT_SORT_ORDER;
            else
                return DEFAULT_SORT_ORDER;
        }
    }
}