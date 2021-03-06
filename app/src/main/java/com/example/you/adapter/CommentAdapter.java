package com.example.you.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.models.Comment;
import com.example.you.models.CommentClass;
import com.example.you.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter <CommentAdapter.ViewHolder> {

    List<Comment> comments;
    Context context;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.comments =comments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        holder.username.setText(comments.get(position).getName());
        holder.comment.setText(comments.get(position).getComment());
        Picasso.get().load(comments.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username, comment;
        CircleImageView imageView;

        public ViewHolder (View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            comment = itemView.findViewById(R.id.comment);
            imageView = itemView.findViewById(R.id.profile);
        }
    }
}
