package com.said.homework.base.presentation.util

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.said.homework.MyApp.Companion.fireBaseAnalytics
import java.util.*

/**
 * Created by Ahmed Sa'eed on 21.11.2020.
 */
object CrashUtil {
    const val BASE_NETWORK_EXCEPTION = "base_network"
    const val RX_JAVA_EXCEPTION = "rx_java";
    fun logFireBaseCrash(param: String?, message: String?) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CHARACTER, message)
        fireBaseAnalytics?.logEvent(param!!, bundle)
    }
}