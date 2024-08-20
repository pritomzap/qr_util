package com.qr_util.qr_util

import io.flutter.embedding.engine.plugins.FlutterPlugin

/** QrUtilPlugin */
class QrUtilPlugin: FlutterPlugin{
  override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    binding.platformViewRegistry.registerViewFactory(
      "com.qr_util.qr_scanner", ScannerViewFactory(binding.binaryMessenger))
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
}
