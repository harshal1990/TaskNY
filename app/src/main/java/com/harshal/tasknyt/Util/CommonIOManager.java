package com.harshal.tasknyt.Util;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Harshal on 07-Apr-18.
 */

public class CommonIOManager {
    private static String BASE_URL = "https://api.nytimes.com/svc/books/v3/";

    public static Retrofit getRetrofitManager()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
