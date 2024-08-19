import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'qr_util_platform_interface.dart';

/// An implementation of [QrUtilPlatform] that uses method channels.
class MethodChannelQrUtil extends QrUtilPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('qr_util');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
