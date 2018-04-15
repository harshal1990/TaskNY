package com.harshal.tasknyt.services;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.harshal.tasknyt.Model.Results;

/**
 * Created by Harshal on 15-Apr-18.
 */

public class ApiCallback extends ResultReceiver {
    private Listener listener;

    public ApiCallback(Handler handler) {
        super(handler);
    }

    public void setListener(Listener listener)
    {
        this.listener = listener;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if(listener != null)
        {
            switch (resultCode)
            {
                case 1:
                    boolean status = resultData.getBoolean("status");
                    if(status)
                    {
                        Results results = (Results) resultData.getSerializable("results");
                        listener.onSuccess(results);
                    }
                    else
                    {
                        listener.onFailure();
                    }
                    break;

                case 2:
                    boolean show_progress = resultData.getBoolean("show_progress");
                    if(show_progress)
                    {
                        listener.showProgressDialog();
                    }
                    else
                    {
                        listener.dismissProgressDialog();
                    }
                    break;
            }
        }
    }

    public interface Listener<T>
    {
        void showProgressDialog();
        void dismissProgressDialog();
        void onSuccess(T data);
        void onFailure();
    }
}
