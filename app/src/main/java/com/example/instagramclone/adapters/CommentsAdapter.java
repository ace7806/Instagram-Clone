package com.example.instagramclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.instagramclone.R;
import com.example.instagramclone.models.Comment;

import java.text.SimpleDateFormat;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    Context context;
    List<Comment> comments;

    public CommentsAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCommentUsername;
        private TextView tvCommentDescription;
        private TextView tvCommentDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCommentUsername = itemView.findViewById(R.id.tvCommentUserName);
            tvCommentDescription = itemView.findViewById(R.id.tvCommentDescription);
            tvCommentDate = itemView.findViewById(R.id.tvCommentDate);

        }

        public void bind(Comment comment) {
            SimpleDateFormat formatter = new SimpleDateFormat();
            String strDate= formatter.format(comment.getCreatedAt());
            tvCommentUsername.setText(comment.getUser().getUsername());
            tvCommentDate.setText(strDate.toString());
            tvCommentDescription.setText(comment.getComment());
        }
    }
}
