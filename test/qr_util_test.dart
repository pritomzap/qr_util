import 'package:flutter_test/flutter_test.dart';
import 'package:qr_util/qr_util.dart';
import 'package:qr_util/qr_util_platform_interface.dart';
import 'package:qr_util/qr_util_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockQrUtilPlatform
    with MockPlatformInterfaceMixin
    implements QrUtilPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final QrUtilPlatform initialPlatform = QrUtilPlatform.instance;

  test('$MethodChannelQrUtil is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelQrUtil>());
  });

  test('getPlatformVersion', () async {
    QrUtil qrUtilPlugin = QrUtil();
    MockQrUtilPlatform fakePlatform = MockQrUtilPlatform();
    QrUtilPlatform.instance = fakePlatform;

    expect(await qrUtilPlugin.getPlatformVersion(), '42');
  });
}
