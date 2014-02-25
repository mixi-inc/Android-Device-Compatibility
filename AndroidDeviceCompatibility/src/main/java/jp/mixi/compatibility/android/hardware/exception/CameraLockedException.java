package jp.mixi.compatibility.android.hardware.exception;

/**
 * @author keishin.yokomaku
 */
public class CameraLockedException extends Exception {
    private static final long serialVersionUID = 6897148507275774935L;

    public CameraLockedException() {
        super();
    }

    public CameraLockedException(String detailMessage) {
        super(detailMessage);
    }

    public CameraLockedException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CameraLockedException(Throwable throwable) {
        super(throwable);
    }
}
