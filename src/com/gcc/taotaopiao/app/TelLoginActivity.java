package com.gcc.taotaopiao.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.BaseListener.BaseTextWatcher;
import com.gcc.taotaopiao.R.id;
import com.gcc.taotaopiao.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class TelLoginActivity extends Activity {
	private ImageView iv_back;
	private EditText et_tel;
	private Button btn_next;
	private ImageView iv_delete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tel_login);
		iv_back=(ImageView) findViewById(R.id.iv_tel_login_back);
		iv_delete=(ImageView) findViewById(R.id.iv_tel_delete);
		et_tel=(EditText) findViewById(R.id.et_tel_login_tel);
		btn_next=(Button) findViewById(R.id.bt_tel_login_btn);
		
		iv_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TelLoginActivity.this.finish();
			}
		});
		
		et_tel.addTextChangedListener(new BaseTextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				String str=et_tel.getText().toString();
				String regExp1 = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$"; // ^1[3,4,5,7,8]\d{9}$
				Pattern p1 = Pattern.compile(regExp1);
				Matcher m1 = p1.matcher(str);
				if(TextUtils.isEmpty(str)){
					iv_delete.setVisibility(View.GONE);
				}else {
					iv_delete.setVisibility(View.VISIBLE);
					if(m1.matches()){
						setButtonListener();
					}
				}
			}
		});
	}
	private void setButtonListener() {
		btn_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String tel=et_tel.getText().toString();
				Intent intent=new Intent(TelLoginActivity.this,TelLoginByCodeActivity.class);
				intent.putExtra("tel", tel);
				startActivity(intent);
			}
		});
	}	
}
