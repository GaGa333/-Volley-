package com.gcc.taotaopiao.app;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.BaseFragment.BaseActivity;
import com.gcc.taotaopiao.Manager.HttpLoadTrainsManager;
import com.gcc.taotaopiao.Manager.HttpLoadTrainsManager.GetTrainListener;
import com.gcc.taotaopiao.Manager.TrainBiz;
import com.gcc.taotaopiao.Utils.TimeFormatUtils;
import com.gcc.taotaopiao.adapter.TrainAdapter;
import com.gcc.taotaopiao.dal.St_codeDBDao;
import com.gcc.taotaopiao.entity.A;
import com.gcc.taotaopiao.entity.TrainType_DEntity;
import com.gcc.taotaopiao.entity.TrainType_GEntity;
import com.gcc.taotaopiao.entity.TrainType_KTZEntity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
public class TrainListActivity extends BaseActivity implements OnClickListener, OnItemClickListener{
	private ImageView img_back;
	private TrainAdapter trainAdapter;
	private List<A> trains;
	private ListView listView;
	private TextView textView_yesday;
	private TextView textView_tomorrow;
	private TextView textView_querydate;
	ProgressDialog progressDialog;
	private SwipeRefreshLayout sRefreshLayout;
	private TextView tv_result_num;
	
	private TextView tv_train_number_type_select;
	private RelativeLayout rl_train_type_select_dailog;
	private TextView tv_trains_list_true;
	private TextView tv_trains_list_flase;
	private CheckBox checkBox_type_G;
	private RelativeLayout rl_train_G_yp;
	private CheckBox checkBox_G_sw;
	private CheckBox checkBox_G_yd;
	private CheckBox checkBox_G_ed;
	
	private CheckBox checkBox_type_D;
	private RelativeLayout rl_train_D_yp;
	private CheckBox checkBox_D_sw;
	private CheckBox checkBox_D_yd;
	private CheckBox checkBox_D_ed;
	private CheckBox checkBox_D_wz;
	
	private CheckBox checkBox_type_KTZ;
	private RelativeLayout rl_train_KTZ_yp;
	private CheckBox checkBox_KTZ_rw;
	private CheckBox checkBox_KTZ_yw;
	private CheckBox checkBox_KTZ_yz;
	private CheckBox checkBox_KTZ_wz;
	private CheckBox checkBox_type_L;
	
	private RadioGroup radioGroup_train_select;
	private RadioButton rb_alltime;
	private RadioButton radio_6_12;
	private RadioButton radio_12_18;
	private RadioButton radio_18_24;
	
	TrainType_GEntity type_GEntity;
	TrainType_DEntity type_DEntity;
	TrainType_KTZEntity type_KTZEntity;
	boolean LisChecked=false;
	
	private TextView tv_train_starttime_type_select;
	private TextView tv_train_consuming_select;
	private TextView tv_train_ticket_left_select;

