package com.tjw.loopviewpager.widget.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.RequestManager;
import com.tjw.loopviewpager.R;


/**
 * Created by huanghaibin
 * on 16-5-23.
 */
public class ViewNewsBanner extends RelativeLayout implements View.OnClickListener {
    private String bannerUrl;
    private ImageView iv_banner;

    public ViewNewsBanner(Context context) {
        super(context, null);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_news_banner, this, true);
        iv_banner = (ImageView) findViewById(R.id.iv_banner);
        setOnClickListener(this);
    }
    
    public void initData(RequestManager manager, String banner) {
        this.bannerUrl = banner;
        manager.load(banner).into(iv_banner);
        
    }

    @Override
    public void onClick(View v) {
        if (bannerUrl != null) {
//            int type = banner.getType();
//            long id = banner.getId();
//            UIHelper.showDetail(getContext(), type, id, banner.getHref());
        }
    }


}
