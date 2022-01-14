package com.example.instagramclone.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.instagramclone.LoginActivity;
import com.example.instagramclone.R;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class UserFragment extends Fragment {

    private static final String TAG = "UserFragment";
    private Button btnLogOut;


    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        btnLogOut = view.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    private void logOut(){
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Log.e(TAG, "done: couldnt log out: ",e);
                    Toast.makeText(getContext(),"Error: couldn't sign out",Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "done: logged out");
                Toast.makeText(getContext(),"Logged out",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(),LoginActivity.class);
                startActivity(i);
                getActivity().finish();

            }
        });
    }
}