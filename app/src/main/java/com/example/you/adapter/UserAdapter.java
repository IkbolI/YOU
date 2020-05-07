package com.example.you.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.R;
import com.example.you.models.Comment;
import com.example.you.models.UserModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<UserModel> users;
    Context context;

    public UserAdapter(Context context, List<UserModel> comments) {
        this.users =comments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.username.setText(users.get(position).getName());
        Picasso.get().load(users.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        CircleImageView imageView;

        public ViewHolder (View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            imageView = itemView.findViewById(R.id.profile);
        }
    }
}
