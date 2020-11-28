package com.said.homework.base.data.database

import android.content.Context
import database.DaoMaster.OpenHelper
import org.greenrobot.greendao.database.Database

/**
 * Created by Ahmed Sa'eed on 27,November,2020
 */
class AppDatabaseOpenHelper(context: Context?, name: String?) : OpenHelper(context, name) {
    override fun onCreate(db: Database) {
        super.onCreate(db)
    }
}