package com.harshal.tasknyt.Interfaces;

import com.harshal.tasknyt.Model.MainBookModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Harshal on 06-Apr-18.
 */

public interface RetrofitObjectAPI {
    @GET("lists/overview.json")
    Call<MainBookModel> getBooksDetails(@Query("api-key") String api_key);
}
