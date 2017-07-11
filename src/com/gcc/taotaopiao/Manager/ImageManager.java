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
     * ��ʽ��ͷ�����Բ�δ���
     * @param context
     * @param bitmap �������ԭʼͼƬ
     * @return
     */
    public  static Bitmap formatBitmap(
        Context context,
        Bitmap bitmap,int mColor){
            //���ͷ��Ŀ�
        int width=bitmap.getWidth();
        //���ͷ��ĸ�
        int height=bitmap.getHeight();
        Bitmap backBitmap=Bitmap.createBitmap(
                width,height,
                Bitmap.Config.ARGB_8888);
        //����һ������
        Canvas canvas=new Canvas(backBitmap);

        //����һ������
        Paint paint=new Paint();
        //���û��ʵ���ɫ
        paint.setColor(mColor);
        //���û��ʵĿ����
        paint.setAntiAlias(true);

        //Բ�İ뾶
        int radius=Math.min(width,height)/2;
        //����Բ
        canvas.drawCircle(width/2,height/2,radius,paint);

        //����ͼ���ཻģʽ
        paint.setXfermode(
                new PorterDuffXfermode(
                PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,0,0,paint);
        //�����û��ʵ���ɫ����ʽ
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        //���ñ��ߵĿ��
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

