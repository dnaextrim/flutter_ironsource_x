# IronSource Ads for Flutter

Flutter plugin for showing [IronSource](https://ironsrc.com) ads (Android only)
## Progress
- [x] Interstitial
- [ ] Banner (Still experimenting)
- [x] Offerwall
- [x] Rewarded video


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
*By default gdprConsent and ccpaConsent are true*

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
IronSourceBannerAd(keepAlive: true, listener: BannerAdListener())
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


## add Google Play Services
Add the following  inside the <application> tag in your AndroidManifest:
```xml
<meta-data android:name="com.google.android.gms.version"
android:value="@integer/google_play_services_version" />
```


please read [this](https://developer.android.com/google/play-services/setup.html) to add google play service

## Mediatin
follow [this](https://developers.ironsrc.com/ironsource-mobile/android/mediation-networks-android/) to add mediation sdks


## Using this plugin
see directory example

Visit [IronSource](https://developers.ironsrc.com/ironsource-mobile/android/android-sdk/) website to know more


## Contributing
Thanks to @karnadii & @DiMiTriFrog

I will continue updating this library.