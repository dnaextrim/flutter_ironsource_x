# flutter_Ironsource_x Ver. 2

## Banner

![Banner](https://images.metamorfosis-labs.com/public/uploads/small/91eb73ae0820bc02c5444c5f901d262a@2x.png)

## Interstitial/Rewarded Video

![Interstitial/Rewarded Video](https://images.metamorfosis-labs.com/public/uploads/small/76ad3756fc66437d0b555054396dbc3d@2x.png)

## Offerwall

![Offerwall](https://images.metamorfosis-labs.com/public/uploads/small/6bafb6a0965026111f2ea59baa772a6a@2x.png)

---

In the next version, the version format will be like this

```
xx.xx.xxxx
 🠕  🠕  🠕
 |  |  Publish Revision
 |  IronSource SDK Major Version
 Dart Major Version

 Example:
 2.7.1
 ------------------------------
 2 is Dart Major Version
 7 is IronSource Major Version
 1 is Publish Revision
```

# IronSource Ads for Flutter

Flutter plugin for showing [IronSource](https://ironsrc.com) ads (Android only)

If this package was helpful to you in delivering on your project or you just wanna to support this project, a cup of coffee would be highly appreciated ;-)

```
Please support me so I can continue to develop this Plugin. Please support me through Buy me a coffee
```

[![Buy me a coffee](https://cdn.buymeacoffee.com/buttons/default-green.png)](https://www.buymeacoffee.com/dnaextrim)

## Progress

- ✅ Interstitial
- ✅ Banner (Still experimenting)
- ✅ Offerwall
- ✅ Rewarded video

# Using Examples

## Init

```dart
  void init() async {
    var userId = await IronSource.getAdvertiserId();
    await IronSource.validateIntegration();
    await IronSource.setUserId(userId);
    await IronSource.initialize(appKey: "appKey", listener: this,
                      gdprConsent: true, ccpaConsent: false);
    rewardeVideoAvailable = await IronSource.isRewardedVideoAvailable();
    offerwallAvailable = await IronSource.isOfferwallAvailable();
    setState(() {});
  }

```

_By default gdprConsent and ccpaConsent are true_

## Interstitial

```dart
    IronSource.loadInterstitial();
```

```dart
  void showInterstitial() async {
    if (await IronSource.isInterstitialReady()) {
      IronSource.showInterstitial();
    } else {
      print(
        "Interstial is not ready. use 'Ironsource.loadInterstial' before showing it",
      );
    }
  }
```

```dart
  @override
  void onInterstitialAdClicked() {
    print("onInterstitialAdClicked");
  }

  @override
  void onInterstitialAdClosed() {
    print("onInterstitialAdClosed");
  }

  @override
  void onInterstitialAdLoadFailed(IronSourceError error) {
      print("onInterstitialAdLoadFailed : ${error.toString()}");
  }

  @override
  void onInterstitialAdOpened() {
    print("onInterstitialAdOpened");
    setState(() {
      interstitialReady = false;
    });


  }

  @override
  void onInterstitialAdReady() {
    print("onInterstitialAdReady");
    setState(() {
      interstitialReady = true;
    });

  }

  @override
  void onInterstitialAdShowFailed(IronSourceError error) {

    print("onInterstitialAdShowFailed : ${error.toString()}");
    setState(() {
      interstitialReady = false;
    });
  }

  @override
  void onInterstitialAdShowSucceeded() {
    print("nInterstitialAdShowSucceeded");
```

## Reward Video

```dart
  void showRewardedVideo() async {
    if (await IronSource.isRewardedVideoAvailable()) {
      IronSource.showRewardedVideol();
    } else {
      print("RewardedVideo not available");
    }
  }
```

```dart
  @override
  void onRewardedVideoAdClicked(Placement placement) {

    print("onRewardedVideoAdClicked");
  }

  @override
  void onRewardedVideoAdClosed() {
    print("onRewardedVideoAdClosed");

  }

  @override
  void onRewardedVideoAdEnded() {
    print("onRewardedVideoAdEnded");
  }

  @override
  void onRewardedVideoAdOpened() {
    print("onRewardedVideoAdOpened");

  }

  @override
  void onRewardedVideoAdRewarded(Placement placement) {

    print("onRewardedVideoAdRewarded: ${placement.placementName}");
  }

  @override
  void onRewardedVideoAdShowFailed(IronSourceError error) {

    print("onRewardedVideoAdShowFailed : ${error.toString()}");
  }

  @override
  void onRewardedVideoAdStarted() {
    print("onRewardedVideoAdStarted");
  }

  @override
  void onRewardedVideoAvailabilityChanged(bool available) {

    print("onRewardedVideoAvailabilityChanged : $available");
    setState(() {
      rewardeVideoAvailable = available;
    });
  }
```

## Banner

```dart
IronSourceBannerAd(keepAlive: true, listener: BannerAdListener());
```

### **Banner Size Type:**

- **BANNER**

```dart
IronSourceBannerAd(keepAlive: true, listener: BannerAdListener(), size: BannerSize.BANNER);
```

- **LARGE**

```dart
IronSourceBannerAd(keepAlive: true, listener: BannerAdListener(), size: BannerSize.LARGE);
```

- **LEADERBOARD**

```dart
IronSourceBannerAd(keepAlive: true, listener: BannerAdListener(), size: BannerSize.LEADERBOARD);
```

- **RECTANGLE**

```dart
IronSourceBannerAd(keepAlive: true, listener: BannerAdListener(), size: BannerSize.RECTANGLE);
```

- **SMART**

```dart
IronSourceBannerAd(keepAlive: true, listener: BannerAdListener(), size: BannerSize.SMART);
```

- **CUSTOM**

```dart
IronSourceBannerAd(
  keepAlive: true,
  listener: BannerAdListener(),
  size: BannerSize.BANNER,
  size: BannerSize(
      type: BannerSizeType.BANNER,
      width: 400,
      height: 50,
    ),
);
```

### **Banner Background Color**

```dart
IronSourceBannerAd(
  keepAlive: true,
  listener: BannerAdListener(),
  size: BannerSize.BANNER,
  backgroundColor: Colors.amber, //Background Color
);
```

```dart
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
```

## OfferWall

```dart

  void showOfferwall() async {
    if (await IronSource.isOfferwallAvailable()) {
      IronSource.showOfferwall();
    } else {
      print("Offerwall not available");
    }
  }
```

```dart
  @override
  void onGetOfferwallCreditsFailed(IronSourceError error) {

    print("onGetOfferwallCreditsFailed : ${error.toString()}");
  }

  @override
  void onOfferwallAdCredited(OfferwallCredit reward) {

    print("onOfferwallAdCredited : $reward");
  }

  @override
  void onOfferwallAvailable(bool available) {
    print("onOfferwallAvailable : $available");

    setState(() {
      offerwallAvailable = available;
    });
  }

  @override
  void onOfferwallClosed() {
    print("onOfferwallClosed");
  }

  @override
  void onOfferwallOpened() {
    print("onOfferwallOpened");
  }

  @override
  void onOfferwallShowFailed(IronSourceError error) {
    print("onOfferwallShowFailed ${error.toString()}");
  }
```

## Update AndroidManifest.xml

### Manifest Permissions

Add the following permissions to your AndroidManifest.xml file inside the manifest tag but outside the `<application>` tag:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### Manifest Activities

Add the following activities inside the `<application>` tag in your AndroidManifest:

```xml
<activity
            android:name="com.ironsource.sdk.controller.ControllerActivity"
            android:configChanges="orientation|screenSize"
            android:hardwareAccelerated="true" />
<activity
            android:name="com.ironsource.sdk.controller.InterstitialActivity"
            android:configChanges="orientation|screenSize"
            android:hardwareAccelerated="true"
            android:theme="@android:style/Theme.Translucent" />
<activity
            android:name="com.ironsource.sdk.controller.OpenUrlActivity"
            android:configChanges="orientation|screenSize"
            android:hardwareAccelerated="true"
            android:theme="@android:style/Theme.Translucent" />
```

## Setting `android/app/build.gradle`:

```gradle
dependencies {
  implementation 'com.ironsource.sdk:mediationsdk:7.2.1'
  ...
}
```

Change `compileSdkVersion` to minimum is `31`

```gradle
android {
    compileSdkVersion 31
    ...
```

Change `minSdkVersion` to minimum is `21` and `targetSdkkVersion` to minimum is 31

```gradle
defaultConfig {
        ...
        minSdkVersion 21
        targetSdkVersion 31
        versionCode flutterVersionCode.toInteger()
        versionName flutterVersionName
    }
```

## Setting `android/build.gradle`

Change `ext.kotlin_version` to minimum is `1.6.10`

```gradle
buildscript {
    ext.kotlin_version = '1.6.10'
    ...
```

## add Google Play Services

Add `xmlns:tools="http://schemas.android.com/tools"` on top for replace label.

```xml
  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.metamorfosis_labs.flutter_ironsource_x_example">
```

Add `tools:replace="android:label"` at the `<application>` tag

```xml
<application
        tools:replace="android:label"
        android:name="io.flutter.app.FlutterApplication"
        android:label="flutter_ironsource_x_example"
        android:networkSecurityConfig="@xml/network_security_config"
        android:usesCleartextTraffic="true"
        android:icon="@mipmap/ic_launcher">
```

Add the following inside the `<application>` tag in your AndroidManifest:

```xml
<meta-data
  android:name="com.google.android.gms.ads.APPLICATION_ID"
  android:value="ca-app-pub-xxxxxxxxxxxxxxxx~yyyyyyyyyy"/>

<meta-data android:name="com.google.android.gms.version"
  android:value="@integer/google_play_services_version" />
```

please read [this](https://developer.android.com/google/play-services/setup.html) to add google play service

# Note

```
Make sure each widget for the Ironsource Plugin is placed on a different widget from the main or screen widget, this is to avoid collisions with the banner widget if you want the banner widget to work. For more details on how to use please see the sample source code
```

## Mediation

follow [this](https://developers.ironsrc.com/ironsource-mobile/android/mediation-networks-android/) to add mediation sdks

## Using this plugin

see directory example

Visit [IronSource](https://developers.ironsrc.com/ironsource-mobile/android/android-sdk/) website to know more

## Contributing

Thanks to @karnadii & @DiMiTriFrog

I will continue updating this library.

## Support

If this package was helpful to you in delivering on your project or you just wanna to support this project, a cup of coffee would be highly appreciated ;-)

[![Buy me a coffee](https://cdn.buymeacoffee.com/buttons/default-green.png)](https://www.buymeacoffee.com/dnaextrim)
