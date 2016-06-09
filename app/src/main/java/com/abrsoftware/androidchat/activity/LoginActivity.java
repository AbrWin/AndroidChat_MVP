package com.abrsoftware.androidchat.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abrsoftware.androidchat.R;
import com.abrsoftware.androidchat.view.ViewLogin;
import com.abrsoftware.androidchat.view.ViewRegister;

public class LoginActivity extends AppCompatActivity {

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pager = (ViewPager)findViewById(R.id.pager_fragment);
        pager.setAdapter(new PagerAdapterIndicator(getSupportFragmentManager()));
        pager.setOffscreenPageLimit(2);
    }

    private class PagerAdapterIndicator extends FragmentPagerAdapter{

        public PagerAdapterIndicator(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ViewLogin();
                case 1:
                    return new ViewRegister();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}


