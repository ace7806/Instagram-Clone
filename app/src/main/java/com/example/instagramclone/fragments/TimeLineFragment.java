package com.example.instagramclone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagramclone.models.Post;
import com.example.instagramclone.adapters.PostsAdapter;
import com.example.instagramclone.R;
import com.example.instagramclone.utility.EndlessRecyclerViewScrollListener;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class TimeLineFragment extends Fragment {

    private RecyclerView rvPosts;
    public static final String TAG = "TimeLineFragment";
    protected PostsAdapter postsAdapter;
    protected List<Post> allPosts;
    private EndlessRecyclerViewScrollListener scrollListener;
    public TimeLineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_line, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts= view.findViewById(R.id.rvPosts);
        allPosts = new ArrayList<>();
        postsAdapter = new PostsAdapter(getContext(),allPosts);
        rvPosts.setAdapter(postsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                queryPosts(page);
                Log.i(TAG, "onLoadMore: success");
            }
        };
        queryPosts(0);
        // Adds the scroll listener to RecyclerView
        rvPosts.addOnScrollListener(scrollListener);



        // Steps to use the recycler view:
        // 0. create layout for one row in the list
        // 1. create the adapter,
        // 2. create the data source
        // 3. set the adapter on the recycler view
        // 4. set the layout manager on the recycler view
    }

    protected void queryPosts(int offset) {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.setSkip(offset*20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + "userName" + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                postsAdapter.notifyDataSetChanged();
            }
        });
    }
}