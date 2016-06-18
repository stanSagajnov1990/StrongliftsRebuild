package com.stanislav.adapters;
 
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stanislav.fragments.Fragment1;
import com.stanislav.fragments.Fragment2;
import com.stanislav.fragments.Fragment3;
import com.stanislav.fragments.Fragment4;
import com.stanislav.fragments.Fragment5;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
 
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
 
    @Override
    public Fragment getItem(int position) {
 
        switch (position) {
            case 0:
                Fragment1 tab1 = new Fragment1();
                return tab1;
            case 1:
                Fragment2 tab2 = new Fragment2();
                return tab2;
            case 2:
                Fragment3 tab3 = new Fragment3();
                return tab3;
            case 3:
                Fragment4 tab4 = new Fragment4();
                return tab4;
            case 4:
                Fragment5 tab5 = new Fragment5();
                return tab5;
            default:
                return null;
        }
    }
 
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}