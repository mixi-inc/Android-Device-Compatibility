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
