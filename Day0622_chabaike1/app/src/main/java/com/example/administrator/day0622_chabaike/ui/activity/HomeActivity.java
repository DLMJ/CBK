package com.example.administrator.day0622_chabaike.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageButton;

import com.example.administrator.day0622_chabaike.R;
import com.example.administrator.day0622_chabaike.beans.TabInfo;
import com.example.administrator.day0622_chabaike.ui.fragment.ContentFragment;

/*
    主页
 */
public class HomeActivity extends FragmentActivity {
    private static final String TAG=HomeActivity.class.getSimpleName();
    private TabLayout mTabs;
    private ViewPager viewpager;
    private ImageButton iv_more;
    private Toolbar tb;
    private TabInfo[] tabs=new TabInfo[]{
            new TabInfo("社会热点",6),
            new TabInfo("企业要闻",1),
            new TabInfo("医疗新闻",2),
            new TabInfo("生活贴士",3),
            new TabInfo("药品新闻",4),
            new TabInfo("疾病快讯",7),
            new TabInfo("食品新闻",5)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        tb= (Toolbar) findViewById(R.id.home_tb);
        tb.setBackgroundColor(Color.GREEN);
        tb.setTitle("茶百科");
        tb.inflateMenu(R.menu.toolbar_menu_home);
        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_item1:
                        Intent intent=new Intent();
                        intent.setClass(HomeActivity.this,MoreActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        initView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void initView() {
        mTabs = (TabLayout) findViewById(R.id.home_class);
        viewpager = (ViewPager) findViewById(R.id.home_vp);

        viewpager.setAdapter(new ContentAdapter(getSupportFragmentManager()));
        mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabs.setupWithViewPager(viewpager);

    }



    public class ContentAdapter extends FragmentStatePagerAdapter {

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "getPageTitle() returned: position=" + position);
            ContentFragment cf = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",tabs[position].class_id);
            cf.setArguments(bundle);
            return cf;
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return tabs[position].name;
        }
    }

}
