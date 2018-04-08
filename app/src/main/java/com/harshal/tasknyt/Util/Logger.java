package com.harshal.tasknyt.Util;

import android.util.Log;

/**
 * Created by Harshal on 06-Apr-18.
 */

public class Logger {
    private String TAG = "TASKNY";
    private static Logger mInstance;
    private Logger(){

    }
    public static synchronized Logger getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new Logger();
        }
        return mInstance;
    }

    public void Log(String msg)
    {
        Log.e(TAG,msg);
    }
}
