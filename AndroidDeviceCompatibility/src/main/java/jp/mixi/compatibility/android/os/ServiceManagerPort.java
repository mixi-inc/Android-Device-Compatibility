package jp.mixi.compatibility.android.os;

import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author KeithYokoma
 */
@SuppressWarnings("unused")
public final class ServiceManagerPort {
    public static final String TAG = ServiceManagerPort.class.getSimpleName();
    private static final String CLASS_SERVICE_MANAGER = "android.os.ServiceManager";
    private static final String METHOD_GET_SERVICE = "getService";

    private ServiceManagerPort() {
        throw new AssertionError();
    }

    public static IBinder tryGetServiceManagerBinder(String serviceName) {
        try {
            return getServiceManagerBinder(serviceName);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "class not found: ", e);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "method not found: ", e);
        } catch (InvocationTargetException e) {
            Log.e(TAG, "invocation target error: ", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "illegal access to the method: ", e);
        }
        return null;
    }

    public static IBinder getServiceManagerBinder(String serviceName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> serviceManager = ServiceManagerPort.class.getClassLoader().loadClass(CLASS_SERVICE_MANAGER);
        Method getService = serviceManager.getDeclaredMethod(METHOD_GET_SERVICE, String.class);
        if (!getService.isAccessible()) {
            getService.setAccessible(true);
        }
        return (IBinder) getService.invoke(null, serviceName);
    }
}

