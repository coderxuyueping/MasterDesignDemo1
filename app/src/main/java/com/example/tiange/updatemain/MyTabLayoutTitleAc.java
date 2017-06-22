package com.example.tiange.updatemain;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tiange.tablayoutandrecycler.Fragment1;
import com.example.tiange.tablayoutandrecycler.Fragment2;
import com.example.tiange.tablayoutandrecycler.Fragment3;
import com.example.tiange.tablayoutandrecycler.Main4Activity;

/**
 * User: xyp
 * Date: 2017/3/14
 * Time: 18:23
 */
//自定义TabLayout的title样式
public class MyTabLayoutTitleAc extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager vp;
    private String[] titles=new String[]{"关注1","关注2","关注3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tab_layout_title);
        tablayout= (TabLayout) findViewById(R.id.tablayout);
        vp= (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(2);//设置默认加载页数
        final MyAdapter adapter=new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        tablayout.setupWithViewPager(vp);//关联viewpager
        initTabTitle(adapter.getCount());
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetTitleBg(position,adapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new Fragment1();
            }else if(position==1){
                return new Fragment2();
            }else{
                return new Fragment3();
            }
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
    private void initTabTitle(int count){
        for(int i=0;i<count;i++){
            TabLayout.Tab tab=tablayout.getTabAt(i);
            tab.setCustomView(R.layout.tab_title_layout);
        }
    }
    private void resetTitleBg(int position,int count){
        for(int i=0;i<count;i++){
            TabLayout.Tab tab=tablayout.getTabAt(i);
            if(i==position){
                tab.getCustomView().findViewById(android.R.id.text1).setSelected(true);
            }else{
                tab.getCustomView().findViewById(android.R.id.text1).setSelected(false);
            }
        }
    }
}
