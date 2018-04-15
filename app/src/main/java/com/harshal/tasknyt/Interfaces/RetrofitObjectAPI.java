package com.harshal.tasknyt.Interfaces;

import com.harshal.tasknyt.Model.MainBookModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Harshal on 06-Apr-18.
 */

public interface RetrofitObjectAPI {
    @GET("lists/overview.json")
    Observable<MainBookModel> getBooksDetailsObservable(@Query("api-key") String api_key);

    @GET("lists/overview.json")
    Call<MainBookModel> getBooksDetailsRetrofit(@Query("api-key") String api_key);
}
