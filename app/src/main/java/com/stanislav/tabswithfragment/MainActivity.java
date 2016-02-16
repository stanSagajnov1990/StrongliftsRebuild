package com.stanislav.tabswithfragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

import com.stanislav.adapters.MyFragmentPagerAdapter;
import com.stanislav.fragments.Fragment1;
import com.stanislav.fragments.Fragment2;
import com.stanislav.fragments.Fragment3;
import com.stanislav.fragments.Fragment4;
import com.stanislav.fragments.Fragment5;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        OnTabChangeListener, OnPageChangeListener {

    private ViewPager viewPager;
    private TabHost tabHost;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i++;

        initViewPager();
        initTabHost();
    }

    private void initTabHost() {
//        tabHost = new TabHost(getApplicationContext());
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        Resources res = getResources();
        String[] tabNames = {"1", "2", "3", "4", "5"};
        for (int i = 0; i < tabNames.length; i++) {
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setIndicator("", res.getDrawable(R.drawable.ic_guidebook));
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    public class FakeContent implements TabContentFactory {

        Context mContext;

        public FakeContent(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(mContext);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    private void initViewPager() {

        List<Fragment> listFragments = new ArrayList<Fragment>();
        listFragments.add(new Fragment1());
        listFragments.add(new Fragment2());
        listFragments.add(new Fragment3());
        listFragments.add(new Fragment4());
        listFragments.add(new Fragment5());

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), listFragments);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        } else if(id == R.id.action_help){
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
        int pos = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(pos);

        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.h_scroll_view);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft()
                - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);
    }

}
