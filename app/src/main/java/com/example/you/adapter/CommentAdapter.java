package com.example.you.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.models.CommentClass;
import com.example.you.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter <CommentAdapter.ViewHolder> {

    List<CommentClass> comments;
    Context context;

    public CommentAdapter (Context context, List<CommentClass> comments) {
        this.comments =comments;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_game_page1, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView_username.setText(comments.get(position).getUsername());
        holder.textView_comment.setText(comments.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_username, textView_comment;

        public ViewHolder (View itemView) {

            super(itemView);

            textView_username = itemView.findViewById(R.id.textView_username);
            textView_comment = itemView.findViewById(R.id.textView_comment);
        }
    }
}
