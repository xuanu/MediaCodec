package apk.cn.zeffect.mediacodec

import android.app.Activity
import android.hardware.Camera
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import android.widget.CheckBox
import apk.cn.zeffect.mediacodec.camera.CameraUtils
import org.jetbrains.anko.find

class MainActivity : Activity(), SurfaceHolder.Callback {
    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
    }

    private val mRecordBtn by lazy { find<CheckBox>(R.id.record) }
    private val mSurf by lazy { find<SurfaceView>(R.id.m_camera) }
    private val mCarmeraUtils by lazy { CameraUtils() }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        mRecordBtn.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                compoundButton.text = "停止"
                mCarmeraUtils.openCamera(Camera.CameraInfo.CAMERA_FACING_BACK, mSurf.holder)
            } else {
                compoundButton.text = "录制"
                mCarmeraUtils.stop()
            }
        }
        mSurf.holder.addCallback(this)
    }

}
