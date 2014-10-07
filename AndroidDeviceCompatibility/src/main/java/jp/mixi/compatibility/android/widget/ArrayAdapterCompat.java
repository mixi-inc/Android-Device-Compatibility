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
package jp.mixi.compatibility.android.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.widget.ArrayAdapter;

import java.util.Collection;

/**
 * Compatibility class for a difference of android framework API levels.
 */
public final class ArrayAdapterCompat {
    /**
     * Do not instantiate this class.
     */
    private ArrayAdapterCompat() {
    }

    /**
     * Add all elements in the collection to the end of the adapter.
     *
     * @param adapter to be added.
     * @param list    to add all elements.
     * @param <T>     generic type for the collection.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static final <T> void addAll(ArrayAdapter<T> adapter, Collection<? extends T> list) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            adapter.addAll(list);
        } else {
            for (T element : list) {
                adapter.add(element);
            }
        }
    }

    /**
     * Add all elements in the array to the end of the adapter.
     *
     * @param adapter to be added
     * @param array   to add all elements
     * @param <T>     generic type for an array.
     */
    @SuppressLint("NewApi")
    public static final <T> void addAll(ArrayAdapter<T> adapter, T... array) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            adapter.addAll(array);
        } else {
            for (T element : array) {
                adapter.add(element);
            }
        }
    }
}