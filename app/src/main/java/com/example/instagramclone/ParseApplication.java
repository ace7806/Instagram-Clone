package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("tcT7ZlioLdbgeFMOmW7beeDiu5J2YAfrCc4elSiA")
                .clientKey("qhinEqnUr5np1KVqXcne6YIDwtvcFkIx0h1ECqXM")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}