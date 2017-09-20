package apk.cn.zeffect.mediacodec.camera

import android.graphics.ImageFormat
import android.hardware.Camera
import android.view.SurfaceHolder


/**
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/09/20
 *      desc    ：
 *      version:：1.0
 * </pre>
 * @author zzx
 */
class CameraUtils {
    private var mCamera: Camera? = null
    private var mParam: Camera.Parameters? = null

    fun openCamera(cameraId: Int = Camera.CameraInfo.CAMERA_FACING_BACK, holder: SurfaceHolder) {
        stop()
        if (getNumbers() < 1) return
        try {
            mCamera = Camera.open(cameraId)
        } catch (e: Exception) {
            mCamera = null
        } finally {
            if (mCamera != null) {
                if (mParam == null) {
                    mParam = mCamera?.parameters
                }
                mParam?.previewFormat = ImageFormat.NV21//预览格式NV21/YV12
                val spSize = mCamera?.parameters?.supportedPreviewSizes//支持的尺寸
                mParam?.setPreviewSize(480, 640)
                mCamera?.parameters = mParam
                mCamera?.setPreviewDisplay(holder)
                mCamera?.startPreview()
            }
        }
    }


    fun stop() {
        mCamera?.setPreviewDisplay(null)
        mCamera?.stopPreview()
        mCamera?.release()
        mCamera = null
    }

    fun getNumbers() = Camera.getNumberOfCameras()


}