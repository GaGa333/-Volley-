package com.gcc.taotaopiao.CustomWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class MyCricleView extends View{

	public MyCricleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public MyCricleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyCricleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
	private int radius=8;
	@Override
	protected void onDraw(Canvas canvas) {
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.STROKE);
			canvas.drawCircle(getWidth()/2, 8, radius, paint);
		//paint.setColor(Color.RED);
		//paint.setStyle(Style.FILL);
	}

}
