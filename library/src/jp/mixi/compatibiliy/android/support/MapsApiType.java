package jp.mixi.compatibiliy.android.support;

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