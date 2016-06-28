package com.tnt.android.hw5_android_database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {

    TextView txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        txtUsername  = (TextView) findViewById(R.id.txt_username);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        txtUsername.setText(username);
    }
}
