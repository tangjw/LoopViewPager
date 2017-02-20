package com.tjw.loopviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.tjw.loopviewpager.widget.banner.NewsHeaderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    private ListView mListView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mListView = (ListView) findViewById(R.id.lv_list);
    
    
        ArrayList<String> banners = new ArrayList<>();
    
        banners.add("http://i.imgur.com/FI49ftb.jpg");
        banners.add("http://i.imgur.com/55w5Km7.jpg");
        banners.add("http://i.imgur.com/F8n3Ic6.jpg");
        banners.add("http://i.imgur.com/Ig9oHCM.jpg");
        banners.add("http://i.imgur.com/aFhEEby.jpg");
        banners.add("http://i.imgur.com/P5ZRSvT.jpg");
        banners.add("http://i.imgur.com/jbemFzr.jpg");
        banners.add("http://i.imgur.com/OKvWoTh.jpg");
    
    
        NewsHeaderView headerView = new NewsHeaderView(this, Glide.with(this), banners);
    
        setContentView(headerView);
//        mListView.addHeaderView(headerView);
//        
//        mListView.setAdapter(new MainListAdapter(banners));
        
        
    }
}
