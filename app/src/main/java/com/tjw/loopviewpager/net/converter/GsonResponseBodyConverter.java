package com.tjw.loopviewpager.net.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import com.tjw.loopviewpager.bean.BaseResponse;
import com.tjw.loopviewpager.net.ApiException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
	private final Gson gson;
	private final TypeAdapter<T> adapter;
	
	GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
		this.gson = gson;
		this.adapter = adapter;
	}
	
	@Override
	public T convert(ResponseBody value) throws IOException {
		JsonReader jsonReader = gson.newJsonReader(value.charStream());
		try {
			
			T t = adapter.read(jsonReader);
			BaseResponse t1 = (BaseResponse) t;
			
			if (t1.getCode() >= 10000) {
				value.close();
				throw new ApiException(t1.getCode(), t1.getMessage());
			}
			return t;
		} finally {
			value.close();
		}
	}
}
