package com.tnt.android.hw5_android_database;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 28.6.2016 г..
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<String> usernames;

    public UserAdapter(UsersListActivity usersListActivity, List<String> usernames) {
        this.usernames = usernames;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        UserHolder holder = new UserHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        String username = usernames.get(position);
        holder.txtUsername.setText(username);
    }

    @Override
    public int getItemCount() {
        return usernames.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        TextView txtUsername;

        public UserHolder(View itemView) {
            super(itemView);

            txtUsername = (TextView) itemView.findViewById(R.id.txt_username);
        }
    }
}
