package com.gcc.taotaopiao.app;
import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.Utils.MD5Utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends Activity {
	private TextView tv_tel;
	private TextView tv_username;
	private ImageView iv_back;
	
	private LinearLayout llLayout_tel;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private LinearLayout llLayout_username;
	private EditText et_register_username;
	private EditText et_register_password;
	private EditText et_register_password_again;
	private Button but_register_user;
	
	private LinearLayout ll_001;
	private EditText et_userTel;
	private Button btn_sendCode;
	private CheckBox cb_userAgreement;
	private LinearLayout ll_002;
	private EditText et_register_tel_code;
	private Button btn_repeteSendCode;
	private Button btn_submitCode;
	private LinearLayout ll_003;
	private EditText et_register_tel_password;
	private EditText et_register_tel_password_repete;
	private Button btn_rehister_tel_submit;
	private Task task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initilView();
		task=new Task(btn_sendCode);
		addListener();
	}

	private void addListener() {
		et_userTel.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				String str_tel = et_userTel.getText().toString();
				String regExp1 = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$"; // ^1[3,4,5,7,8]\d{9}$
				Pattern p1 = Pattern.compile(regExp1);
				Matcher m1 = p1.matcher(str_tel);

				String regExp2 = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))"; // ^1[3,4,5,7,8]\d{9}$
				Pattern p2 = Pattern.compile(regExp2);
				Matcher m2 = p2.matcher(str_tel);

				String regExp3 = "^1[3,4,5,7,8]"; // ^1[3,4,5,7,8]\d{9}$
				Pattern p3 = Pattern.compile(regExp3);
				Matcher m3 = p3.matcher(str_tel);
				if (str_tel.length()>0) {
					if ((str_tel.charAt(0) + "").equals("1")) {
						Log.i("TAG", str_tel.charAt(0) + "");
						if (str_tel.length() == 2) {
							Log.i("TAG", "m3.matches()=" + m3.matches());
							if (m3.matches()) {

							} else {
								Toast.makeText(RegisterActivity.this,
										"您输入的手机格式不正确", Toast.LENGTH_SHORT)
										.show();
								et_userTel.setText("");
							}
						}

						if (str_tel.length() == 3) {
							Log.i("TAG", "m2.matches()=" + m2.matches());
							if (m2.matches()) {

							} else {
								Toast.makeText(RegisterActivity.this,
										"您输入的手机格式不正确", Toast.LENGTH_SHORT)
										.show();
								et_userTel.setText("");
							}
						}
						if (str_tel.length() == 11&&m1.matches()) {
							btn_sendCode.setBackgroundColor(getResources()
									.getColor(R.color.colorDefault));
							btn_sendCode.setEnabled(true);
						}else{
							btn_sendCode.setBackgroundColor(getResources()
									.getColor(R.color.colorDefault_GRAY));
							btn_sendCode.setEnabled(false);
						}
						
						if (str_tel.length() > 11) {
							Toast.makeText(RegisterActivity.this,
									"确保您输入的为11位手机号码", Toast.LENGTH_SHORT)
									.show();
							et_userTel.setText("");
						}
					} else {
						Toast.makeText(RegisterActivity.this, "您输入的手机格式不正确",
								Toast.LENGTH_SHORT).show();
						et_userTel.setText("");
					}
				}
			}
		});
		btn_sendCode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(cb_userAgreement.isChecked()){
					ll_001.setVisibility(View.INVISIBLE);
					ll_002.setVisibility(View.VISIBLE);
					task.execute();
				}else{
					Toast.makeText(RegisterActivity.this, "请阅读并同意接受本软件的相关协议",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		iv_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RegisterActivity.this.finish();
			}
		});
		tv_tel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_username.setTextColor(Color.BLACK);
				tv_tel.setTextColor(getResources().getColor(
						R.color.colorDefault));
				llLayout_tel.setVisibility(View.VISIBLE);
				llLayout_username.setVisibility(View.INVISIBLE);
			}
		});

		tv_username.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_tel.setTextColor(Color.BLACK);
				tv_username.setTextColor(getResources().getColor(
						R.color.colorDefault));
				llLayout_username.setVisibility(View.VISIBLE);
				llLayout_tel.setVisibility(View.INVISIBLE);
			}
		});

		but_register_user.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String userName = et_register_username.getText().toString();
				String passWord = et_register_password.getText().toString();
				String passWord_again = et_register_password_again.getText()
						.toString();
				
				String regExp_userName = "^[A-Za-z]{8}$"; // ^1[3,4,5,7,8]\d{9}$
				Pattern p1 = Pattern.compile(regExp_userName);
				Matcher m1 = p1.matcher(userName);
				String regExp_passWord = "^[A-Za-z0-9]{8,16}$"; // ^1[3,4,5,7,8]\d{9}$
				Pattern p2 = Pattern.compile(regExp_passWord);
				Matcher m2 = p2.matcher(passWord);
				User user = new User();
				user.setUsername(userName);
				String passwordMD5=MD5Utils.getMD5ByString(passWord);
				user.setPassword(passwordMD5);
				if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(passWord)||TextUtils.isEmpty(passWord_again)){
					Toast.makeText(RegisterActivity.this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
				}else{
					if(!passWord.equals(passWord_again)){
						Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
					}else{
						if(m1.matches()&&m2.matches()){
						bmobSignUp(user);
						}else{
							Toast.makeText(RegisterActivity.this, "账号或密码格式有误", Toast.LENGTH_SHORT).show();
						}
					}
				}	
			}
		});
	}

			/**
			 * @param user
			 */
			private void bmobSignUp(User user) {
				user.signUp(new SaveListener<User>() {
				    @Override
				    public void done(User s, BmobException e) {
				        if(e==null){
				        	Toast.makeText(RegisterActivity.this, "注册成功",
									Toast.LENGTH_LONG).show();
				        }else{
				        	Toast.makeText(RegisterActivity.this, "登录失败", Toast.LENGTH_LONG).show();	
				        }
				            Log.i("TAG",e+"");
				        }
				});
	}

	private void initilView() {
		textView1 = (TextView) findViewById(R.id.tv_register_tel);
		textView2 = (TextView) findViewById(R.id.tv_register_code);
		textView3 = (TextView) findViewById(R.id.tv_register_password);
		et_userTel = (EditText) findViewById(R.id.et_login_tel);
		btn_sendCode = (Button) findViewById(R.id.bt_register_setCode);
		cb_userAgreement = (CheckBox) findViewById(R.id.checkBox_register_tel);

		iv_back = (ImageView) findViewById(R.id.iv_register_back);
		et_register_username = (EditText) findViewById(R.id.et_register_username);
		et_register_password = (EditText) findViewById(R.id.et_register_password);
		et_register_password_again = (EditText) findViewById(R.id.et_register_password_again);
		but_register_user = (Button) findViewById(R.id.bt_register_password);

		tv_tel = (TextView) findViewById(R.id.tv_register_tel_register);
		tv_username = (TextView) findViewById(R.id.tv_register_username);
		llLayout_tel = (LinearLayout) findViewById(R.id.ll_register_tel);
		llLayout_username = (LinearLayout) findViewById(R.id.ll_register_username);
		
         ll_001=(LinearLayout) findViewById(R.id.ll_001);
           ll_002=(LinearLayout) findViewById(R.id.ll_002);
		et_register_tel_code=(EditText) findViewById(R.id.et_register_tel_code);
		btn_repeteSendCode=(Button) findViewById(R.id.btn_repeteSendCode);
		btn_submitCode=(Button) findViewById(R.id.btn_submitCode);
		ll_003=(LinearLayout) findViewById(R.id.ll_003);
		et_register_tel_password=(EditText) findViewById(R.id.ed_tel_register_password);
		et_register_tel_password_repete=(EditText) findViewById(R.id.ed_tel_register_password_true);
		btn_rehister_tel_submit=(Button) findViewById(R.id.btn_rehister_tel_submit);

		textView1.setTextColor(getResources().getColor(R.color.colorDefault));
		tv_tel.setTextColor(getResources().getColor(R.color.colorDefault));
		btn_sendCode.setEnabled(false);
	}

	class User extends BmobUser {

	}
	static class Task extends AsyncTask<Void, Integer, Void>{
		private int time=60;
		private WeakReference<Button> btn;
		public Task(Button button) {
			btn=new WeakReference<Button>(button);
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
			Button button=btn.get();
			if(button!=null){button.setText("重新发送("+String.valueOf(values[0])+")");
			if(values[0]==0){
				button.setText("重新发送");
			}
			}
		}
	}
	@Override
	protected void onDestroy() {	
		super.onDestroy();
		task.cancel(true);
	}
	
	public void requestBmobSMSCode(String tel,String modelName){
		BmobSMS.requestSMSCode(tel,modelName, new QueryListener<Integer>() {

		    @Override
		    public void done(Integer smsId,BmobException ex) {
		        if(ex==null){//验证码发送成功
		            Log.i("smile", "短信id："+smsId);//用于查询本次短信发送详情
		        }
		    }
		    });
	}
}
	//手机注册
	/*public void verifySmsCode(){
		
	}*/

