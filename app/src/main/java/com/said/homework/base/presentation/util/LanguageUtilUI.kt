package com.said.homework.base.presentation.util

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.said.homework.base.data.util.LanguageUtil.Companion.getLocale
import com.said.homework.base.domain.entity.LanguageEntity

object LanguageUtilUI {
    fun setLocale(context: Context, language: String?): Context {
        var language = language
        if (language == null || language.isEmpty()) language =
            deviceLanguage //KaptanConstants.DEFAULT_LANGUAGE;

//        if (MyApp.Companion.get() != null)
//            Lingver.getInstance().setLocale(MyApp.Companion.get(), language);
        return context
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

    fun getLanguageEntityByLang(lang: String?): LanguageEntity {
        val languageEntity = LanguageEntity()
        languageEntity.language = lang
        languageEntity.name = getLocale(lang).displayName
        return languageEntity
    }
}