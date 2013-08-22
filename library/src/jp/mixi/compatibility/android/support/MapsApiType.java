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

public enum MapsApiType {

    AMAZON_MAPS_API("com.amazon.geo.maps.MapView"),
    GOOGLE_MAPS_API_V1("com.google.android.maps.MapView"),
    GOOGLE_MAPS_API_V2("com.google.android.gms.maps.MapFragment");

    private final String mMapViewClassName;

    private MapsApiType(String mapViewClassName) {
        this.mMapViewClassName = mapViewClassName;
    }

    /*package*/ String getMapViewClassName() {
        return this.mMapViewClassName;
    }
}