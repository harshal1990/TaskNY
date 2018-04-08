package com.harshal.tasknyt.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Harshal on 07-Apr-18.
 */
public class CommonCode {

	private static CommonCode mInstance;
	private Context _mContext;

	private CommonCode(Context context) {
		this._mContext = context;
	}

	public static synchronized CommonCode getInstance(Context context)
	{
		if(mInstance == null)
		{
			mInstance = new CommonCode(context);
		}
		return mInstance;
	}
	
	/**
	 * This is used to check Internet connection is available or not
	 * @author Harshal 11/5/2017
	 * @return boolean
	 */
	public boolean checkInternet() {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) _mContext
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (connectivity != null) {
				NetworkInfo[] info = connectivity.getAllNetworkInfo();
				if (info != null)
					for (int i = 0; i < info.length; i++)
						// check if network is connected or device is in range
						if (info[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This is used to show tost message
	 * @author Harshal 11/5/2017
	 * @param msg
	 */
	public void showToastMessage(String msg)
	{
		Toast.makeText(_mContext,msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * This is used to show tost message
	 * @author Harshal 11/5/2017
	 * @param msg
	 */
	public void showToastMessage(int msg)
	{
		Toast.makeText(_mContext,msg, Toast.LENGTH_SHORT).show();
	}
}
