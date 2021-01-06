package com.metamorfosis_labs.flutter_ironsource_x

object IronSourceConsts {
    const val MAIN_CHANNEL = "com.metamorfosis_labs.flutter_ironsource_x"
    const val BANNER_AD_CHANNEL = "$MAIN_CHANNEL/bannerAd"
    const val INTERSTITIAL_CHANNEL = "$MAIN_CHANNEL/interstitialAd"
    const val INIT = "initialize"
    const val LOAD_INTERSTITIAL = "loadInterstitial"
    const val SHOW_INTERSTITIAL = "showInterstitial"
    const val IS_INTERSTITIAL_READY = "isInterstitialReady"
    const val IS_REWARDED_VIDEO_AVAILABLE = "isRewardedVideoAvailable"
    const val IS_OFFERWALL_AVAILABLE = "isOfferwallAvailable"
    const val SHOW_OFFERWALL = "showOfferwall"
    const val SHOW_REWARDED_VIDEO = "showRewardedVideo"

    //    Listener
    const val ON_REWARDED_VIDEO_AD_OPENED = "onRewardedVideoAdOpened"
    const val ON_REWARDED_VIDEO_AD_CLOSED = "onRewardedVideoAdClosed"
    const val ON_REWARDED_VIDEO_AVAILABILITY_CHANGED = "onRewardedVideoAvailabilityChanged"
    const val ON_REWARDED_VIDEO_AD_STARTED = "onRewardedVideoAdStarted"
    const val ON_REWARDED_VIDEO_AD_ENDED = "onRewardedVideoAdEnded"
    const val ON_REWARDED_VIDEO_AD_REWARDED = "onRewardedVideoAdRewarded"
    const val ON_REWARDED_VIDEO_AD_SHOW_FAILED = "onRewardedVideoAdShowFailed"
    const val ON_REWARDED_VIDEO_AD_CLICKED = "onRewardedVideoAdClicked"

    // Offerwall listener
    const val ON_OFFERWALL_AVAILABLE = "onOfferwallAvailable"
    const val ON_OFFERWALL_OPENED = "onOfferwallOpened"
    const val ON_OFFERWALL_SHOW_FAILED = "onOfferwallShowFailed"
    const val ON_OFFERWALL_AD_CREDITED = "onOfferwallAdCredited"
    const val ON_OFFERWALL_CREDITS_FAILED = "onOfferwallCreditsFailed"
    const val ON_OFFERWALL_CLOSED = "onOfferwallClosed"

    //    Interstitial Listener
    const val ON_INTERSTITIAL_AD_OPENED = "onInterstitialAdOpened"
    const val ON_INTERSTITIAL_AD_READY = "onInterstitialAdReady"
    const val ON_INTERSTITIAL_AD_CLOSED = "onInterstitialAdClosed"
    const val ON_INTERSTITIAL_AD_LOAD_FAILED = "onInterstitialAdLoadFailed"
    const val ON_INTERSTITIAL_AD_SHOW_FAILED = "onInterstitialAdShowFailed"
    const val ON_INTERSTITIAL_AD_SHOW_SUCCEEDED = "onInterstitialAdShowSucceeded"
    const val ON_INTERSTITIAL_AD_CLICKED = "onInterstitialAdClicked"

    // Banner listener const
    const val ON_BANNER_AD_LOADED = "onBannerAdLoaded"
    const val ON_BANNER_AD_CLICKED = "onBannerAdClicked"
    const val ON_BANNER_AD_SCREEN_PRESENTED = "onBannerAdScreenPresented"
    const val ON_BANNER_AD_sCREEN_DISMISSED = "onBannerAdScreenDismissed"
    const val ON_BANNER_AD_LEFT_APPLICATION = "onBannerAdLeftApplication"
    const val ON_BANNER_AD_LOAD_FAILED = "onBannerAdLoadFailed"
}