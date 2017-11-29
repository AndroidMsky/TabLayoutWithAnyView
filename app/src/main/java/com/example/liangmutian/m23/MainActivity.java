package com.example.liangmutian.m23;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

public class MainActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewpager;
    PagerAdapter2 mPagerAdapter;
    Context context;
    SparseArray<ImageView> imageViewSparseArray = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewpager = (ViewPager) findViewById(R.id.p1);
        mTabLayout.setTabMode(MODE_SCROLLABLE);
        context = this;

        mPagerAdapter = new PagerAdapter2();
        mViewpager.setAdapter(mPagerAdapter);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.d("lmt", "tabtabtab" + tab.getPosition());
                if (imageViewSparseArray.get(tab.getPosition()) != null)
                    imageViewSparseArray.get(tab.getPosition()).setImageResource(R.mipmap.timg);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (imageViewSparseArray.get(tab.getPosition()) != null)
                    imageViewSparseArray.get(tab.getPosition()).setImageResource(R.mipmap.ic_launcher);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(mViewpager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mPagerAdapter.getTabView(i));
            }
        }


    }

    private View initData() {
        View viewpagerA = getLayoutInflater().inflate(R.layout.viewpager_item, null);
        return viewpagerA;
    }


    class PagerAdapter2 extends PagerAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = initData();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position + "";
        }

        public View getTabView(int position) {
            if (position == 4 || position == 6) {
                View v = LayoutInflater.from(context).inflate(R.layout.textview_item, null);
                return v;
            }

            View v = LayoutInflater.from(context).inflate(R.layout.imageview_item, null);
            ImageView im = (ImageView) v.findViewById(R.id.m1);
            imageViewSparseArray.put(position, im);
            if (position == 0)
                im.setImageResource(R.mipmap.timg);
            return v;
        }

    }
}
