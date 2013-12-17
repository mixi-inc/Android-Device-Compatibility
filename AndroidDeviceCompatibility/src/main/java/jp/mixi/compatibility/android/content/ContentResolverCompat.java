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

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

/**
 * OS version compatibility of periodical synchronization procedure call.
 * @author keishin.yokomaku
 */
public final class ContentResolverCompat {
    /**
     * Do not instantiate this class.
     */
    private ContentResolverCompat() {}

    /**
     * Add job queue to dispatch a sync that should be requested with the specified account, authority, and extras at the given frequency.
     * @param context a context.
     * @param account an account to add periodic sync.
     * @param authority an authority to dispatch periodic sync.
     * @param extras an bundle contains arguments for a sync adapter.
     * @param pollFrequency how frequently do a sync performed.
     */
    @SuppressLint("NewApi")
    public static final void addPeriodicSync(Context context, Account account, String authority, Bundle extras, long pollFrequency) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            // Available for periodical sync of content provider
            ContentResolver.addPeriodicSync(account, authority, extras, pollFrequency);
        } else {

        }
    }

    /**
     * Remove a periodic sync request for the account and authority.
     * @param context a context.
     * @param account an account to remove periodic sync.
     * @param authority an authority to cancel periodic sync.
     * @param extras an bundle contains arguments for a sync adapter.
     */
    @SuppressLint("NewApi")
    public static final void removePeriodicSync(Context context, Account account, String authority, Bundle extras) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            ContentResolver.removePeriodicSync(account, authority, extras);
        } else {

        }
    }
}