	String startingstation;
	String terminalstation;
	String date;
	St_codeDBDao st_codeDBDao=new St_codeDBDao(this);
	String sst_code;
	String tst_code;
	Animation animation;
	private boolean showSelectDialog=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_train_list);
		type_GEntity=new TrainType_GEntity();
		type_DEntity=new TrainType_DEntity();
		type_KTZEntity=new TrainType_KTZEntity();
		initialUI();
		setSwipeRefreshLayout();
	}
	private void setSwipeRefreshLayout() {
		// TODO Auto-generated method stub
		sRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		sRefreshLayout.setColorScheme(R.color.colorGreen, R.color.colorBule, R.color.colorYellow, R.color.colorRed);
		sRefreshLayout.setOnRefreshListener(new OnRefreshListener() {			
			@Override
			public void onRefresh() {
				showDialog();
				requestTrains(sst_code, tst_code, date,true);
			}
		});
	}
	@Override
	public void initialUI() {
		Intent intent=getIntent();
		startingstation=intent.getStringExtra("starting");
		terminalstation=intent.getStringExtra("terminal");
		sst_code=st_codeDBDao.querySt_codeByStation(startingstation);
		tst_code=st_codeDBDao.querySt_codeByStation(terminalstation);
		date=intent.getStringExtra("date");
		String trip=startingstation+" 至  "+terminalstation;
		actionbar=(RelativeLayout) findViewById(R.id.actionbar_train_list);
		initialActionbar(R.drawable.ic_back2, trip, R.drawable.ic_title_rob2);
		img_back=(ImageView) findViewById(R.id.imageView_Actionbar_Left);
		img_back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				TrainListActivity.this.finish();	
			}
		});
				
		progressDialog=ProgressDialog.show(this, "数据加载", "正在加载数据请稍后...");
		
		tv_result_num=(TextView) findViewById(R.id.textView_result_num);
		
		textView_querydate=(TextView) findViewById(R.id.textView_currentDate);
		textView_yesday=(TextView) findViewById(R.id.textView_Yesterday);
		textView_tomorrow=(TextView) findViewById(R.id.textView_Tomorrow);
		//火车票搜索条件筛选控件初始化
		tv_train_number_type_select=(TextView) findViewById(R.id.tv_select);
		tv_train_starttime_type_select=(TextView) findViewById(R.id.tv_start_time);
		tv_train_consuming_select=(TextView) findViewById(R.id.tv_select_consuming);
		tv_train_ticket_left_select=(TextView) findViewById(R.id.tv_select_tickets_left);
		rl_train_type_select_dailog=(RelativeLayout) findViewById(R.id.rl_trians_select_dailog);
		tv_trains_list_true=(TextView) findViewById(R.id.tv_trains_list_true);
		tv_trains_list_flase=(TextView) findViewById(R.id.tv_trains_list_flase);
		checkBox_type_G=(CheckBox) findViewById(R.id.checkBox_type_G);
		rl_train_G_yp=(RelativeLayout) findViewById(R.id.rl_train_G_yp);
		checkBox_G_sw=(CheckBox) findViewById(R.id.checkBox_G_sw);
		checkBox_G_yd=(CheckBox) findViewById(R.id.checkBox_G_yd);
		checkBox_G_ed=(CheckBox) findViewById(R.id.checkBox_G_ed);
		
		checkBox_type_D=(CheckBox) findViewById(R.id.checkBox_type_D);
		rl_train_D_yp=(RelativeLayout) findViewById(R.id.rl_train_D_yp);
		checkBox_D_sw=(CheckBox) findViewById(R.id.checkBox_type_D_sw);
		checkBox_D_yd=(CheckBox) findViewById(R.id.checkBox_type_D_yd);
		checkBox_D_ed=(CheckBox) findViewById(R.id.checkBox_type_D_ed);
		checkBox_D_wz=(CheckBox) findViewById(R.id.checkBox_type_D_wz);
		
		checkBox_type_KTZ=(CheckBox) findViewById(R.id.checkBox_type_KTZ);
		rl_train_KTZ_yp=(RelativeLayout) findViewById(R.id.rl_train_KTZ_yp);
		checkBox_KTZ_rw=(CheckBox) findViewById(R.id.checkBox_type_KTZ_rw);
		checkBox_KTZ_yw=(CheckBox) findViewById(R.id.checkBox_type_KTZ_yw);
		checkBox_KTZ_yz=(CheckBox) findViewById(R.id.checkBox_type_KTZ_yz);
		checkBox_KTZ_wz=(CheckBox) findViewById(R.id.checkBox_type_KTZ_wz);
		checkBox_type_L=(CheckBox) findViewById(R.id.checkBox_type_L);
		
		radioGroup_train_select=(RadioGroup) findViewById(R.id.radioGroup_train_select);
		//火车票搜索条件筛选控件设置监听器
		tv_train_number_type_select.setOnClickListener(this);
		tv_train_starttime_type_select.setOnClickListener(this);
		tv_train_consuming_select.setOnClickListener(this);
		tv_train_ticket_left_select.setOnClickListener(this);
		
		//设置监听器
		textView_tomorrow.setOnClickListener(this);
		textView_yesday.setOnClickListener(this);
		
		//初始化日期title
		textView_querydate.setText(date);
		
		
		listView=(ListView) findViewById(R.id.listView_train_list);
		trainAdapter=new TrainAdapter(this);
		listView.setAdapter(trainAdapter);
		listView.setOnItemClickListener(this);
		requestTrains(sst_code, tst_code, date,true);
	}
	public void addtv_trains_list_trueListener(){
		tv_trains_list_true.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				rl_train_type_select_dailog.setVisibility(View.INVISIBLE);
				showDialog();
				requestTrains(sst_code, tst_code, date,true);
				Log.i("TAG", trains.size()+"");
				Log.i("TAG", type_GEntity.isChecked+"");
				Log.i("TAG", trains.size()+"");
			}
		});	
	}
	public void addtv_trains_list_flaseListener(){
		tv_trains_list_flase.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rl_train_type_select_dailog.setVisibility(View.INVISIBLE);
				checkBox_type_G.setChecked(false);
				checkBox_type_D.setChecked(false);
				checkBox_type_KTZ.setChecked(false);
				checkBox_type_L.setChecked(false);
			}
		});
	}
	//火车票搜索条件筛选框中的子控件设置监听器
	public void addCheckBox_type_ALLListener(){
		checkBox_G_sw.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_G_yd.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_G_ed.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_D_sw.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_D_yd.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_D_ed.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_D_wz.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_KTZ_rw.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_KTZ_yw.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_KTZ_yz.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());
		checkBox_KTZ_wz.setOnCheckedChangeListener(new MyaddCheckBox_type_ALLListener());	
	}
	class MyaddCheckBox_type_ALLListener implements OnCheckedChangeListener{
			@Override
			public void onCheckedChanged(CompoundButton v,
					boolean isChecked) {
				switch (v.getId()) {
				case R.id.checkBox_G_sw:
					if(isChecked){
						type_GEntity.sw_checked=true;
					}else {
						type_GEntity.sw_checked=false;
					}
					break;
				case R.id.checkBox_G_yd:
					if(isChecked){
						type_GEntity.yd_checked=true;
					}else {
						type_GEntity.yd_checked=false;
					}
					break;
				case R.id.checkBox_G_ed:
					if(isChecked){
						type_GEntity.ed_checked=true;
					}
					else {
						type_GEntity.ed_checked=false;
					}
					break;
				case R.id.checkBox_type_D_sw:
					if(isChecked){
						type_DEntity.sw_checked=true;
					}else {
						type_DEntity.sw_checked=false;
					}
					break;
				case R.id.checkBox_type_D_yd:
					if(isChecked){
						type_DEntity.yd_checked=true;
					}else {
						type_DEntity.yd_checked=false;
					}
					break;
				case R.id.checkBox_type_D_ed:
					if(isChecked){
						type_DEntity.ed_checked=true;
					}else {
						type_DEntity.ed_checked=false;
					}
					break;
				case R.id.checkBox_type_D_wz:
					if(isChecked){
						type_DEntity.wz_checked=true;
					}else {
						type_DEntity.wz_checked=false;
					}
					break;
				case R.id.checkBox_type_KTZ_rw:
					if(isChecked){
						type_KTZEntity.rw_checked=true;
					}else {
						type_KTZEntity.rw_checked=false;
					}
					break;
				case R.id.checkBox_type_KTZ_yw:
					if(isChecked){
						type_KTZEntity.yw_checked=true;
					}else {
						type_KTZEntity.yw_checked=false;
					}
					break;
				case R.id.checkBox_type_KTZ_yz:
					if(isChecked){
						type_KTZEntity.yz_checked=true;
					}else {
						type_KTZEntity.yz_checked=false;
					}
					break;
				case R.id.checkBox_type_KTZ_wz:
					if(isChecked){
						type_KTZEntity.wz_checked=true;
					}else {
						type_KTZEntity.wz_checked=false;
					}
					break;

				default:
					break;
				}
			}
		
	}

	public void addCheckBox_type_GListener(){
		
		checkBox_type_G.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					rl_train_G_yp.setVisibility(View.VISIBLE);
					type_GEntity.isChecked=true;
					addCheckBox_type_ALLListener();
					Log.i("TAG", type_GEntity.ed_checked+"type_GEntity.ed_checked");
				}else{
					rl_train_G_yp.setVisibility(View.INVISIBLE);
					type_GEntity.isChecked=false;
					/*checkBox_G_sw.setChecked(false);
					checkBox_G_yd.setChecked(false);
					checkBox_G_ed.setChecked(false);
					type_GEntity.sw_checked=false;
					type_GEntity.yd_checked=false;
					type_GEntity.ed_checked=false;*/
				}
			}
		});
	}
	public void addCheckBox_type_DListener(){
		
		checkBox_type_D.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					rl_train_D_yp.setVisibility(View.VISIBLE);
					type_DEntity.isChecked=true;
					addCheckBox_type_ALLListener();
				}else{
					rl_train_D_yp.setVisibility(View.INVISIBLE);
					type_DEntity.isChecked=false;
					/*checkBox_D_sw.setChecked(false);
					checkBox_D_yd.setChecked(false);
					checkBox_D_ed.setChecked(false);
					checkBox_D_wz.setChecked(false);
					type_DEntity.sw_checked=false;
					type_DEntity.yd_checked=false;
					type_DEntity.ed_checked=false;
					type_DEntity.wz_checked=false;*/
				}
			}
		});
	}
	public void addCheckBox_type_KTZListener(){
		
		checkBox_type_KTZ.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					rl_train_KTZ_yp.setVisibility(View.VISIBLE);
					type_KTZEntity.isChecked=true;
					addCheckBox_type_ALLListener();
				}else{
					rl_train_KTZ_yp.setVisibility(View.INVISIBLE);
					type_KTZEntity.isChecked=false;
					/*checkBox_KTZ_rw.setChecked(false);
					checkBox_KTZ_yw.setChecked(false);
					checkBox_KTZ_yz.setChecked(false);
					checkBox_KTZ_wz.setChecked(false);
					type_KTZEntity.rw_checked=false;
					type_KTZEntity.yw_checked=false;
					type_KTZEntity.yz_checked=false;
					type_KTZEntity.wz_checked=false;*/
				}
			}
		});
	}
	
	public void showDialog(){
		progressDialog=ProgressDialog.show(this, "数据加载", "正在加载数据请稍后...");
	}
	
	@Override
	public void onClick(View v) {
		long querydate;
		switch (v.getId()) {
		case R.id.textView_Yesterday:
			try {
				progressDialog=ProgressDialog.show(this, "数据加载", "正在加载数据请稍后...");
				querydate = TimeFormatUtils.stringToLong(date, "yyyy-MM-dd");
				long date1=querydate-3600000*24;
				date=TimeFormatUtils.longToString(date1, "yyyy-MM-dd");	
				textView_querydate.setText(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			requestTrains(sst_code, tst_code, date,true);
			afterDateChange();
			//trains=TrainBiz.getTrainsByNumberTypeSelected(type_GEntity, type_DEntity, type_KTZEntity, LisChecked, trains);
			break;
		case R.id.textView_Tomorrow:
			try {
				progressDialog=ProgressDialog.show(this, "数据加载", "正在加载数据请稍后...");
				querydate = TimeFormatUtils.stringToLong(date, "yyyy-MM-dd");
				long date1=querydate+3600000*24;
				date=TimeFormatUtils.longToString(date1, "yyyy-MM-dd");	
				textView_querydate.setText(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			requestTrains(sst_code, tst_code, date,true);
			afterDateChange();
			//trains=TrainBiz.getTrainsByNumberTypeSelected(type_GEntity, type_DEntity, type_KTZEntity, LisChecked, trains);
			break;
		case R.id.tv_select:
			try {
				tv_train_starttime_type_select.setTextColor(Color.WHITE);
				tv_train_consuming_select.setTextColor(Color.WHITE);
				tv_train_ticket_left_select.setTextColor(Color.WHITE);
				if(showSelectDialog){
				tv_train_number_type_select.setTextColor(0xFF5495E5);
				rl_train_type_select_dailog.setVisibility(View.VISIBLE);
				animation=AnimationUtils.loadAnimation(TrainListActivity.this, R.anim.trains_type_select_scale);
				rl_train_type_select_dailog.setAnimation(animation);
				addCheckBox_type_DListener();
				addCheckBox_type_GListener();
				addCheckBox_type_KTZListener();
				addtv_trains_list_trueListener();
				addtv_trains_list_flaseListener();
				showSelectDialog=false;
				}else{
					rl_train_type_select_dailog.setVisibility(View.INVISIBLE);
					tv_train_number_type_select.setTextColor(Color.WHITE);
					showSelectDialog=true;
				}
				
				//showSelectDialog=true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_start_time:
			try {
				tv_train_consuming_select.setTextColor(Color.WHITE);
				tv_train_ticket_left_select.setTextColor(Color.WHITE);
				tv_train_number_type_select.setTextColor(Color.WHITE);
				tv_train_starttime_type_select.setTextColor(0xFF5495E5);
				rl_train_type_select_dailog.setVisibility(View.INVISIBLE);
				trains=getTrainsByCompareToLeft(trains);
				trainAdapter.addDatas(trains, true);
				listView.smoothScrollToPosition(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_select_consuming:
			try {
				tv_train_ticket_left_select.setTextColor(Color.WHITE);
				tv_train_number_type_select.setTextColor(Color.WHITE);
				tv_train_starttime_type_select.setTextColor(Color.WHITE);
				tv_train_consuming_select.setTextColor(0xFF5495E5);
				rl_train_type_select_dailog.setVisibility(View.INVISIBLE);
				trains=getTrainsByCompareToArrayTime(trains);
				trainAdapter.addDatas(trains, true);
				listView.smoothScrollToPosition(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_select_tickets_left:
			try {
				tv_train_number_type_select.setTextColor(Color.WHITE);
				tv_train_starttime_type_select.setTextColor(Color.WHITE);
				tv_train_consuming_select.setTextColor(Color.WHITE);
				tv_train_ticket_left_select.setTextColor(0xFF5495E5);
				rl_train_type_select_dailog.setVisibility(View.INVISIBLE);
				trains=getTrainsByCompareToStartTime(trains);
				trainAdapter.addDatas(trains, true);
				listView.smoothScrollToPosition(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
	}
	//日期改变后初始化UI
	public void afterDateChange(){
		tv_train_number_type_select.setTextColor(Color.WHITE);
		tv_train_starttime_type_select.setTextColor(Color.WHITE);
		tv_train_consuming_select.setTextColor(Color.WHITE);
		tv_train_ticket_left_select.setTextColor(Color.WHITE);
	}
	//请求加载火车票的网络数据
	public void requestTrains(String start_station,String terminal_station,String date,final boolean isClear){
		HttpLoadTrainsManager.getTrainsData(this, start_station, terminal_station, date, new GetTrainListener() {	
			@Override
			public void getTrains(List<A> trains5) {
				// TODO Auto-generated method stub
				progressDialog.dismiss();
				if(trains5!=null){
					trainAdapter.removeDatas();
					trains=trains5;
					trains=TrainBiz.getTrainsByNumberTypeSelected(type_GEntity, type_DEntity, type_KTZEntity, LisChecked, trains);
					if(trains.size()>0){
				trainAdapter.addDatas(trains, isClear);
				tv_result_num.setVisibility(View.VISIBLE);
				tv_result_num.setText("已为您加载到"+trains.size()+"条火车信息");
				listView.smoothScrollToPosition(0);
				}else{
					trainAdapter.removeDatas();
					tv_result_num.setVisibility(View.GONE);
					Toast.makeText(TrainListActivity.this, "亲，当日无符合条件的车次", Toast.LENGTH_SHORT).show();
				}
				}else{	
					trainAdapter.removeDatas();
					tv_result_num.setVisibility(View.GONE);
					Toast.makeText(TrainListActivity.this, "亲，当日无符合条件的车次", Toast.LENGTH_SHORT).show();
				}
				sRefreshLayout.setRefreshing(false);	
			}
		});	
	}
	public List<A> getTrainsByCompareToLeft(List<A> list){
		for(A a:list){
			Log.i("TAG",a.getTrain_code()+"    "+a.getRun_time_minute());
		}
		 Collections.sort(list,new Comparator<A>() {  
	            //这里可以再Person中实现 Comparator<T>接口，重写compare方法  
	            @Override  
	            public int compare(A o1, A o2) { 
	                 //return (Integer.parseInt(o1.getRun_time_minute())+"").compareTo(Integer.parseInt(o2.getRun_time_minute())+"");
	            	int time1=Integer.parseInt(o1.getRun_time_minute());
	            	int time2=Integer.parseInt(o2.getRun_time_minute());
	            	if(time1>time2){
	            		return 1;
	            	}if(time1==time2){
	            		return 0;
	            	}else {
	            		return -1;
					}
	            }     
	        });  
		return list;
	}
	public List<A> getTrainsByCompareToArrayTime(List<A> list){
		 Collections.sort(list,new Comparator<A>() {  
	            //这里可以再Person中实现 Comparator<T>接口，重写compare方法  
	            @Override  
	            public int compare(A o1, A o2) { 
	                 //return (Integer.parseInt(o1.getRun_time_minute())+"").compareTo(Integer.parseInt(o2.getRun_time_minute())+"");
	            	try{
	            	int arrivalDay1=Integer.parseInt(o1.getArrive_days());
	            	int arrivalDay2=Integer.parseInt(o2.getArrive_days());
	            	
	            	String arrivalTime1="2017-6-21"+" "+o1.getArrive_time();
	            	String arrivalTime2="2017-6-21"+" "+o2.getArrive_time();
	            	long arrivalTimeFormat1=TimeFormatUtils.stringToLong(arrivalTime1, "yyyy-MM-dd HH:mm")+arrivalDay1*24*60*60*1000;
	            	long arrivalTimeFormat2=TimeFormatUtils.stringToLong(arrivalTime2, "yyyy-MM-dd HH:mm")+arrivalDay2*24*60*60*1000;
	            		if(arrivalTimeFormat1>arrivalTimeFormat2){
	            			return 1;
	            		}
						else if(arrivalTimeFormat1==arrivalTimeFormat2){
							return 0;
						}else {
							return -1;
						}
	            }catch(Exception e){
	            	e.printStackTrace();
	            	return 0;
	            }
	            }
	        });  
		return list;
	}
	public List<A> getTrainsByCompareToStartTime(List<A> list){
		 Collections.sort(list,new Comparator<A>() {  
	            //这里可以再Person中实现 Comparator<T>接口，重写compare方法  
	            @Override  
	            public int compare(A o1, A o2) { 
	                 //return (Integer.parseInt(o1.getRun_time_minute())+"").compareTo(Integer.parseInt(o2.getRun_time_minute())+"");
	            	try{
	            	String arrivalTime1="2017-6-21"+" "+o1.getStart_time();
	            	String arrivalTime2="2017-6-21"+" "+o2.getStart_time();
	            	long arrivalTimeFormat1=TimeFormatUtils.stringToLong(arrivalTime1, "yyyy-MM-dd HH:mm");
	            	long arrivalTimeFormat2=TimeFormatUtils.stringToLong(arrivalTime2, "yyyy-MM-dd HH:mm");
	            		if(arrivalTimeFormat1<arrivalTimeFormat2){
	            			return -1;
	            		}else if(arrivalTimeFormat1==arrivalTimeFormat2){
	            			return 0;
	            		}else{
							return 1;
						}
	            	}
	            catch(Exception e){
	            	e.printStackTrace();
	            	return 0;
	            }
	            }  
	        });  
		 for(A a:list){
			 try{
		Log.i("TAG", a.getStart_time()+"        "+TimeFormatUtils.stringToLong("2017-6-21"+" "+a.getStart_time(), "yyyy-MM-dd HH:mm"));
			 }catch(Exception e){
				 
			 }
		 }
		return list;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		A a=trains.get(position);
		Intent intent=new Intent(TrainListActivity.this,BuyTrainTicketActivity.class);
		intent.putExtra("currentDate", date);
		intent.putExtra("sst_code", sst_code);
		intent.putExtra("tst_code", tst_code);
		intent.putExtra("A_data", a); 
		startActivity(intent);
	}
}
