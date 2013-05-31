package jp.mixi.compatibility.android.support;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Build;

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
    
    /**
     * Return has camera facing {@link CameraInfo.CAMERA_FACING_BACK}.
     * 
     *  Prevent {@link Camera.open} is null.
     * 
     * @return true if has facing back camera.
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public boolean hasFacingBackCamera() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            return true;
        }

        int numberOfCameras = Camera.getNumberOfCameras();
        CameraInfo cameraInfo = new CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK) {
                return true;
            }
        }
        return false;
    }
}