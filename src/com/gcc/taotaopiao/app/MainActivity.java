package com.gcc.taotaopiao.app;
import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.Manager.NetWorkManager;
import com.gcc.taotaopiao.fragment.Fragment_bus;
import com.gcc.taotaopiao.fragment.Fragment_personal_account_;
import com.gcc.taotaopiao.fragment.Fragment_plane;
import com.gcc.taotaopiao.fragment.Fragment_train;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener {
	private ViewPager vPager;
	private RadioGroup rGroup;
	private boolean isNetWorkConnected=false;
	private boolean isClosed=false; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		setRadioGroup();
		setViewPager();
	}
	private void setRadioGroup(){
		rGroup = (RadioGroup) findViewById(R.id.radiogroup_main);
		rGroup.setOnCheckedChangeListener(this);
		RadioButton rBtn=(RadioButton) rGroup.findViewById(R.id.radio_train);
		rBtn.setChecked(true);
	}
	
	private void setViewPager(){
		vPager = (ViewPager) 
		findViewById(R.id.viewpager);
		//2.构建适配器
		FragmentPagerAdapter adapter=
		new MainFrgAdapter(
		getSupportFragmentManager());
		//3.关联适配器
		vPager.setAdapter(adapter);
		//4.添加监听器
		vPager.setOnPageChangeListener(this);
	}
	class MainFrgAdapter extends FragmentPagerAdapter{
		public MainFrgAdapter(FragmentManager fm) {
			super(fm);
		}
		@Override
		public Fragment getItem(int position) {
			if(position==0){
				return new Fragment_train();
			}else if(position==1){
				return new Fragment_plane();
			}else if(position==2){
				return new Fragment_bus();
			}else{
				return new Fragment_personal_account_();
			}
		}
		@Override
		public int getCount() {
			return 4;
		}
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int index=group.indexOfChild(group.findViewById(checkedId));
		if(vPager!=null){
			vPager.setCurrentItem(index);
		}
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}
	@Override
	public void onPageSelected(int position) {
		if(rGroup!=null){
			RadioButton rBtn=(RadioButton)
			rGroup.getChildAt(position);
			rBtn.setChecked(true);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_, menu);
		return true;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		isNetWorkConnected=NetWorkManager.isNetWorkConnected(this);
		if(!isNetWorkConnected){
			new AlertDialog.Builder(MainActivity.this).setTitle("网络检查").
		setMessage("无网络连接，请检查网络设置").setPositiveButton("确定",null).
		setIcon(R.drawable.icon_no_net).show();
		}
	}
	Handler handler=new Handler(){
	public void handleMessage(android.os.Message msg) {
		if(msg.what==0){
		isClosed=false;
		}
	};
	};
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(!isClosed){
			isClosed=true;
			Toast.makeText(MainActivity.this, "再点一次退出", Toast.LENGTH_SHORT).show();
			handler.sendEmptyMessageDelayed(0,3000);
		}else {
			finish();
		}
		
	}
	
  

}
