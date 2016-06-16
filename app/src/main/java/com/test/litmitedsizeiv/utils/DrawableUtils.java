package com.test.litmitedsizeiv.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by 13798 on 2016/6/15.
 */
public class DrawableUtils {
    public static Bitmap zoomBitmap(Drawable drawable, float w, float h){
        // 未知原因会少一倍，因此补偿差值
        int width = drawable.getIntrinsicWidth();
        int height= drawable.getIntrinsicHeight();
        Bitmap oldbmp = BitmapUtils.drawableToBitmap(drawable); // drawable 转换成 bitmap
        Matrix matrix = new Matrix();   // 创建操作图片用的 Matrix 对象
        float scaleWidth = ((float)w / width);   // 计算缩放比例
        float scaleHeight = ((float)h / height);

        matrix.postScale(scaleWidth, scaleHeight);         // 设置缩放比例
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);       // 建立新的 bitmap ，其内容是对原 bitmap 的缩放后的图
//        Drawable drawable1 = new BitmapDrawable(newbmp);
//        return new BitmapDrawable(newbmp);       // 把 bitmap 转换成 drawable 并返回
        return newbmp;
    }
}
