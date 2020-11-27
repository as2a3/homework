package com.said.homework.base.data.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import database.DaoMaster;

/**
 * Created by Ahmed Sa'eed on 27,November,2020
 */
public class AppDatabaseOpenHelper extends DaoMaster.OpenHelper {

    public AppDatabaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
