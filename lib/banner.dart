import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'Ironsource_consts.dart';

class BannerSizeType {
  final String type;
  static const String BANNER = "BANNER";
  static const String LARGE = "LARGE";
  static const String RECTANGLE = "RECTANGLE";
  static const String LEADERBOARD = "LEADERBOARD";
  static const SMART = "SMART";
  static const CUSTOM = "CUSTOM";

  const BannerSizeType({this.type = "CUSTOM"});
}

class BannerSize {
  final String type;
  final int width;
  final int height;

  static const BannerSize BANNER =
      BannerSize(type: BannerSizeType.BANNER, width: 320, height: 50);
  static const BannerSize LARGE =
      BannerSize(type: BannerSizeType.LARGE, width: 320, height: 90);
  static const BannerSize RECTANGLE =
      BannerSize(type: BannerSizeType.RECTANGLE, width: 300, height: 250);
  static const BannerSize LEADERBOARD =
      BannerSize(type: BannerSizeType.LEADERBOARD, width: 728, height: 90);
  static const BannerSize SMART =
      BannerSize(type: BannerSizeType.SMART, width: 0, height: 0);
  const BannerSize(
      {this.type = BannerSizeType.CUSTOM, this.width = 320, this.height = 50});
}

class IronSourceBannerAd extends StatefulWidget {
  final Key? key;
  final IronSourceBannerListener? listener;
  final bool keepAlive;
  final BannerSize size;
  final Color? backgroundColor;

  IronSourceBannerAd({
    this.key,
    this.listener,
    this.keepAlive = false,
    this.size = BannerSize.BANNER,
    this.backgroundColor,
  }) : super(key: key);

  @override
  _IronSourceBannerAdState createState() => _IronSourceBannerAdState();
}

class _IronSourceBannerAdState extends State<IronSourceBannerAd>
    with AutomaticKeepAliveClientMixin {
  static IronSourceBannerListener? _listener;
  static IronSourceBannerListener? getListener() {
    return _listener!;
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);
    if (defaultTargetPlatform == TargetPlatform.android) {
      return Container(
          color: widget.backgroundColor,
          width: widget.size.width.toDouble(),
          height: widget.size.height.toDouble(),
          child: AndroidView(
            key: UniqueKey(),
            viewType: BANNER_AD_CHANNEL,
            onPlatformViewCreated: _onBannerAdViewCreated,
            creationParams: <String, dynamic>{
              "banner_type": widget.size.type,
              "height": widget.size.height,
              "width": widget.size.width,
            },
            creationParamsCodec: StandardMessageCodec(),
          ));
    } else {
      return Container(child: Text("this plugin only supported for android"));
    }
  }

  void _onBannerAdViewCreated(int id) async {
    final channel = MethodChannel('$BANNER_AD_CHANNEL$id');
    _listener = widget.listener!;
    channel.setMethodCallHandler(_listener?._handle);
  }

  @override
  bool get wantKeepAlive => widget.keepAlive;
}

abstract class IronSourceBannerListener {
  Future<dynamic> _handle(MethodCall call) async {
    if (call.method == ON_BANNER_AD_CLICKED)
      onBannerAdClicked();
    else if (call.method == ON_BANNER_AD_LEFT_APPLICATION)
      onBannerAdLeftApplication();
    else if (call.method == ON_BANNER_AD_LOAD_FAILED)
      onBannerAdLoadFailed(call.arguments);
    else if (call.method == ON_BANNER_AD_LOADED)
      onBannerAdLoaded();
    else if (call.method == ON_BANNER_AD_sCREEN_DISMISSED)
      onBannerAdScreenDismissed();
    else if (call.method == ON_BANNER_AD_SCREEN_PRESENTED)
      onBannerAdScreenPresented();
  }

//  Banner
  void onBannerAdLeftApplication();

  void onBannerAdScreenDismissed();

  void onBannerAdScreenPresented();

  void onBannerAdClicked();

  void onBannerAdLoaded();

  void onBannerAdLoadFailed(Map<String, dynamic> error);
}
