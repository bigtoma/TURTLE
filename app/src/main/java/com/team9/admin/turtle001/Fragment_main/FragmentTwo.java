package com.team9.admin.turtle001.Fragment_main;

import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.team9.admin.turtle001.Fragment_2_content.SongAdapter;
import com.team9.admin.turtle001.Fragment_2_content.TabListFragment1;
import com.team9.admin.turtle001.Fragment_2_content.TabListFragment2;
import com.team9.admin.turtle001.Fragment_2_content.TabListFragment3;
import com.team9.admin.turtle001.R;
import java.util.ArrayList;
import java.util.List;

// 频道


public class FragmentTwo extends Fragment   {

    private TabLayout mTabTl;
    private ViewPager mContentVp;
    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;
    private SongAdapter songAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.activity_tab_layout_top, container, false);
        mTabTl =view2.findViewById(R.id.tl_tab);
        mContentVp =view2.findViewById(R.id.vp_content);
        initTab();
        initContent();
        return view2;
    }
    private void initTab(){
        mTabTl.setTabMode(TabLayout.MODE_FIXED);
        mTabTl.setTabTextColors(ContextCompat.getColor(mTabTl.getContext(), R.color.colorWhite), ContextCompat.getColor(mTabTl.getContext(), R.color.white));
        mTabTl.setSelectedTabIndicatorColor(ContextCompat.getColor(mTabTl.getContext(), R.color.colorPrimaryDark));
        ViewCompat.setElevation(mTabTl, 3);
        mTabTl.setupWithViewPager(mContentVp);

    }
    private void initContent(){
        tabIndicators = new ArrayList<>();


        tabIndicators.add("冥想");
        tabIndicators.add("音乐");
        tabIndicators.add("游戏");

        tabFragments = new ArrayList<>();

            String s1= tabIndicators.get(0);
            tabFragments.add(TabListFragment1.newInstance(s1));
            String s2= tabIndicators.get(1);
            tabFragments.add(TabListFragment2.newInstance(s2));
            String s3= tabIndicators.get(2);
            tabFragments.add(TabListFragment3.newInstance(s3));

        contentAdapter = new ContentPagerAdapter(getChildFragmentManager());
        mContentVp.setAdapter(contentAdapter);
    }


    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override

        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }
        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }

    }

}

