import Flutter
import UIKit

public class SwiftFlutterIronsource_xPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_ironsource_x", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterIronsource_xPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
