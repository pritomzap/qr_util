package com.qr_util.qr_util

import android.app.Activity
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

/** QrUtilPlugin */
class QrUtilPlugin: FlutterPlugin,ActivityAware{

  private var activity: Activity? = null
  private lateinit var pluginBinding:FlutterPlugin.FlutterPluginBinding
  override fun onAttachedToEngine(b: FlutterPlugin.FlutterPluginBinding) {
    pluginBinding = b
  }

  /*
  * onDetachedFromEngine: should release all resources in this method
  * https://api.flutter.dev/javadoc/io/flutter/embedding/engine/plugins/FlutterPlugin.html
  * */
  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    /*
    * Eg: .setMethodCallHandler(null), setStreamHandler(null) etc
    * */
    TODO("Not yet implemented")
  }

  override fun onDetachedFromActivity() {
    activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
    pluginBinding.platformViewRegistry.registerViewFactory(
      "com.qr_util.qr_scanner", ScannerViewFactory(pluginBinding.binaryMessenger,activity!!))
  }

  override fun onDetachedFromActivityForConfigChanges() {
    activity = null
  }
}
