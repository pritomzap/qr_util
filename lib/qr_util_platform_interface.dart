import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'qr_util_method_channel.dart';

abstract class QrUtilPlatform extends PlatformInterface {
  /// Constructs a QrUtilPlatform.
  QrUtilPlatform() : super(token: _token);

  static final Object _token = Object();

  static QrUtilPlatform _instance = MethodChannelQrUtil();

  /// The default instance of [QrUtilPlatform] to use.
  ///
  /// Defaults to [MethodChannelQrUtil].
  static QrUtilPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [QrUtilPlatform] when
  /// they register themselves.
  static set instance(QrUtilPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
