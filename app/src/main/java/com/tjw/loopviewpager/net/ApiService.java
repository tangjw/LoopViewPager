package com.tjw.loopviewpager.net;

import com.tjw.loopviewpager.bean.BannerListRes;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


interface ApiService {
    
    @GET("meetings/{meetingId}/banners")
    Observable<BannerListRes> getMeetingBanners(@Path("meetingId") long meetingId);
    
    
}





