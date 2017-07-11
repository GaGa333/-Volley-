package com.gcc.taotaopiao.fragment;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.BaseFragment.BaseFragment;
import com.gcc.taotaopiao.app.LoginActivity;
import com.gcc.taotaopiao.app.MyAPP;
import com.gcc.taotaopiao.app.TelLoginActivity;
import com.gcc.taotaopiao.app.TelLoginByCodeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_personal_account_ extends BaseFragment implements OnClickListener {
	private LinearLayout ll_user_logon;
	private LinearLayout ll_my_order;
	private LinearLayout ll_my_discount;
	private LinearLayout ll_my_recharge;
	private LinearLayout ll_main_setting;
	private LinearLayout ll_feedback;
	private TextView tv_name;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		contentView = inflater.inflate(R.layout.personal_fragment, container, false);
		initialUI();
		return contentView;
	}

	@Override
	public void initialUI() {
		ll_user_logon = (LinearLayout) contentView.findViewById(R.id.ll_user_logon);
		ll_my_order = (LinearLayout) contentView.findViewById(R.id.ll_my_order);
		ll_my_discount = (LinearLayout) contentView.findViewById(R.id.ll_my_discount);
		ll_my_recharge = (LinearLayout) contentView.findViewById(R.id.ll_my_recharge);
		ll_main_setting = (LinearLayout) contentView.findViewById(R.id.ll_main_setting);
		ll_feedback = (LinearLayout) contentView.findViewById(R.id.ll_feedback);
		tv_name=(TextView) contentView.findViewById(R.id.tv_username);

		ll_user_logon.setOnClickListener(this);
		ll_my_order = (LinearLayout) contentView.findViewById(R.id.ll_my_order);
		ll_my_discount = (LinearLayout) contentView.findViewById(R.id.ll_my_discount);
		ll_my_recharge = (LinearLayout) contentView.findViewById(R.id.ll_my_recharge);
		ll_main_setting = (LinearLayout) contentView.findViewById(R.id.ll_main_setting);
		ll_feedback = (LinearLayout) contentView.findViewById(R.id.ll_feedback);
		//setName();
	}
	public void setName(){
		if(MyAPP.user!=null){
		String str=MyAPP.user.getUsername().toString();
		if(TextUtils.isEmpty(str)){
			tv_name.setText("Î´µÇÂ¼");
		}else {
			tv_name.setText(str);
		}
		}else{
			Toast.makeText(getActivity(), "userÎª¿Õ", Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_user_logon:
			Intent intent=new Intent(getActivity(),LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_my_order:

			break;
		case R.id.ll_my_discount:

			break;
		case R.id.ll_my_recharge:

			break;
		case R.id.ll_main_setting:

			break;
		case R.id.ll_feedback:

			break;

		default:
			break;
		}

	}

}
