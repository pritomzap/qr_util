package com.qr_util.qr_util

/**
 * @Created By Zarraf Ahmed
 * Created 20/8/24 at 11:31 PM
 */
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.zxing.*
import com.journeyapps.barcodescanner.*
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.platform.PlatformView
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DefaultDecoderFactory


class ScannerView internal constructor(
    val context: Context,
    messenger: BinaryMessenger,
    id: Int,
    val activity: Activity
) : PlatformView, MethodCallHandler {
    //private lateinit var qrScannerBinding: QrScannerLayoutBinding
    private var capture: CaptureManager? = null
    private var barcodeScannerView: DecoratedBarcodeView? = null
    private var viewfinderView: ViewfinderView? = null
   private val methodChannel: MethodChannel
    private var barcodeView: CustomFramingRectBarcodeView? = null
    var tc = TextView(context)

    init {
//        // Init WebView
//        //qrScannerBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.qr_scanner_layout, null, false);
//        barcodeScannerView = LayoutInflater.from(context).inflate(R.layout.qr_scanner_layout,null) as DecoratedBarcodeView?
//        viewfinderView = barcodeScannerView?.findViewById(R.id.zxing_viewfinder_view)
//        capture = CaptureManager(activity, barcodeScannerView)
//        capture!!.setShowMissingCameraPermissionDialog(false)
//        capture!!.decode()
//        barcodeScannerView!!.decodeSingle {
//            Log.i("QR_CODE", ":.............${it.result?.text} ")
//        }
//        changeMaskColor(null)
//        changeLaserVisibility(false)

//        barcodeView = BarcodeView(context)
//
//        // Configure ZXing scanner settings (e.g., formats, camera, etc.)
//
//        // Configure ZXing scanner settings (e.g., formats, camera, etc.)
//        barcodeView!!.decodeContinuous(BarcodeCallback { result: BarcodeResult? -> })
        methodChannel = MethodChannel(messenger, "com.qr_util.qr_scanner.view")
        methodChannel.setMethodCallHandler(this)
    }

    override fun getView(): View = initBarCodeView()

    private fun initBarCodeView(): CustomFramingRectBarcodeView {
        checkAndRequestPermission()
        var barcodeView = barcodeView

        if (barcodeView == null) {
            barcodeView = CustomFramingRectBarcodeView(activity).also {
                this.barcodeView = it
            }

            barcodeView.decoderFactory = DefaultDecoderFactory(null, null, null, 2)

            barcodeView.cameraSettings?.requestedCameraId = 1
        }

        startScan()
        return barcodeView
    }

    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        when (methodCall.method) {
            "qr_scanner" -> setText(methodCall, result)
            else -> result.notImplemented()
        }
    }

    // set and load new Url
    private fun setText(methodCall: MethodCall, result: MethodChannel.Result ) {
        val url = methodCall.arguments as String
        //webView.loadUrl(url)
        //textView.text = url
        result.success(null)
    }

    // Destroy WebView when PlatformView is destroyed
    override fun dispose() {

    }

    private fun changeMaskColor(view: View?) {
        viewfinderView!!.setMaskColor(Color.BLUE)
    }

    private fun changeLaserVisibility(visible: Boolean) {
        viewfinderView!!.setLaserVisibility(visible)
    }

    private fun startScan() {

        barcodeView?.decoderFactory = DefaultDecoderFactory(null, null, null, 2)

        barcodeView?.decodeContinuous(
            object : BarcodeCallback {
                override fun barcodeResult(result: BarcodeResult) {
//                    if (allowedBarcodeTypes.isEmpty() || allowedBarcodeTypes.contains(result.barcodeFormat)) {
//                        val code = mapOf(
//                            "code" to result.text,
//                            "type" to result.barcodeFormat.name,
//                            "rawBytes" to result.rawBytes
//                        )
//
//                        //channel.invokeMethod(CHANNEL_METHOD_ON_RECOGNIZE_QR, code)
//                    }
                }

                override fun possibleResultPoints(resultPoints: List<ResultPoint>) = Unit
            }
        )
    }

    private val hasCameraPermission: Boolean
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED

    private fun checkAndRequestPermission() {
        if (hasCameraPermission) {
            //channel.invokeMethod(CHANNEL_METHOD_ON_PERMISSION_SET, true)
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                1001
            )
        }
    }

}