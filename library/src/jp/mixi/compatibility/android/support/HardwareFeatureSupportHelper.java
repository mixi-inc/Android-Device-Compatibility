package jp.mixi.compatibility.android.support;

import android.content.Context;
import android.content.pm.PackageManager;

import javax.inject.Inject;

/**
 * An utility class for handling device features.
 * 
 * @author yuki.fujisaki
 * 
 */
public class HardwareFeatureSupportHelper {
	
	private final PackageManager mPackageManager;
	
	@Inject
	public HardwareFeatureSupportHelper(PackageManager packageManager) {
		mPackageManager = packageManager;
	}
	
    /**
     * Returns whether camera is available on the device.
     * 
     * @param pm
     *            {@link PackageManager} instance which can be retrieved via
     *            {@link Context}.
     * @return true if the device has any camera.
     */
    public boolean hasCamera() {
        return mPackageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}