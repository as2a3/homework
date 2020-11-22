package com.said.homework.base.domain.entity

/**
 * Created by Ahmed Sa'eed on 21.11.2020.
 */
enum class Language(val lang: String) {
    ARABIC("ar"), ENGLISH("en"), TURKISH("tr");

    companion object {
        fun mapStringToLanguage(text: String?): Language? {
            for (language in Language.values()) {
                if (language.lang == text) {
                    return language
                }
            }
            return null
        }
    }
}