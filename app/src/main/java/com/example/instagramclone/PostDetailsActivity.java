package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.instagramclone.adapters.CommentsAdapter;
import com.example.instagramclone.models.Comment;
import com.example.instagramclone.models.Post;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class PostDetailsActivity extends AppCompatActivity {
    public static final String TAG="postDetailsActivity";
    private Post post;
    private TextView tvUsername;
    private ImageView ivImage;
    private TextView tvDescription;
    private EditText etComment;
    private Button btnSubmitComment;
    private RecyclerView rvComments;
    private CommentsAdapter commentsAdapter;
    private List<Comment> comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        tvUsername = findViewById(R.id.tvUserName);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        rvComments = findViewById(R.id.rvComments);
        etComment = findViewById(R.id.etComment);
        btnSubmitComment = findViewById(R.id.btnSubmitComment);
        post = Parcels.unwrap(getIntent().getParcelableExtra("post"));

        comments = new ArrayList<>();
        commentsAdapter = new CommentsAdapter(PostDetailsActivity.this,comments);
        rvComments.setAdapter(commentsAdapter);
        rvComments.setLayoutManager(new LinearLayoutManager(PostDetailsActivity.this));

        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        if (post.getImage() != null)
            Glide.with(PostDetailsActivity.this).load(post.getImage().getUrl()).into(ivImage);
        queryComments();

        btnSubmitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String commentText = etComment.getText().toString();
               SubmitComment(commentText);
            }
        });
    }

    private void SubmitComment(String commentText) {
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setPost(post);
        comment.setUser(ParseUser.getCurrentUser());
        comment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Log.e(TAG, "error: couldn't submit comment",e );
                    Toast.makeText(PostDetailsActivity.this,"couldn't submit comment",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "done: successfully submitted comment");
                Toast.makeText(PostDetailsActivity.this,"successfully submitted comment",Toast.LENGTH_SHORT).show();
                comments.add(comment);
                commentsAdapter.notifyDataSetChanged();
                etComment.setText("");

            }
        });
    }


    private void queryComments(){
        ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);
        query.addAscendingOrder(Comment.KEY_CREATED_AT);
        query.include(Comment.KEY_USER);

        query.whereEqualTo(Comment.KEY_POST,post);
        query.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> commentList, ParseException e) {
                if(e!=null){
                    Log.e(TAG, "done: couldn't retrieve comments",e );
                    Toast.makeText(PostDetailsActivity.this,"couldn't retrieve comments",Toast.LENGTH_SHORT).show();
                    return;
                }
                for(Comment c :commentList) {
                    Log.i(TAG, "username: " + c.getUser().getUsername() + " comment: " + c.getComment());
                }
                comments.addAll(commentList);
                commentsAdapter.notifyDataSetChanged();
            }
        });

    }
}