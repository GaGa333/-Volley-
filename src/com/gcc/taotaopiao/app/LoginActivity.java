package com.gcc.taotaopiao.app;

import android.os.Bundle;

import com.gcc.taotaopiao.R;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {
	private ImageView iv_login_delete;
	private TextView tv_register;
	private RelativeLayout rl_login_password;
	private Button btn_phone_login;
	private ImageView iv_login_xlwb;
	private ImageView iv_login_qq;
	private ImageView iv_login_wx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initilView();
		addListener();
	}

	private void addListener() {

	}

	private void initilView() {
		iv_login_delete = (ImageView) findViewById(R.id.iv_login_delete);
		tv_register = (TextView) findViewById(R.id.tv_register);
		rl_login_password = (RelativeLayout) findViewById(R.id.rl_login_lock);
		btn_phone_login = (Button) findViewById(R.id.but_login_tel);
		iv_login_xlwb = (ImageView) findViewById(R.id.iv_login_xlwb);
		iv_login_qq = (ImageView) findViewById(R.id.iv_login_qq);
		iv_login_wx = (ImageView) findViewById(R.id.iv_login_wx);

		iv_login_delete.setOnClickListener(this);
		tv_register.setOnClickListener(this);
		rl_login_password.setOnClickListener(this);
		btn_phone_login.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_login_delete:
			this.finish();

			break;
		case R.id.tv_register:
			startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

			break;
		case R.id.rl_login_lock:
			startActivity(new Intent(LoginActivity.this,LoginByPasswordActivity.class));
			break;
		case R.id.but_login_tel:
			startActivity(new Intent(LoginActivity.this,TelLoginActivity.class));
			break;

		default:
			break;
		}

	}

}
