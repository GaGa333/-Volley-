package com.gcc.taotaopiao.Manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.TypedValue;

/**
 * Created by pjy on 2017/4/18.
 */

public class ImageManager {
    /**
     * 格式化头像进行圆形处理
     * @param context
     * @param bitmap 被处理的原始图片
     * @return
     */
    public  static Bitmap formatBitmap(
        Context context,
        Bitmap bitmap,int mColor){
            //获得头像的宽
        int width=bitmap.getWidth();
        //获得头像的高
        int height=bitmap.getHeight();
        Bitmap backBitmap=Bitmap.createBitmap(
                width,height,
                Bitmap.Config.ARGB_8888);
        //创建一个画布
        Canvas canvas=new Canvas(backBitmap);

        //创建一个画笔
        Paint paint=new Paint();
        //设置画笔的颜色
        paint.setColor(mColor);
        //设置画笔的抗锯齿
        paint.setAntiAlias(true);

        //圆的半径
        int radius=Math.min(width,height)/2;
        //绘制圆
        canvas.drawCircle(width/2,height/2,radius,paint);

        //设置图像相交模式
        paint.setXfermode(
                new PorterDuffXfermode(
                PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,0,0,paint);
        //重设置画笔的颜色和样式
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        //设置边线的宽度
        float strokeWidth=
                TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,2,
                context.getResources().getDisplayMetrics());
        paint.setStrokeWidth(strokeWidth);

        canvas.drawCircle(width/2,height/2,
                radius-strokeWidth/2,paint);

        return backBitmap;
    }
}

