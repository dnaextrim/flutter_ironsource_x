package com.metamorfosis_labs.flutter_ironsource_x

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.ironsource.mediationsdk.ISBannerSize
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.IronSourceBannerLayout
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.sdk.BannerListener
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView
import java.util.*


class IronSourceBannerView internal constructor(context: Context, id: Int, args: HashMap<*, *>, messenger: BinaryMessenger?, activity: Activity) : PlatformView, BannerListener {
    private val adView: FrameLayout
    private val TAG = "IronSourceBannerView"
    private val channel: MethodChannel
    private val args: HashMap<*, *>
    private val context: Context
    private val activity: Activity
    private val mIronSourceBannerLayout: IronSourceBannerLayout
    private fun loadBanner() {
        if (adView.childCount > 0) adView.removeAllViews()
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT)
        adView.addView(
                mIronSourceBannerLayout, 0, layoutParams
        )
        adView.visibility = View.VISIBLE
        IronSource.loadBanner(mIronSourceBannerLayout)
    }

    override fun getView(): View {
        return adView
    }

    override fun dispose() {
        adView.visibility = View.INVISIBLE
        mIronSourceBannerLayout.removeBannerListener()
        IronSource.destroyBanner(mIronSourceBannerLayout)
        channel.setMethodCallHandler(null)
    }

    override fun onBannerAdLoaded() {
        activity.runOnUiThread { channel.invokeMethod(IronSourceConsts.ON_BANNER_AD_LOADED, null) }
    }

    override fun onBannerAdLoadFailed(ironSourceError: IronSourceError) {
        activity.runOnUiThread {
            val arguments: MutableMap<String, Any> = HashMap()
            arguments["errorCode"] = ironSourceError.errorCode
            arguments["errorMessage"] = ironSourceError.errorMessage
            channel.invokeMethod(IronSourceConsts.ON_BANNER_AD_LOAD_FAILED, arguments)
        }
    }

    override fun onBannerAdClicked() {
        activity.runOnUiThread { channel.invokeMethod(IronSourceConsts.ON_BANNER_AD_CLICKED, null) }
    }

    override fun onBannerAdScreenPresented() {
        activity.runOnUiThread { channel.invokeMethod(IronSourceConsts.ON_BANNER_AD_SCREEN_PRESENTED, null) }
    }

    override fun onBannerAdScreenDismissed() {
        activity.runOnUiThread { channel.invokeMethod(IronSourceConsts.ON_BANNER_AD_sCREEN_DISMISSED, null) }
    }

    override fun onBannerAdLeftApplication() {
        activity.runOnUiThread { channel.invokeMethod(IronSourceConsts.ON_BANNER_AD_LEFT_APPLICATION, null) }
    }

    init {
        channel = MethodChannel(messenger,
                IronSourceConsts.BANNER_AD_CHANNEL + id)
        this.activity = activity
        this.args = args
        this.context = context
        adView = FrameLayout(context)
        // choose banner size
        val size = ISBannerSize.SMART
        val height = args["height"] as Int
        val width = args["height"] as Int
        val lp = LinearLayout.LayoutParams(width, height)
        // instantiate IronSourceBanner object, using the IronSource.createBanner API
        mIronSourceBannerLayout = IronSource.createBanner(activity, size)
        mIronSourceBannerLayout.bannerListener = this
        loadBanner()
    }
}
