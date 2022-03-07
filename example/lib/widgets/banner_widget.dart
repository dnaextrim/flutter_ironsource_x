import 'package:flutter/material.dart';
import 'package:flutter_ironsource_x/banner.dart';
import 'package:flutter_ironsource_x_example/providers/banner_providers.dart';
import 'package:provider/provider.dart';

import 'custom_button.dart';

class BannerWidget extends StatefulWidget {
  const BannerWidget({Key? key}) : super(key: key);

  @override
  State<BannerWidget> createState() => _BannerWidgetState();
}

class _BannerWidgetState extends State<BannerWidget> {
  @override
  Widget build(BuildContext context) {
    return Consumer<BannerProvider>(builder: (context, bannerProv, child) {
      print('Banner');
      return CustomButton(
        label: bannerProv.isBannerShow ? "hide banner" : "Show Banner",
        onPressed: () {
          bannerProv.isBannerShow = !bannerProv.isBannerShow;
        },
      );
    });
  }
}

class BannerAdListener extends IronSourceBannerListener {
  @override
  void onBannerAdClicked() {
    print("onBannerAdClicked");
  }

  @override
  void onBannerAdLeftApplication() {
    print("onBannerAdLeftApplication");
  }

  @override
  void onBannerAdLoadFailed(Map<String, dynamic> error) {
    print("onBannerAdLoadFailed");
  }

  @override
  void onBannerAdLoaded() {
    print("onBannerAdLoaded");
  }

  @override
  void onBannerAdScreenDismissed() {
    print("onBannerAdScreenDismisse");
  }

  @override
  void onBannerAdScreenPresented() {
    print("onBannerAdScreenPresented");
  }
}
