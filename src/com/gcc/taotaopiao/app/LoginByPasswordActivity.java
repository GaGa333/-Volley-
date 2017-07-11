package com.gcc.taotaopiao.app;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import android.os.Bundle;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.BaseListener.BaseTextWatcher;
import com.gcc.taotaopiao.Utils.MD5Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class LoginByPasswordActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_by_password);
		et_password=(EditText) findViewById(R.id.et_password);
		cb_password=(CheckBox) findViewById(R.id.iv_password_find);
		remeberUser=(CheckBox)findViewById(R.id.Cb_number);
		et_admin = (EditText) findViewById(R.id.et_phone);
		b_logon=(Button) findViewById(R.id.b_logon);
		iv_clean = (ImageView) findViewById(R.id.iv_end);
		adminOnClickListener();
		passwordOnClickListener();
		setAdminText();
		setOnEditorActionListener();
		ButtonOnClickListener();
		
	}
	private EditText et_admin;
	private EditText et_password;
	private ImageView iv_clean;
	private CheckBox  cb_password;
   private CheckBox remeberUser;
   private Button b_logon;
	
	//自定义软键盘登陆监听方法
	public void setOnEditorActionListener(){
	et_password.setOnEditorActionListener(new OnEditorActionListener() {
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if(EditorInfo.IME_ACTION_DONE==actionId){
				logon();
			}
			return false;
		}
	});
	}
	//获取用户名输入文本的初始数据
	public void setAdminText(){
	SharedPreferences sp=getSharedPreferences("user_zl", Context.MODE_PRIVATE);
	String phone=sp.getString("phone", "");
	et_admin.setText(phone);}
	//为登录按钮配置一个监听对象并调用里面的监听方法
	public void ButtonOnClickListener(){
		b_logon.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				logon();
			}
		});
	}
	
	//自定义一个点击按钮判断用户是否成功登录的方法
		public void logon(){
			String userName=et_admin.getText().toString();
			String passWord=et_password.getText().toString();
			passwordOnRemeberListener(userName);
			/*if(TextUtils.isEmpty(admin)||TextUtils.isEmpty(password)){
				et_admin.setError(getString(R.string.phone_error));
				return;
			}if(admin.length()!=11){
				et_admin.setError(getString(R.string.error_error));
				return;
			}
				startActivity(new Intent(this,GuideInMainActivity.class));
							
		}*/

				MyUser  user=new MyUser();
				user.setUsername(userName);
				String passwordMD5=MD5Utils.getMD5ByString(passWord);
				user.setPassword(passwordMD5);
				
				user.login(new SaveListener<MyUser>() {

				    @Override
				    public void done(MyUser user, BmobException e) {
				        if(e==null){
				        	Toast.makeText(LoginByPasswordActivity.this, "登录成功", Toast.LENGTH_LONG).show();	
				            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
				            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
				        }else{
				        	Toast.makeText(LoginByPasswordActivity.this, "登录失败", Toast.LENGTH_LONG).show();	
				        }
				    }
				});
		}
		
		
		//为是否记住密码的CheckBox控件(设置一个监听对象，并)创建一个选中控件的监听方法;
		public void passwordOnRemeberListener(String s){
					if(remeberUser.isChecked()){
				SharedPreferences sp=
				getSharedPreferences("user_zl", Context.MODE_PRIVATE);
				Editor editor=sp.edit();
				editor.putString("phone", s);
				editor.commit();
			}
		}
//自定义一个判断密码输入格式正确与否的方法
	private void passwordOnClickListener() {
			cb_password.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					et_password.setInputType(0x91);
				}else{
					et_password.setInputType(0x81);
				}
			}
			
		});
	}
//自定义一个用户名输入格式正确与否的方法
	private void adminOnClickListener() {
		et_admin.addTextChangedListener(new BaseTextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				String admin = et_admin.getText().toString();
				if (TextUtils.isEmpty(admin)) {
					iv_clean.setVisibility(View.GONE);
				} else {
					iv_clean.setVisibility(View.VISIBLE);
				}
				iv_clean.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						et_admin.setText(null);
					}
				});
			}
		});
		}
    class MyUser extends BmobUser{
		
	}

}
