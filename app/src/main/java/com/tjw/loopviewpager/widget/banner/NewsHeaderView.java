package com.tjw.loopviewpager.widget.banner;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.tjw.loopviewpager.R;

import java.util.List;

public class NewsHeaderView extends HeaderView {
    private TextView mTitleTextView;
    
    private List<String> mStringList;
    
    
    public NewsHeaderView(Context context, RequestManager loader, List<String> banners) {
        super(context, loader, banners);
        mStringList = banners;
        System.out.println(mStringList.size() + "----");
    }
    
    @Override
    protected void init(Context context) {
        super.init(context);
        mTitleTextView = (TextView) findViewById(R.id.tv_title);
    }
    
    @Override
    protected int getLayoutId() {
        return R.layout.layout_news_header_view;
    }
    
    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
    
        System.out.println(mStringList.size());
        if (mStringList.size() != 0) {
//            mTitleTextView.setText(mStringList.get(position % mStringList.size()).getName());
            mTitleTextView.setText("test");
        }
    }
    
    void setStringList(List<String> stringList) {
        super.setStringList(stringList);
        if (stringList.size() > 0 && mCurrentItem == 0) {
//            mTitleTextView.setText(mStringList.get(0).getName());
            mTitleTextView.setText("test");
        }
    }
    
    @Override
    protected Object instantiateItem(ViewGroup container, int position) {
        ViewNewsBanner view = new ViewNewsBanner(getContext());
        if (mStringList.size() != 0) {
            int p = position % mStringList.size();
            if (p >= 0 && p < mStringList.size()) {
                view.initData(mImageLoader, mStringList.get(p));
                
            }
        }
        container.addView(view);
        return view;
    }
    
    @Override
    protected void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof ViewNewsBanner) {
            container.removeView((ViewNewsBanner) object);
        }
    }
}
