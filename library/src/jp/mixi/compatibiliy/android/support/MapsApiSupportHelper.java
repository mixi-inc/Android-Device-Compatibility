package jp.mixi.compatibiliy.android.support;

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
