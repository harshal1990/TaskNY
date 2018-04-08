package com.harshal.tasknyt.Util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Harshal on 08-Apr-18.
 */

public class ImageLoader {
    private static ImageLoader mInstance;
    private Context _mContext;
    private ImageLoader(Context context)
    {
        this._mContext = context;
    }

    public static synchronized ImageLoader getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new ImageLoader(context);
        }
        return mInstance;
    }

    public void displayImage(String path, ImageView imageView)
    {
        Glide.with(_mContext)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
