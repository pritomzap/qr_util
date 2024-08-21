import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class QrScannerWidget extends StatefulWidget {
  const QrScannerWidget({Key? key}) : super(key: key);

  @override
  State<QrScannerWidget> createState() => _QrScannerWidgetState();
}

class _QrScannerWidgetState extends State<QrScannerWidget> {

  static const platform = MethodChannel('com.qr_util.qr_scanner');
  int? _textureId;
  Future<void> _createNativeScannerView() async {
    try {
      final result = await platform.invokeMethod('qr_scanner', 'https://facebook.com');
      setState(() {
        _textureId = result;
      });
    } on PlatformException catch (e) {
      print('Error creating native TextView: ${e.message}');
    }
  }

  @override
  void initState() {
    super.initState();
    //_createNativeScannerView();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 300,
      height: 300,
      child: AndroidView(
        viewType: 'com.qr_util.qr_scanner',
        onPlatformViewCreated: (id){

        },
      ),
    );
  }
}
