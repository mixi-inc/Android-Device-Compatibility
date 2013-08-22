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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * MapsAPIのサポート情報に関するヘルパークラス
 * @author genichiro.sugawara
 */
public class MapsApiSupportHelper {

    /**
     * {@link MapsApiType}に記述されたMapsAPIのうち、実行環境で利用できるものだけをリストにして返す
     * @return 利用できるMapsAPIの不変なリスト。利用できるAPIがない場合は空のリスト。
     */
    public Collection<MapsApiType> getSupportedMapsApis() {
        List<MapsApiType> supportedList = new ArrayList<MapsApiType>(MapsApiType.values().length);

        for(MapsApiType type : MapsApiType.values()) {
            try {
                Class.forName(type.getMapViewClassName());
                supportedList.add(type);
            } catch(ClassNotFoundException e) {}
        }

        return Collections.unmodifiableList(supportedList);
    }
}
