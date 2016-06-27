package com.tnt.android.hw5_android_database.database;

import android.content.Context;
import android.database.Cursor;

import com.tnt.android.hw5_android_database.common.User;

/**
 * Created by USER on 27.6.2016 г..
 */
public class DBUtils {

    private static DBUtils instance;
    private DBHelper db;

    private DBUtils(Context context) {
        initDB(context);
    }

    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }

    public void writeUserRecord(User user) {
        db.insertUser(user);
    }

    public Cursor readUserRecord() {
        return db.getUserValues();
    }

    private DBHelper initDB(Context context) {
        if (db == null) {
            db = new DBHelper(context);
        }
        return db;
    }
}
