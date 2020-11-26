package com.said.homework

import androidx.multidex.MultiDexApplication
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.said.homework.AppConstants.API_KEY_KEY
import com.said.homework.base.presentation.di.component.ApplicationComponent
import com.said.homework.base.presentation.di.component.DaggerApplicationComponent
import com.said.homework.base.presentation.di.module.ApplicationModule

/**
 * Created by Ahmed Sa'eed on 15.01.2020.
 */
class MyApp : MultiDexApplication() {
    private var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        initializeInjector()

        initRemoteConfig()
        AppConstants.API_KEY = remoteConfig!![API_KEY_KEY].asString()

        // Obtain the FirebaseAnalytics instance.
        fireBaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    private fun initRemoteConfig() {
        // Get Remote Config instance.
        // [START get_remote_config_instance]
        remoteConfig = Firebase.remoteConfig
        // [END get_remote_config_instance]

        // Create a Remote Config Setting to enable developer mode, which you can use to increase
        // the number of fetches available per hour during development. Also use Remote Config
        // Setting to set the minimum fetch interval.
        // [START enable_dev_mode]
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig!!.setConfigSettingsAsync(configSettings)
        // [END enable_dev_mode]

        // Set default Remote Config parameter values. An app uses the in-app default values, and
        // when you need to adjust those defaults, you set an updated value for only the values you
        // want to change in the Firebase console. See Best Practices in the README for more
        // information.
        // [START set_default_values]
        remoteConfig!!.setDefaultsAsync(R.xml.remote_config_defaults)
        // [END set_default_values]
    }

    companion object {
        private var sInstance: MyApp? = null
        var fireBaseAnalytics: FirebaseAnalytics? = null
            private set
        var remoteConfig: FirebaseRemoteConfig? = null
            private set

        fun get(): MyApp? {
            checkNotNull(sInstance) { "Something went horribly wrong, no application attached!" }
            return sInstance
        }

    }


    private fun initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return mApplicationComponent
    }

}