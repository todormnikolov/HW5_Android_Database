package com.tnt.android.hw5_android_database;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.tnt.android.hw5_android_database.common.User;
import com.tnt.android.hw5_android_database.database.DBHelper;
import com.tnt.android.hw5_android_database.database.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnNew;
    Button btnOpen;
    Button btnExtract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        //deleteDatabase("db_users"); //delete all data from local storage

        btnNew = (Button) findViewById(R.id.btn_new);
        btnOpen = (Button) findViewById(R.id.btn_open);
        btnExtract = (Button) findViewById(R.id.btn_extract);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsersListActivity.class);
                startActivity(intent);
            }
        });

        btnExtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBUtils dbUtils = DBUtils.getInstance(getApplicationContext());
                Firebase ref = new Firebase("https://hw5users.firebaseio.com/");

                int counter = 0;

                Cursor cursor = dbUtils.readUserRecord();
                if (cursor.moveToFirst()) {

                    Toast.makeText(getApplicationContext(),"Please wait, app starts upload on Firebase!", Toast.LENGTH_SHORT).show();

                    do {
                        Firebase firebase = ref.child("users").child(String.valueOf(counter));
                        counter++;

                        //store fields in firebase
                        firebase.child("username").setValue(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_USERNAME)));
                        firebase.child("password").setValue(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PASSWORD)));

                        //firebase.setValue(new User( cursor.getString(cursor.getColumnIndex(DBHelper.KEY_USERNAME)), cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PASSWORD))));


                    } while (cursor.moveToNext());
                }

                Toast.makeText(getApplicationContext(),"Data is saved on Firebase!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
