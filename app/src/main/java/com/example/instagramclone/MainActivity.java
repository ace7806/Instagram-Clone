package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.instagramclone.fragments.ComposeFragment;
import com.example.instagramclone.fragments.TimeLineFragment;
import com.example.instagramclone.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_insta_logo);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        //define fragments here
        final Fragment timeLineFragment = new TimeLineFragment();
        final Fragment composeFragment = new ComposeFragment();
        final Fragment userFragment = new UserFragment();

        bottomNavigationView = findViewById(R.id.bnAppBar);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragment = timeLineFragment;
                        break;
                    case R.id.action_addBox:
                        fragment = composeFragment;
                        break;
                    case R.id.action_User:
                        fragment = userFragment;
                        break;
                    default:
                        fragment = userFragment;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);

    }

}