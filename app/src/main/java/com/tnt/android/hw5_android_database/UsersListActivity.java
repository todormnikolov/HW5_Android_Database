package com.tnt.android.hw5_android_database;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tnt.android.hw5_android_database.database.DBHelper;
import com.tnt.android.hw5_android_database.database.DBUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UsersListActivity extends AppCompatActivity {

    @Bind(R.id.rec_view)
    RecyclerView recyclerView;

    private DBUtils dbUtils;
    List<String> usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        ButterKnife.bind(this);

        dbUtils = DBUtils.getInstance(this);

        usernames = getUsernames(dbUtils);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserAdapter(this, usernames));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String username = usernames.get(position);
                        Intent intent = new Intent(UsersListActivity.this, UserInfoActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }
                })
        );

    }

    private List<String> getUsernames(DBUtils dbUtils) {

        List<String> usernames = new ArrayList<>();

        Cursor cursor = dbUtils.readUserRecord();
        if (cursor.moveToFirst()) {
            do {
                usernames.add(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_USERNAME)));
            } while (cursor.moveToNext());
        }

        return usernames;
    }

}
