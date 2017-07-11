package com.gcc.taotaopiao.CustomWidget;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.R.color;

import android.R.bool;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View{
	Paint paint=new Paint();
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	
	public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener listener){
		this.onTouchingLetterChangedListener=listener;
	}
	public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
		"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
		"W", "X", "Y", "Z" };
	public static int isClicked=-1;
	private TextView mTextDialog;
	
	public void setTextView(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int width=getWidth();
		int height=getHeight();
		int singleHeight=height/b.length;	
		for(int i=0;i<b.length;i++){
			paint.setColor(Color.rgb(33, 65, 98));
			/*paint.setColor(color.colorDefault);*/
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(20);			
			if(i==isClicked){
				/*paint.setColor(Color.parseColor("#3399ff"));*/
				paint.setColor(color.colorRed);
				paint.setFakeBoldText(true);
			}
			float x=width/2-paint.measureText(b[i])/2;
			float y=singleHeight*i+singleHeight;
			String str=b[i];
			canvas.drawText(str, x, y, paint);
			paint.reset();
		}
	}
	public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public SideBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		final int action=event.getAction();
		final float yOff=event.getY();
		 int choose=isClicked;
		int c=(int)yOff/(getHeight()/b.length);
		 final OnTouchingLetterChangedListener listener=onTouchingLetterChangedListener;
		switch (action) {
		case MotionEvent.ACTION_UP:
			//ÉèÖÃ±³¾°ÑÕÉ«
			setBackground(new ColorDrawable(0x00000000));
			isClicked=-1;
			//Ë¢ÐÂview
			invalidate();
			if(mTextDialog!=null){
				mTextDialog.setVisibility(INVISIBLE);
			}
			break;
		default:
			setBackgroundResource(R.drawable.sidebar_background);
			
			if(c!=choose){
				if(c>=0&&c<b.length){
					if(listener!=null){
						listener.OnTouchingLetterChanged(b[c]);
					}
					if(mTextDialog!=null){
						mTextDialog.setVisibility(VISIBLE);
						mTextDialog.setText(b[c]);
							}
					c=choose;
					invalidate();
				}			
			}
			break;
		}
		return true;
	}
	
	public interface OnTouchingLetterChangedListener{
		public void OnTouchingLetterChanged(String choosePy);
	}
}
