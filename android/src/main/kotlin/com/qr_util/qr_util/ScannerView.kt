package com.qr_util.qr_util

/**
 * @Created By Zarraf Ahmed
 * Created 20/8/24 at 11:31 PM
 */
import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import com.qr_util.qr_util.databinding.QrScannerLayoutBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.platform.PlatformView


class ScannerView internal constructor(
    context: Context,
    messenger: BinaryMessenger,
    id: Int
) :
    PlatformView, MethodCallHandler {
    private lateinit var qrScannerBinding: QrScannerLayoutBinding
    private val methodChannel: MethodChannel
    override fun getView(): View {
        return qrScannerBinding.root
    }

    init {
        // Init WebView
        qrScannerBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.qr_scanner_layout, null, false);
        methodChannel = MethodChannel(messenger, "com.qr_util.qr_scanner")
        methodChannel.setMethodCallHandler(this)
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

}