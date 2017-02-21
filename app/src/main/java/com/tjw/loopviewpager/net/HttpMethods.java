package com.tjw.loopviewpager.net;

import com.tjw.loopviewpager.BuildConfig;
import com.tjw.loopviewpager.bean.BannerListRes;
import com.tjw.loopviewpager.net.converter.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 所有的网络请求 Created by tang-jw on 2016/9/13.
 */
public class HttpMethods {
    
    //    private static final String BASE_URL = "http://192.168.1.235:8180/";
    private static final String BASE_URL = "http://meeting.zonsim.com:8080/simmeeting/";
    private Retrofit mRetrofit;
    private ApiService mApiService;
    
    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        
        OkHttpClient client = builder.connectTimeout(15000L, TimeUnit.MILLISECONDS)
                .readTimeout(15000L, TimeUnit.MILLISECONDS)
                .build();
        
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        
        mApiService = mRetrofit.create(ApiService.class);
        
    }
    
    
    
    public static HttpMethods getInstance() {
        return new HttpMethods();
    }
    
    
    private <T> void toSubscribe(Observable<T> observable, BaseSubscriber<T> subscriber) {
        
        observable
                //指定被观察者的线程
                .subscribeOn(Schedulers.io())
                //指定订阅者的线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        
        
    }
    
    
    public void getMeetingBanners(long meetingId, BaseSubscriber<BannerListRes> subscriber) {
        Observable<BannerListRes> observable = mApiService.getMeetingBanners(meetingId);
        toSubscribe(observable, subscriber);
    }
    
    
}
