package com.said.homework.news.domain.entity

import java.util.*

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class GetNewsParamsEntity {
    var page = 0
    var keyword: String? = null
    var sortBy: String? = null
    var apiKey: String? = null
    var from: Date? = null
}