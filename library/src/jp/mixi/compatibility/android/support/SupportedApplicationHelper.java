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

import java.util.List;

import javax.inject.Inject;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

/**
 * 端末にインストールされているアプリケーションの情報に関するヘルパークラス
 * @author genichiro.sugawara
 */
public class SupportedApplicationHelper {

    private final PackageManager mPackageManager;

    @Inject
    public SupportedApplicationHelper(PackageManager packageManager) {
        mPackageManager = packageManager;
    }

    /** 
     * 指定したintentに対応したアプリケーションがインストールされているかどうかを返す
     * （intentでstartActivity()する前に、それが可能であるか確認する）
     */
    public boolean canStartActivity(final Intent intent) {
        List<ResolveInfo> activities = mPackageManager.queryIntentActivities(intent, 0);
        return !activities.isEmpty();
    }

    /**
     * 指定した intent をハンドル出来る BroadcastReceiver が存在するかどうかを返す
     */

    public boolean canHandleIntent(final Intent intent) {
        List<ResolveInfo> activities = mPackageManager.queryBroadcastReceivers(intent, 0);
        return !activities.isEmpty();
    }
}
