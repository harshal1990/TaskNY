package com.harshal.tasknyt.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.harshal.tasknyt.Interfaces.RetrofitObjectAPI;
import com.harshal.tasknyt.Model.MainBookModel;
import com.harshal.tasknyt.Util.CommonCode;
import com.harshal.tasknyt.Util.CommonIOManager;
import com.harshal.tasknyt.Util.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Harshal on 15-Apr-18.
 */

public class ApiCallService extends IntentService {
    private static String TAG = "ApiCallService";
    private ResultReceiver resultReceiver;

    public ApiCallService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        resultReceiver = intent.getParcelableExtra("receiver");
        sendShowProgres(true);
//        getData();
        getDataUsingObservable();
    }

    //not using RxJava
    void getData()
    {
        if(CommonCode.getInstance(this).checkInternet())
        {
            RetrofitObjectAPI service = CommonIOManager.getRetrofitManager().create(RetrofitObjectAPI.class);

            Call<MainBookModel> call = service.getBooksDetailsRetrofit("7745fc0778b44a61bc7287765ca7aed0");

            call.enqueue(new Callback<MainBookModel>() {

                @Override
                public void onResponse(Call<MainBookModel> call, Response<MainBookModel> response) {
                    Logger.getInstance().Log("responce received : "+response.body().results);
                    sendShowProgres(false);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("status",true);
                    bundle.putSerializable("results",response.body().results);
                    resultReceiver.send(1,bundle);
                }

                @Override
                public void onFailure(Call<MainBookModel> call, Throwable t) {
                    sendShowProgres(false);
                    Logger.getInstance().Log("Api Call Failure");
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("status",false);
                    resultReceiver.send(1,bundle);
                }
            });
        }
        else
        {
            sendShowProgres(false);
            Logger.getInstance().Log("No Internet Connection");
        }
    }

    //using RxJava
    void getDataUsingObservable()
    {
        if(CommonCode.getInstance(this).checkInternet())
        {
            RetrofitObjectAPI service = CommonIOManager.getRetrofitManager().create(RetrofitObjectAPI.class);

            Observable<MainBookModel> call = service.getBooksDetailsObservable("7745fc0778b44a61bc7287765ca7aed0");

            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MainBookModel>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(MainBookModel value) {
                            Logger.getInstance().Log("responce received : "+value.results);
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("status",true);
                            bundle.putSerializable("results",value.results);
                            resultReceiver.send(1,bundle);
                        }

                        @Override
                        public void onError(Throwable e) {
                            sendShowProgres(false);
                            Logger.getInstance().Log("Api Call Failure");
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("status",false);
                            resultReceiver.send(1,bundle);
                        }

                        @Override
                        public void onComplete() {
                            sendShowProgres(false);
                        }
                    });
        }
        else
        {
            sendShowProgres(false);
            Logger.getInstance().Log("No Internet Connection");
        }
    }

    private void sendShowProgres(final boolean status)
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("show_progress",status);
        resultReceiver.send(2,bundle);
    }
}
