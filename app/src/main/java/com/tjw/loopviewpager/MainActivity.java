package com.tjw.loopviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.tjw.loopviewpager.bean.BannerListRes;
import com.tjw.loopviewpager.net.BaseSubscriber;
import com.tjw.loopviewpager.net.HttpMethods;
import com.tjw.loopviewpager.widget.banner.SimpleHeaderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    private ListView mListView;
    private SimpleHeaderView mHeaderView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv_list);
        
        mHeaderView = new SimpleHeaderView(this);
        
        mListView.addHeaderView(mHeaderView);
        
        mListView.setAdapter(new MainListAdapter(new ArrayList<String>()));
        
        HttpMethods.getInstance()
                .getMeetingBanners(0, new BaseSubscriber<BannerListRes>(this) {
                    @Override
                    public void onNext(BannerListRes bannerListRes) {
                        mHeaderView.loadBanner(bannerListRes.getData());
                    }
                });
    }
}
