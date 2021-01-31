import 'dart:async';

import 'package:flutter/services.dart';
import 'Ironsource_consts.dart';
import 'models.dart';
export 'banner.dart';

class IronSource {
  static const MethodChannel _channel =
      MethodChannel("com.metamorfosis_labs.flutter_ironsource_x");
  static IronSourceListener _listener;
  static IronSourceListener getListener() {
    return _listener;
  }

  static Future<Null> initialize(
      {final String appKey,
      final IronSourceListener listener,
      bool gdprConsent = true,
      bool ccpaConsent = true}) async {
    _listener = listener;
    _channel.setMethodCallHandler(_listener._handle);
    await _channel.invokeMethod('initialize', {
      'appKey': appKey,
      'gdprConsent': gdprConsent,
      'ccpaConsent': ccpaConsent
    });
  }

  static Future<Null> shouldTrackNetworkState(bool state) async {
    await _channel.invokeMethod('shouldTrackNetworkState', {'state': state});
  }

  static Future<Null> validateIntegration() async {
    await _channel.invokeMethod('validateIntegration');
  }

  static Future<Null> setUserId(String userId) async {
    await _channel.invokeMethod('setUserId', {'userId': userId});
  }

  static Future<String> getAdvertiserId() async {
    return await _channel.invokeMethod('getAdvertiserId');
  }

  static Future<Null> loadInterstitial() async {
    await _channel.invokeMethod('loadInterstitial');
  }

  static Future<Null> showInterstitial() async {
    await _channel.invokeMethod('showInterstitial');
  }

  static Future<Null> showRewardedVideo() async {
    await _channel.invokeMethod('showRewardedVideo');
  }

  static Future<Null> showOfferwall() async {
    await _channel.invokeMethod('showOfferwall');
  }

  static Future<bool> isInterstitialReady() async {
    return await _channel.invokeMethod('isInterstitialReady');
  }

  static Future<Null> activityResumed() async {
    await _channel.invokeMethod('activityResumed');
  }

  static Future<Null> activityPaused() async {
    await _channel.invokeMethod('activityPaused');
  }

  static Future<bool> isRewardedVideoAvailable() async {
    return await _channel.invokeMethod('isRewardedVideoAvailable');
  }

  static Future<bool> isOfferwallAvailable() async {
    return await _channel.invokeMethod('isOfferwallAvailable');
  }
}

abstract class IronSourceListener {
  Future<Null> _handle(MethodCall call) async {
//    Rewarded
    if (call.method == ON_REWARDED_VIDEO_AD_CLICKED)
      onRewardedVideoAdClicked(Placement(
          placementId: call.arguments["placementid"],
          placementName: call.arguments["placementName"],
          rewardAmount: call.arguments["rewardAmount"],
          rewardName: call.arguments["rewardName"]));
    else if (call.method == ON_REWARDED_VIDEO_AD_CLOSED)
      onRewardedVideoAdClosed();
    else if (call.method == ON_REWARDED_VIDEO_AD_ENDED)
      onRewardedVideoAdEnded();
    else if (call.method == ON_REWARDED_VIDEO_AD_OPENED)
      onRewardedVideoAdOpened();
    else if (call.method == ON_REWARDED_VIDEO_AD_REWARDED)
      onRewardedVideoAdRewarded(Placement(
          placementId: call.arguments["placementid"],
          placementName: call.arguments["placementName"],
          rewardAmount: call.arguments["rewardAmount"],
          rewardName: call.arguments["rewardName"]));
    else if (call.method == ON_REWARDED_VIDEO_AD_SHOW_FAILED)
      onRewardedVideoAdShowFailed(
        IronSourceError(
            errorCode: call.arguments["errorCode"],
            errorMessage: call.arguments["errorMessage"]),
      );
    else if (call.method == ON_REWARDED_VIDEO_AVAILABILITY_CHANGED)
      onRewardedVideoAvailabilityChanged(call.arguments);
    else if (call.method == ON_REWARDED_VIDEO_AD_STARTED)
      onRewardedVideoAdStarted();
// Offerwall
    else if (call.method == ON_OFFERWALL_AD_CREDITED)
      onOfferwallAdCredited(OfferwallCredit(
          credits: call.arguments["credits"],
          totalCredits: call.arguments["totalCredits"],
          totalCreditsFlag: call.arguments["totalCreditsFlag"]));
    else if (call.method == ON_OFFERWALL_AVAILABLE)
      onOfferwallAvailable(call.arguments);
    else if (call.method == ON_OFFERWALL_CLOSED)
      onOfferwallClosed();
    else if (call.method == ON_OFFERWALL_CREDITS_FAILED)
      onGetOfferwallCreditsFailed(IronSourceError(
          errorCode: call.arguments["errorCode"],
          errorMessage: call.arguments["errorMessage"]));
    else if (call.method == ON_OFFERWALL_OPENED)
      onOfferwallOpened();
    else if (call.method == ON_OFFERWALL_SHOW_FAILED)
      onOfferwallShowFailed(IronSourceError(
          errorCode: call.arguments["errorCode"],
          errorMessage: call.arguments["errorMessage"]));
//    interstitial
    else if (call.method == ON_INTERSTITIAL_AD_CLICKED)
      onInterstitialAdClicked();
    else if (call.method == ON_INTERSTITIAL_AD_CLOSED)
      onInterstitialAdClosed();
    else if (call.method == ON_INTERSTITIAL_AD_OPENED)
      onInterstitialAdOpened();
    else if (call.method == ON_INTERSTITIAL_AD_READY)
      onInterstitialAdReady();
    else if (call.method == ON_INTERSTITIAL_AD_SHOW_SUCCEEDED)
      onInterstitialAdShowSucceeded();
    else if (call.method == ON_INTERSTITIAL_AD_LOAD_FAILED)
      onInterstitialAdLoadFailed(IronSourceError(
          errorCode: call.arguments["errorCode"],
          errorMessage: call.arguments["errorMessage"]));
    else if (call.method == ON_INTERSTITIAL_AD_SHOW_FAILED)
      onInterstitialAdShowFailed(IronSourceError(
          errorCode: call.arguments["errorCode"],
          errorMessage: call.arguments["errorMessage"]));
  }

  //  Rewarded video
  void onRewardedVideoAdOpened();

  void onRewardedVideoAdClosed();

  void onRewardedVideoAvailabilityChanged(bool available);

  void onRewardedVideoAdStarted();

  void onRewardedVideoAdEnded();

  void onRewardedVideoAdRewarded(Placement placement);

  void onRewardedVideoAdShowFailed(IronSourceError error);

  void onRewardedVideoAdClicked(Placement placement);

  // Offer wall
  void onOfferwallAvailable(bool available);

  void onOfferwallOpened();

  void onOfferwallShowFailed(IronSourceError error);

  void onOfferwallAdCredited(OfferwallCredit reward);

  void onGetOfferwallCreditsFailed(IronSourceError error);

  void onOfferwallClosed();

  // Interstitial
  void onInterstitialAdClicked();

  void onInterstitialAdReady();

  void onInterstitialAdLoadFailed(IronSourceError error);

  void onInterstitialAdOpened();

  void onInterstitialAdClosed();

  void onInterstitialAdShowSucceeded();

  void onInterstitialAdShowFailed(IronSourceError error);
}
