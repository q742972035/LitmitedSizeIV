package com.test.litmitedsizeiv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.test.litmitedsizeiv.utils.DrawableUtils;


/**
 * 限制大小的ImageView，用于类朋友圈图片大小控制
 * Created by 13798 on 2016/6/15.
 */
public class LitmitedSizeImageView extends ImageView {

    private Context mContext;
    /**
     * 获取屏幕宽度
     */
    private int mWidth;
    /**
     * 获取屏幕高度
     */
    private int mHeight;
    /**
     * 图片宽度
     */
    private int dWidth;

    /**
     * 图片高度
     */
    private int dHeight;

    private Drawable mDrawable;

    public LitmitedSizeImageView(Context context) {
        this(context, null);
    }

    public LitmitedSizeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LitmitedSizeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mWidth = context.getResources().getDisplayMetrics().widthPixels;
        mHeight = context.getResources().getDisplayMetrics().heightPixels;
        mContext = context;
        initSize();

    }

    /**
     * 测量控件大小并作初始化操作
     */
    private void initSize() {
        //通过src获取资源
        if (getDrawable() != null) {
            mDrawable = getDrawable();
            // 通过background获取资源
        } else if (getBackground() != null) {
            mDrawable = getBackground();
        }

        if (mDrawable!=null)
            initDrawable();
    }

    /**
     * 针对drawable初始化大小
     */
    private void initDrawable() {
        dWidth = mDrawable.getIntrinsicWidth();
        dHeight = mDrawable.getIntrinsicHeight();

        float scale = 1.0f;
        float nowWidth;
        float nowHeight;

        if (dWidth > mWidth && dHeight > mHeight) {
            scale = Math.min(mWidth * 1.0f / dWidth, mHeight * 1.0f / dHeight);
        } else if (dWidth > mWidth) {
            scale = mWidth * 1.0f / dWidth;
        } else if (dHeight > mHeight) {
            scale = mHeight * 1.0f / dHeight;
        }
        // 当宽度或者高度有一方超屏，先进行缩放到屏幕大小
        nowWidth = dWidth * scale;
        nowHeight = dHeight * scale;

        //宽度大于屏幕0.8，高度大于屏幕0.8，就要先放大成屏幕大小
        if (nowWidth > mWidth * 0.8 && nowHeight > mHeight * 0.8) {
            nowWidth = mWidth ;
            nowHeight = mHeight ;
        }

        //宽度或者高度大于屏幕宽高1/3 ，就要进行缩放
        if (nowWidth > mWidth / 3 || nowHeight > mHeight / 3) {
            nowWidth = nowWidth * 1.0f/3;
            nowHeight = nowHeight * 1.0f/3;
        }


        // 获得压缩后（大小和视图）的Bitmap
        Bitmap newBitmap = DrawableUtils.zoomBitmap(mDrawable, nowWidth, nowHeight);
        // 强行至空，防止和下面方法冲突
        setBackgroundDrawable(null);
        setImageBitmap(newBitmap);
    }
}
