package com.example.instagramclone.utility;

import android.app.Application;

import com.example.instagramclone.models.Comment;
import com.example.instagramclone.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Comment.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("JrshYESW4s7GQCXTVkXtCVw1r68uAEAlydVWkwBu")
                .clientKey("FemQBTu7Sh94unE9zDnujJ9JywI5PaiIQLByIBxC")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}