package com.gcc.taotaopiao.app;

import java.util.List;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.BaseListener.BaseTextWatcher;
import com.gcc.taotaopiao.CustomWidget.SideBar;
import com.gcc.taotaopiao.CustomWidget.SideBar.OnTouchingLetterChangedListener;
import com.gcc.taotaopiao.adapter.StationListAdapter;
import com.gcc.taotaopiao.dal.St_codeDBDao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StationListSelectActivity extends Activity implements OnClickListener,OnItemClickListener{
	 private EditText editText_gjz;
	 private ImageView img_delete;
	 private StationListAdapter adapter;
	 private List<String> stations;
	 private ListView listView;
	 private SideBar sideBar;
	 private TextView dialog;
	 St_codeDBDao st_codeDBDao=null;
	 private Button bt_city;
	 private ImageView img_location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_station_list_select);
		st_codeDBDao=new St_codeDBDao(this);
		adapter=new StationListAdapter(this);
		listView=(ListView) findViewById(R.id.listView_group);
		initiaView();
		addListener();
	}

	private void addListener() {
		adminOnClickListener();
		img_delete.setOnClickListener(this);
        img_location.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(StationListSelectActivity.this,BaiduActivity.class));
				
			}
		});
		
	}

	private void initiaView() {
		stations=st_codeDBDao.getALLStationByPy();
		initiaListView();
		editText_gjz=(EditText) findViewById(R.id.editText_gjz);
		bt_city=(Button) findViewById(R.id.bt_city);
		img_location=(ImageView) findViewById(R.id.imageView_location);
		img_delete=(ImageView) findViewById(R.id.imageView_delete);
		dialog=(TextView) findViewById(R.id.textView_py);
		sideBar=(SideBar) findViewById(R.id.listView_py);
		sideBar.setTextView(dialog);
        sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {	
			@Override
			public void OnTouchingLetterChanged(String choosePy) {
				Log.i("TAG", choosePy);
				String py_down=choosePy.toLowerCase();
				stations=st_codeDBDao.getAllStationByPy(py_down);
				initiaListView();
			}
		});
		//实现对listView每个Item的监听
		listView.setOnItemClickListener(this);	
	}
	

	/**
	 *初始化ListView
	 */
	private void initiaListView() {		
		listView.setAdapter(adapter);
		adapter.addDatas(stations, true);
	}
	public void adminOnClickListener(){
		editText_gjz.addTextChangedListener(new BaseTextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				String gjz=editText_gjz.getText().toString();
				if(!TextUtils.isEmpty(gjz)){
					String str=gjz.charAt(0)+"";
					img_delete.setVisibility(View.VISIBLE);
					//判断是否为A~Z的大小写字母
					//是则按拼音来查询车站
					//不是则按汉字查询车站
					if(str.matches("[A-Z]")||str.matches("[a-z]")){
						
						if(str.matches("[A-Z]")){
							gjz=gjz.toLowerCase();
						}
						if(gjz.length()==1){
							stations=st_codeDBDao.getAllStationByPy(gjz);
							initiaListView();
						}else{
							stations=st_codeDBDao.getStationByPy(gjz);
							initiaListView();
						}	
					}else{
						//不是则按汉字查询车站
						if(gjz.length()==1){
							stations=st_codeDBDao.getStationByHzFirst(gjz);
							initiaListView();
						}else{
							stations=st_codeDBDao.getStationByHz(gjz);
							initiaListView(); 
						}	
					}
				}else{
					img_delete.setVisibility(View.GONE);
					stations=st_codeDBDao.getALLStationByPy();
					initiaListView();
				}	
			}
		});
	}
	@Override
	public void onClick(View v) {
		editText_gjz.setText(null);		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		String station=stations.get(position);
		Toast.makeText(StationListSelectActivity.this, station,Toast.LENGTH_LONG).show();
		Intent intent=new Intent();
		intent.putExtra("station", station);
		this.setResult(100, intent);
		this.setResult(200, intent);
		this.finish();
         
	}
	

}
