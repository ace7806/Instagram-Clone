package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.instagramclone.fragments.ComposeFragment;
import com.example.instagramclone.fragments.TimeLineFragment;
import com.example.instagramclone.fragments.UserFragment;
import com.example.instagramclone.fragments.UserTimeLineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;
    private Fragment timeLineFragment;
    private Fragment composeFragment;
    private Fragment userFragment;

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
        timeLineFragment = new TimeLineFragment();
        composeFragment = new ComposeFragment();
        userFragment = new UserTimeLineFragment();

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
                //displayFragment(fragment);
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);

    }

    // Replace the switch method
    protected void displayFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Hide fragment B
        if (timeLineFragment.isAdded()) { ft.hide(timeLineFragment); }
        // Hide fragment C
        if (composeFragment.isAdded()) { ft.hide(composeFragment); }
        if (userFragment.isAdded()) { ft.hide(userFragment); }

        if (fragment.isAdded()) { // if the fragment is already in container
            ft.show(fragment);
        } else { // fragment needs to be added to frame container
            ft.add(R.id.flContainer, fragment);
        }
        // Commit changes
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.LogOut){
            logOut();
        }
        return true;
    }

    private void logOut(){
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Log.e(TAG, "done: couldnt log out: ",e);
                    Toast.makeText(MainActivity.this,"Error: couldn't sign out",Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "done: logged out");
                Toast.makeText(MainActivity.this,"Logged out",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}