package com.stanislav.tabswithfragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.stanislav.adapters.PagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.stanislav.adapters.MyFragmentPagerAdapter;


public class MainActivity extends AppCompatActivity implements
        OnTabChangeListener, OnPageChangeListener {

    private static final String TAG = "MainActivity";
    private ViewPager viewPager;
    private TabHost tabHost;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(0x000000);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_clock));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_diagram));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_calender));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_playbut));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.ic_home_selected);
                        setTitle("StrongLifts");
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_clock_selected);
                        setTitle("History");
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_diagram_selected);
                        setTitle("StrongLifts");
                        break;
                    case 3:
                        tab.setIcon(R.drawable.ic_calender_selected);
                        setTitle("StrongLifts");
                        break;
                    case 4:
                        tab.setIcon(R.drawable.ic_playbut_selected);
                        setTitle("Videos");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.ic_home);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_clock);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_diagram);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.ic_calender);
                        break;
                    case 4:
                        tab.setIcon(R.drawable.ic_playbut);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //return true;
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (id == R.id.action_help) {
            Intent i = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        /*int pos = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(pos);

        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.h_scroll_view);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft()
                - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);*/
    }

    public void startYoutube(View v) {
        Log.i(TAG, "startYoutube clicked");

        ;

        switch (v.getId()) {
            case R.id.container_workout_a:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=EP2g3Sj3qSw")));
                break;
            case R.id.container_workout_b:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ro3Mh9o7JPU")));
                break;
        }
    }

}
