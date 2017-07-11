package com.gcc.taotaopiao.app;

import android.os.AsyncTask;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import com.gcc.taotaopiao.R;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class In_MainActivity extends Activity implements AnimationListener{
	private TextView textview_djs;private Task task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_in_main);
		ImageView image=(ImageView) findViewById(R.id.imageView_in_main);
		Animation animator=AnimationUtils.loadAnimation(this, R.anim.alpha_in_main);
		image.setAnimation(animator);
		animator.setAnimationListener(this);
		textview_djs=(TextView) findViewById(R.id.textView_djs);
		task=new Task(textview_djs);
		task.execute();
	}
	
	static class Task extends AsyncTask<Void, Integer, Void>{
		private int time=2;
		private WeakReference<TextView> tv;
		public Task(TextView tV) {
			tv=new WeakReference<TextView>(tV);
		}
		@Override
		protected Void doInBackground(Void... params) {
			for(;time>=0;time--){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress(time);
				;}
			return null;
	}
		@Override
		protected void onProgressUpdate(Integer... values) {
			TextView text=tv.get();
			if(text!=null)text.setText(String.valueOf(values[0]));
			
		}
	}
	@Override
	protected void onDestroy() {	
		super.onDestroy();
		task.cancel(true);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		startActivity(new Intent(In_MainActivity.this,MainActivity.class));
		finish();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	
}
