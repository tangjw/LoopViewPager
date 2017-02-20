package com.tjw.loopviewpager.widget.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.RequestManager;
import com.tjw.loopviewpager.R;
import com.tjw.loopviewpager.widget.indicator.CirclePagerIndicator;

import java.util.List;


/**
 * Created by haibin
 * on 2016/10/26.
 */

public abstract class HeaderView extends RelativeLayout implements ViewPager.OnPageChangeListener, Runnable {
    protected ViewPager mViewPager;
    protected CirclePagerIndicator mIndicator;
    protected BannerAdapter mAdapter;
    protected Handler mHandler;
    protected int mCurrentItem;
    protected RequestManager mImageLoader;
    protected List<String> mBannerUrlList;
    private boolean isScrolling;
    
    public HeaderView(Context context, RequestManager loader, List<String> bannerUrlList) {
        super(context);
        mBannerUrlList = bannerUrlList;
        mImageLoader = loader;
        
        System.out.println(mBannerUrlList.size() + "HeaderView");
        init(context);
    }
    
    protected void init(Context context) {

        LayoutInflater.from(context).inflate(getLayoutId(), this, true);
        mViewPager = (ViewPager) findViewById(R.id.vp_banner);
        mIndicator = (CirclePagerIndicator) findViewById(R.id.indicator);
        mAdapter = new BannerAdapter();
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(mAdapter);
        mIndicator.bindViewPager(mViewPager);
        mIndicator.setCount(mBannerUrlList.size());
        
        new SmoothScroller(getContext()).bingViewPager(mViewPager);
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isScrolling = true;
                    case MotionEvent.ACTION_UP:
                        isScrolling = false;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        isScrolling = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isScrolling = true;
                        break;
                }
                return false;
            }
        });
        
        requestBanner();
    }
    
    @Override
    public void run() {
        mHandler.postDelayed(this, 5000);
        if (isScrolling)
            return;
        mCurrentItem = mCurrentItem + 1;
        mViewPager.setCurrentItem(mCurrentItem);
    }
    
    public void requestBanner() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.removeCallbacks(this);
        
        // TODO: 2017/2/20 联网获取Banner 
//        OSChinaApi.getBanner(mUrl, mCallBack);

//        mImageLoader.load(mBanners.get(0));
        
        setStringList(mBannerUrlList);
        
    }
    
    void setStringList(List<String> banner) {
        if (banner != null) {
            mHandler.removeCallbacks(this);
            mBannerUrlList.clear();
            mBannerUrlList.addAll(banner);
            mViewPager.getAdapter().notifyDataSetChanged();
            mIndicator.setCount(mBannerUrlList.size());
            mIndicator.notifyDataSetChanged();
            if (mCurrentItem == 0 && mBannerUrlList.size() != 1) {
                mCurrentItem = mBannerUrlList.size() * 1000;
                mViewPager.setCurrentItem(mCurrentItem);
            }
            if (mBannerUrlList.size() > 1) {
                mHandler.postDelayed(this, 5000);
            }
        }
    }
    
    protected abstract int getLayoutId();
    
    protected abstract Object instantiateItem(ViewGroup container, int position);
    
    protected abstract void destroyItem(ViewGroup container, int position, Object object);
    
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        isScrolling = mCurrentItem != position;
    }
    
    @Override
    public void onPageSelected(int position) {
        isScrolling = false;
        mCurrentItem = position;
    }
    
    @Override
    public void onPageScrollStateChanged(int state) {
        isScrolling = state != ViewPager.SCROLL_STATE_IDLE;
    }
    
    private class BannerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mBannerUrlList.size() == 1 ? 1 : Integer.MAX_VALUE;
        }
        
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return HeaderView.this.instantiateItem(container, position);
        }
        
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            HeaderView.this.destroyItem(container, position, object);
        }
    }
    
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mHandler == null)
            mHandler = new Handler();
        mHandler.postDelayed(this, 5000);
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mHandler == null)
            return;
        mHandler.removeCallbacks(this);
        mHandler = null;
    }
}
