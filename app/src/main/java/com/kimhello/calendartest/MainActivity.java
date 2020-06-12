package com.kimhello.calendartest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kimhello.calendartest.UI.fragments.CalendarFragment;
import com.kimhello.calendartest.UI.fragments.CommunityFragment;
import com.kimhello.calendartest.UI.fragments.MyPageFragment;
import com.kimhello.calendartest.UI.fragments.NotificationsFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment calendar_fragment, community_fragment, notifications_fragment, mypage_fragment, test_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //generate fragment
        calendar_fragment = new CalendarFragment();
        community_fragment = new CommunityFragment();
        notifications_fragment = new NotificationsFragment();
        mypage_fragment = new MyPageFragment();

        //initial fragment setting
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, calendar_fragment).commitAllowingStateLoss();

        //bottom navigationbar select listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.calendar_tab: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, calendar_fragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.community_tab: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, community_fragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.notifications_tab: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, notifications_fragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.mypage_tab: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, mypage_fragment).commitAllowingStateLoss();
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });

    }
}
