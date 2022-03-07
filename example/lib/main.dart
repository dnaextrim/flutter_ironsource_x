import 'package:flutter/material.dart';

import 'package:flutter_ironsource_x/ironsource.dart';
import 'package:flutter_ironsource_x_example/providers/banner_providers.dart';
import 'package:flutter_ironsource_x_example/widgets/banner_widget.dart';
import 'package:flutter_ironsource_x_example/widgets/button_ads.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => BannerProvider()),
      ],
      child: MaterialApp(
        home: Scaffold(
          appBar: AppBar(
            centerTitle: true,
            title: const Text('Flutter IronSource X Demo'),
          ),
          body: Stack(
            children: <Widget>[
              Container(
                padding: const EdgeInsets.symmetric(vertical: 50.0),
                alignment: Alignment.center,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: const <Widget>[
                    ButtonAdsWidget(),
                    BannerWidget(),
                  ],
                ),
              ),
              // Banner ad
              Consumer<BannerProvider>(builder: (context, bannerProv, child) {
                return bannerProv.isBannerShow
                    ? Align(
                        alignment: Alignment.bottomCenter,
                        child: IronSourceBannerAd(
                          keepAlive: true,
                          listener: BannerAdListener(),
                          size: BannerSize.BANNER,
                          // size: BannerSize.LARGE,
                          // size: BannerSize.LEADERBOARD,
                          // size: BannerSize.RECTANGLE,
                          // size: BannerSize.SMART,
                          /* size: BannerSize(
                          type: BannerSizeType.BANNER,
                          width: 400,
                          height: 50,
                        ), */
                          // backgroundColor: Colors.amber,
                        ),
                      )
                    : const SizedBox();
              }),
            ],
          ),
        ),
      ),
    );
  }
}
