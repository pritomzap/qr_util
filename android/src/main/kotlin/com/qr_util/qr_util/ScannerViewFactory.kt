package com.qr_util.qr_util


import android.app.Activity
import android.content.Context
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
/**
 * @Created By Zarraf Ahmed
 * Created 20/8/24 at 11:30 PM
 */
class ScannerViewFactory (private val messenger: BinaryMessenger,private val activity: Activity) :
    PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    override fun create(context: Context, id: Int, o: Any?): PlatformView {
        return ScannerView(context, messenger, id,activity)
    }
}