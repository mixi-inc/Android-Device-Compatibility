package jp.mixi.compatibility.android.provider;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.provider.CallLog;
import android.text.TextUtils;
import android.util.Log;

/**
 * Compatibility for the variety of call log data store.
 *
 * @author jfsso
 * @author KeithYokoma
 */
@SuppressWarnings("unused")
public final class CallLogCompat {
    public static final String TAG = CallLogCompat.class.getSimpleName();
    public static final String COLUMN_CACHED_LOOKUP_URI = "lookup_uri";
    public static final String UNKNOWN_NUMBER = "-1";
    public static final String PRIVATE_NUMBER = "-2";
    public static final String PAYPHONE_NUMBER = "-3";

    private CallLogCompat() {
        throw new AssertionError();
    }

    public static String getName(Cursor cursor, String fallback) {
        String name = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.CACHED_NAME));
        if (TextUtils.isEmpty(name))
            return fallback;
        else
            return name;
    }

    @SuppressLint("InlinedApi")
    public static int getNumberPresentation(Cursor cursor, String number) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return cursor.getInt(cursor.getColumnIndex(CallLog.Calls.NUMBER_PRESENTATION));
        } else {
            if (UNKNOWN_NUMBER.equals(number)) {
                return CallLog.Calls.PRESENTATION_UNKNOWN;
            } else if (PRIVATE_NUMBER.equals(number)) {
                return CallLog.Calls.PRESENTATION_RESTRICTED;
            } else if (PAYPHONE_NUMBER.equals(number)) {
                return CallLog.Calls.PRESENTATION_PAYPHONE;
            }
        }
        return CallLog.Calls.PRESENTATION_ALLOWED;
    }

    /**
     * Compatibility support method for Samsung devices to correctly fetch call logs.
     * Many thanks to CallLogCalendar :), and the code reference is here(https://gist.github.com/jfsso/391c4bc757bc4210f4bc).
     *
     * @param resolver the resolver, not nullable.
     * @param selection the selection, can be null.
     */
    public static String addTypeSelectionIfNeeded(ContentResolver resolver, String selection) {
        // Some samsung devices add all types of logs to the call log.
        // Here we will make an attempt to know if it is one of those.
        // It simply will check for the "logtype" column:
        //   if it exists, the phone is a troublesome one: add some selection parameters
        //   if not, the phone is a nice one ;)
        try {
            if (Build.BRAND.toLowerCase().equals("samsung")) {
                resolver.query(CallLog.Calls.CONTENT_URI, new String[] { "logtype" }, null, null, null);
                // no exception thrown
                // yes, it is a galaxy phone! lets add the parameter to select
                // only call logs
                Log.v(TAG, "oops, this one needs some hack for call logs X(");
                if(TextUtils.isEmpty(selection)) {
                    selection = "logtype IN (100, 500)";
                } else {
                    selection += " AND logtype IN (100, 500)";
                }
            }
        } catch (Exception e) {
            // ignore
            Log.v(TAG, "nice one! no need to hack call logs :)");
        }
        Log.v(TAG, "after: " + selection);
        return selection;
    }
}
