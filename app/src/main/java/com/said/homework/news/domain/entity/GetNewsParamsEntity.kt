package com.said.homework.news.domain.entity

import java.util.*

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class GetNewsParamsEntity {
    var page = 1
    var keyword: String? = null
    var sortBy: String? = null
    var from: Date? = null

    constructor(page: Int, keyword: String?) {
        this.page = page
        this.keyword = keyword
    }
}