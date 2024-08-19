
import 'qr_util_platform_interface.dart';

class QrUtil {
  Future<String?> getPlatformVersion() {
    return QrUtilPlatform.instance.getPlatformVersion();
  }
}
