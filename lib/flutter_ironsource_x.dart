
import 'dart:async';

import 'package:flutter/services.dart';

class FlutterIronsource_x {
  static const MethodChannel _channel =
      const MethodChannel('flutter_ironsource_x');

  static FutureOr<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
