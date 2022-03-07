package com.metamorfosis_labs.flutter_ironsource_x

import android.app.Activity
import android.content.Context
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import java.util.*

class IronSourceBanner internal constructor(val mActivity: Activity, private val messenger: BinaryMessenger) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    override fun create(context: Context?, id: Int, args: Any?): PlatformView {
        return IronSourceBannerView(context, id, (args as HashMap<*, *>), messenger, mActivity)
    }
}