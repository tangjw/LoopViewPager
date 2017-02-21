package com.tjw.loopviewpager.net;

import android.content.Context;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


/**
 * Created by Android on 2016/11/11.
 */

public class BaseSubscriber<T> extends Subscriber<T> {
	private Context mContext;
	
	public BaseSubscriber(Context context) {
		mContext = context;
	}
	
	@Override
	public void onStart() {
		
		
	}
	
	
	@Override
	public void onCompleted() {
		
	}
	
	@Override
	public void onError(Throwable e) {
		
		if (e instanceof HttpException) {
		} else if (e instanceof IOException) {
		} else if (e instanceof ApiException) {
		} else {
//			MyToast.show(mContext, "未知错误" + e.getMessage());
		}
	}
	
	@Override
	public void onNext(T t) {
		
	}
}
