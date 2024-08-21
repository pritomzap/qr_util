
import 'package:flutter/widgets.dart';
import 'package:qr_util/src/qr_scanner_widget.dart';

import 'qr_util_platform_interface.dart';

class QrUtil {
  Future<String?> getPlatformVersion() {
    return Future.value("1111");
  }

  Widget getQrScannerWidget() => const QrScannerWidget();

}
