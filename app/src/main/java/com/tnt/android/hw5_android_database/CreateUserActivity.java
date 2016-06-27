package com.tnt.android.hw5_android_database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tnt.android.hw5_android_database.common.User;
import com.tnt.android.hw5_android_database.database.DBHelper;
import com.tnt.android.hw5_android_database.database.DBUtils;

public class CreateUserActivity extends AppCompatActivity {

    EditText editUsername;
    EditText editPass;
    EditText editRePass;
    Button btnOk;
    Button btnCancel;

    private DBUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        dbUtils = DBUtils.getInstance(this);
        //User newUser = new User("admin", "1234");
        //writeRecord(newUser);


        editUsername = (EditText) findViewById(R.id.edit_username);
        editPass = (EditText) findViewById(R.id.edit_pass);
        editRePass = (EditText) findViewById(R.id.edit_re_pass);

        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check for username exists in db
                readDB();

                //check for equals values in pass and re-pass fields
                String username = editUsername.getText().toString();
                String password = editPass.getText().toString();
                String rePassword = editRePass.getText().toString();

                // /save in db
                if(password.length() > 2 && password.equals(rePassword)){
                    User user = new User(username, password);
                    writeRecord(user);

                    Toast toast = Toast.makeText(getApplicationContext(), "This user save in db!", Toast.LENGTH_LONG);
                    toast.show();
                }

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void readDB() {
        Cursor cursor = dbUtils.readUserRecord();
        if (cursor.moveToFirst()) {
            do {
                String nodeUsername = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_USERNAME));

//                if (nodeUsername.equals(editUsername.getText().toString())) {
//                    Toast toast = Toast.makeText(getApplicationContext(), "This user already exists!", Toast.LENGTH_LONG);
//                    toast.show();
//                    return;
//                }

                //String nodePassword = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PASSWORD));

                //Node node = new Node(nodeName, nodeNum);
                //nodes.add(node);
                Log.d("Username", nodeUsername);
                //Log.d("Password", nodePassword);
            } while (cursor.moveToNext());
        }
    }

    private void writeRecord(User user) {
        dbUtils.writeUserRecord(user);
    }
}
