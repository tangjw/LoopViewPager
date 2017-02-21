package com.tjw.loopviewpager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * ^-^
 * Created by tang-jw on 2/20.
 */
public class MainListAdapter extends BaseAdapter {
    
    private List<String> mBannerList;
    
    public MainListAdapter(List<String> bannerList) {
        mBannerList = bannerList;
    }
    
    public void setBannerList(List<String> bannerList) {
        mBannerList = bannerList;
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
//        return mBannerList.size();
        return 30;
    }
    
    @Override
    public String getItem(int position) {
        return mBannerList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_main, null);
        }
        
        return convertView;
    }
}
