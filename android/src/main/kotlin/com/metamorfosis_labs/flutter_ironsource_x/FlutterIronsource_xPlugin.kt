package com.metamorfosis_labs.flutter_ironsource_x

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.ironsource.adapters.supersonicads.SupersonicConfig
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.integration.IntegrationHelper
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.model.Placement
import com.ironsource.mediationsdk.sdk.InterstitialListener
import com.ironsource.mediationsdk.sdk.OfferwallListener
import com.ironsource.mediationsdk.sdk.RewardedVideoListener

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import java.util.*

/** FlutterIronsource_xPlugin */
class FlutterIronsource_xPlugin() : FlutterPlugin, MethodCallHandler, ActivityAware, InterstitialListener, RewardedVideoListener, OfferwallListener {
  private lateinit var mActivity : Activity
  private lateinit var mChannel : MethodChannel
  private lateinit var messenger: BinaryMessenger
  private lateinit var flutterPluginBinding: FlutterPlugin.FlutterPluginBinding

  val TAG = "IronsourcePlugin"
  var APP_KEY = ""
  lateinit var mPlacement: Placement
  val FALLBACK_USER_ID = "userId"
/*  var mActivity: Activity
  var mChannel: MethodChannel*/

  /*init {
    this.mActivity = activity
    this.mChannel = channel
  }*/

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == IronSourceConsts.INIT && call.hasArgument("appKey")) {
      call.argument<String>("")
      initialize(call.argument<String>("appKey")!!, call.argument<Boolean>("gdprConsent")!!, call.argument<Boolean>("ccpaConsent")!!)
      result.success(null)
    } else if (call.method == IronSourceConsts.LOAD_INTERSTITIAL) {
      IronSource.loadInterstitial()
      result.success(null)
    } else if (call.method == IronSourceConsts.SHOW_INTERSTITIAL) {
      IronSource.showInterstitial()
      result.success(null)
    } else if (call.method == IronSourceConsts.IS_INTERSTITIAL_READY) {
      result.success(IronSource.isInterstitialReady())
    } else if (call.method == IronSourceConsts.IS_REWARDED_VIDEO_AVAILABLE) {
      result.success(IronSource.isRewardedVideoAvailable())
    } else if (call.method == IronSourceConsts.IS_OFFERWALL_AVAILABLE) {
      result.success(IronSource.isOfferwallAvailable())
    } else if (call.method == IronSourceConsts.SHOW_OFFERWALL) {
      IronSource.showOfferwall()
      result.success(null)
    } else if (call.method == IronSourceConsts.SHOW_REWARDED_VIDEO) {
      IronSource.showRewardedVideo()
      result.success(null)
    } else if (call.method == "validateIntegration") {
      IntegrationHelper.validateIntegration(mActivity)
      result.success(null)
    } else if (call.method == "setUserId") {
      IronSource.setUserId(call.argument<String>("userId"))
      result.success(null)
    } else if (call.method == "getAdvertiserId") {
      result.success(IronSource.getAdvertiserId(mActivity))
    } else if (call.method == "activityResumed") {
      IronSource.onResume(mActivity)
      result.success(null)
    } else if (call.method == "activityPaused") {
      IronSource.onPause(mActivity)
      result.success(null)
    } else if (call.method == "shouldTrackNetworkState" && call.hasArgument("state")) {
      call.argument<Boolean>("state")?.let { IronSource.shouldTrackNetworkState(mActivity, it) }
      result.success(null)

    } else {
      result.notImplemented()
    }
  }

  fun initialize(appKey: String, gdprConsent: Boolean, ccpaConsent: Boolean) {
    IronSource.setInterstitialListener(this)
    IronSource.setRewardedVideoListener(this)
    IronSource.setOfferwallListener(this)
    SupersonicConfig.getConfigObj().clientSideCallbacks = true
    IronSource.setConsent(gdprConsent)
    if (ccpaConsent)
      IronSource.setMetaData("do_not_sell", "false")
    else
      IronSource.setMetaData("do_not_sell", "true")

    IronSource.init(mActivity, appKey)
  }// Interstitial Listener

  override fun onInterstitialAdClicked() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_INTERSTITIAL_AD_CLICKED, null)
    }
  }

  override fun onInterstitialAdReady() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_INTERSTITIAL_AD_READY, null)
    }
  }

  override fun onInterstitialAdLoadFailed(ironSourceError: IronSourceError) {
    mActivity.runOnUiThread { //back on UI thread...
      val arguments = HashMap<String, Any>()
      arguments["errorCode"] = ironSourceError.errorCode
      arguments["errorMessage"] = ironSourceError.errorMessage
      mChannel.invokeMethod(IronSourceConsts.ON_INTERSTITIAL_AD_LOAD_FAILED, arguments)
    }
  }

  override fun onInterstitialAdOpened() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_INTERSTITIAL_AD_OPENED, null)
    }
  }

  override fun onInterstitialAdClosed() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_INTERSTITIAL_AD_CLOSED, null)
    }
  }

  override fun onInterstitialAdShowSucceeded() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_INTERSTITIAL_AD_SHOW_SUCCEEDED, null)
    }
  }

  override fun onInterstitialAdShowFailed(ironSourceError: IronSourceError) {
    mActivity.runOnUiThread { //back on UI thread...
      val arguments = HashMap<String, Any>()
      arguments["errorCode"] = ironSourceError.errorCode
      arguments["errorMessage"] = ironSourceError.errorMessage
      mChannel.invokeMethod(IronSourceConsts.ON_INTERSTITIAL_AD_SHOW_FAILED, arguments)
    }
  }

  // --------- IronSource Rewarded Video Listener ---------
  override fun onRewardedVideoAdOpened() {
    // called when the video is opened
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_REWARDED_VIDEO_AD_OPENED, null)
    }
  }

  override fun onRewardedVideoAdClosed() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_REWARDED_VIDEO_AD_CLOSED, null)
    }
  }

  override fun onRewardedVideoAvailabilityChanged(b: Boolean) {
    // called when the video availbility has changed
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_REWARDED_VIDEO_AVAILABILITY_CHANGED, b)
    }
  }

  override fun onRewardedVideoAdStarted() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_REWARDED_VIDEO_AD_STARTED, null)
    }
  }

  override fun onRewardedVideoAdEnded() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_REWARDED_VIDEO_AD_ENDED, null)
    }
  }

  override fun onRewardedVideoAdRewarded(placement: Placement) {
    mActivity.runOnUiThread {
      val arguments = HashMap<String, Any>()
      arguments["placementId"] = placement.placementId
      arguments["placementName"] = placement.placementName
      arguments["rewardAmount"] = placement.rewardAmount
      arguments["rewardName"] = placement.rewardName
      mChannel.invokeMethod(IronSourceConsts.ON_REWARDED_VIDEO_AD_REWARDED, arguments)
    }
  }

  override fun onRewardedVideoAdShowFailed(ironSourceError: IronSourceError) {
    mActivity.runOnUiThread {
      val arguments = HashMap<String, Any>()
      arguments["errorCode"] = ironSourceError.errorCode
      arguments["errorMessage"] = ironSourceError.errorMessage
      mChannel.invokeMethod(IronSourceConsts.ON_REWARDED_VIDEO_AD_SHOW_FAILED, arguments)
    }
  }

  override fun onRewardedVideoAdClicked(placement: Placement) {
    mActivity.runOnUiThread {
      val arguments = HashMap<String, Any>()
      arguments["placementId"] = placement.placementId
      arguments["placementName"] = placement.placementName
      arguments["rewardAmount"] = placement.rewardAmount
      arguments["rewardName"] = placement.rewardName
      mChannel.invokeMethod(IronSourceConsts.ON_REWARDED_VIDEO_AD_CLICKED, arguments)
    }
  }

  // --------- IronSource Offerwall Listener ---------
  override fun onOfferwallAvailable(available: Boolean) {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_OFFERWALL_AVAILABLE, available)
    }
  }

  override fun onOfferwallOpened() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_OFFERWALL_OPENED, null)
    }
  }

  override fun onOfferwallShowFailed(ironSourceError: IronSourceError) {
    mActivity.runOnUiThread {
      val arguments = HashMap<String, Any>()
      arguments["errorCode"] = ironSourceError.errorCode
      arguments["errorMessage"] = ironSourceError.errorMessage
      mChannel.invokeMethod(IronSourceConsts.ON_OFFERWALL_SHOW_FAILED, arguments)
    }
  }

  override fun onOfferwallAdCredited(credits: Int, totalCredits: Int, totalCreditsFlag: Boolean): Boolean {
    mActivity.runOnUiThread {
      val arguments = HashMap<String, Any>()
      arguments["credits"] = credits
      arguments["totalCredits"] = totalCredits
      arguments["totalCreditsFlag"] = totalCreditsFlag
      mChannel.invokeMethod(IronSourceConsts.ON_OFFERWALL_AD_CREDITED, arguments)
    }
    return false
  }

  override fun onGetOfferwallCreditsFailed(ironSourceError: IronSourceError) {
    mActivity.runOnUiThread {
      val arguments = HashMap<String, Any>()
      arguments["errorCode"] = ironSourceError.errorCode
      arguments["errorMessage"] = ironSourceError.errorMessage
      mChannel.invokeMethod(IronSourceConsts.ON_OFFERWALL_CREDITS_FAILED, arguments)
    }
  }

  override fun onOfferwallClosed() {
    mActivity.runOnUiThread { //back on UI thread...
      mChannel.invokeMethod(IronSourceConsts.ON_OFFERWALL_CLOSED, null)
    }
  }

  /*companion object {
    @JvmStatic
    fun registerWith(registrar: PluginRegistry.Registrar) {
      val channel = MethodChannel(registrar.messenger(), IronSourceConsts.MAIN_CHANNEL)
      channel.setMethodCallHandler(FlutterIronsource_xPlugin())
      val interstitialAdChannel = MethodChannel(registrar.messenger(), IronSourceConsts.INTERSTITIAL_CHANNEL)
      registrar.platformViewRegistry().registerViewFactory(IronSourceConsts.BANNER_AD_CHANNEL, IronSourceBanner(registrar.activity(), registrar.messenger()))
  }*/

  /*private companion object Factory {
    fun setup(plugin: FlutterIronsource_xPlugin, binaryMessenger: BinaryMessenger) {
    }
  }*/



  override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    this.flutterPluginBinding = binding
    this.mChannel = MethodChannel(binding.binaryMessenger, IronSourceConsts.MAIN_CHANNEL)
    this.mChannel.setMethodCallHandler(this)
    Log.i("DEBUG","Tesst On Attached")
    val interstitialAdChannel = MethodChannel(binding.binaryMessenger, IronSourceConsts.INTERSTITIAL_CHANNEL)
//    binding.platformViewRegistry.registerViewFactory(IronSourceConsts.BANNER_AD_CHANNEL, IronSourceBanner(this.mActivity, binding.binaryMessenger))
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    this.mChannel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    this.mActivity = binding.activity;
    Log.i("DEBUG", "Tesst On Activity")
    this.flutterPluginBinding.platformViewRegistry.registerViewFactory(IronSourceConsts.BANNER_AD_CHANNEL, IronSourceBanner(binding.activity, this.flutterPluginBinding.binaryMessenger))
//    registrar.platformViewRegistry().registerViewFactory(IronSourceConsts.BANNER_AD_CHANNEL, IronSourceBanner(this.mActivity, binding.binaryMessenger))
  }

  override fun onDetachedFromActivityForConfigChanges() {
    TODO("Not yet implemented")
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    TODO("Not yet implemented")
  }

  override fun onDetachedFromActivity() {
    TODO("Not yet implemented")
  }
}
