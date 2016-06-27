package com.tnt.android.hw5_android_database.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tnt.android.hw5_android_database.common.User;

/**
 * Created by USER on 27.6.2016 г..
 */
public class DBHelper extends SQLiteOpenHelper{
    protected SQLiteDatabase database;

    public static final String DB_NAME = "db_users";
    public static final int DB_VERSION = 1;
    public static final String DB_TABLE_USERS = "DB_TABLE_USERS";
    public static final String KEY_USERNAME = "USERNAME";
    public static final String KEY_PASSWORD = "PASSWORD";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        open();
    }

    public void insertUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_USERNAME, user.getUsername());
        cv.put(KEY_PASSWORD, user.getPassword());
        database.insert(DB_TABLE_USERS, null, cv);
    }

    private void open() {
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + DB_TABLE_USERS + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_USERNAME + " TEXT, "
                + KEY_PASSWORD + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_USERS);
        onCreate(db);
    }

    public void close() {
        database.close();
    }

    public Cursor getUserValues(){
        return this.database.query(DB_TABLE_USERS, new String[]{KEY_USERNAME, KEY_PASSWORD}, null, null, null, null, null);
    }
}
