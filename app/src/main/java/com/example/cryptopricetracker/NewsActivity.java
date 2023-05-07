package com.example.cryptopricetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;


public class NewsActivity extends AppCompatActivity {
    TabLayout tablayout;
    TabItem mhome,mbusiness;
    PagerAdapter pagerAdapter;
    Toolbar mtoolbar;
    String api = "09f50b4e950d46bc91033099913fb0a1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news2);
//        mtoolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(mtoolbar);
        mhome=findViewById(R.id.home);
        mbusiness=findViewById(R.id.business);
        ViewPager viewPager=findViewById(R.id.fragmentcontainer);
        tablayout =findViewById(R.id.toolbar);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),2);
        viewPager.setAdapter(pagerAdapter);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0||tab.getPosition()==1){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }
    public void backbutton(View view) {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}