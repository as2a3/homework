package com.said.homework.base.data.util

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.said.homework.base.presentation.util.LanguageUtilUI
import java.util.*

class LanguageUtil(private val applicationContext: Context) {
    fun setLocale(language: String) {
        setLocale(applicationContext, language)
    }

    private fun setLocale(context: Context, language: String) {
        LanguageUtilUI.setLocale(context, language)
    }

    @get:TargetApi(Build.VERSION_CODES.N)
    private val deviceLanguageN: String
        get() = Resources.getSystem().configuration
            .locales[0].language
    private val deviceLanguageLegacy: String
        get() = Resources.getSystem().configuration.locale.language
    val deviceLanguage: String
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            deviceLanguageN
        } else {
            deviceLanguageLegacy
        }

    companion object {
        fun getLocale(language: String?): Locale {
            return Locale(language)
        }

        val locale: Locale
            get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Locale.getDefault(Locale.Category.FORMAT)
            } else {
                Locale.getDefault()
            }
    }
}