package com.gcc.taotaopiao.fragment;

import java.util.List;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.BaseFragment.BaseFragment;
import com.gcc.taotaopiao.Utils.CommonDataUtils;
import com.gcc.taotaopiao.Utils.DateDialogCallBack;
import com.gcc.taotaopiao.Utils.TimeFormatUtils;
import com.gcc.taotaopiao.Utils.TrainQueryDataUtils;
import com.gcc.taotaopiao.adapter.RecentTripAdapter;
import com.gcc.taotaopiao.app.StationListSelectActivity;
import com.gcc.taotaopiao.app.TrainListActivity;
import com.gcc.taotaopiao.dal.TripDBDao;
import com.gcc.taotaopiao.entity.RecentTrip;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_train extends BaseFragment implements DateDialogCallBack,
		OnCheckedChangeListener, OnPageChangeListener, OnClickListener, OnItemClickListener {

	private RadioGroup rg;
	private ViewPager pager;
	int[] imgs = { R.drawable.header, R.drawable.ic_call,
			R.drawable.apple_pic};
	private PagerAdapter Padapter;
	private RecentTripAdapter rTAdapter;
	private ListView listView;
	private ArrayAdapter<String> adapter2;
	private List<String> list;
	private TextView textview_startingstation;
	private TextView textview_terminalstation;
	private TextView textView_data;
	private Button button_date;
	private Button button_query;
	private ImageView imageView_swilcher;
	private TextView textView_clear_record;
	List<RecentTrip> trips;
	TripDBDao tripDBDao=null;
	boolean isTrue=false;
	private ImageView iv_sell;
	private ImageView iv_query;
	private ImageView iv_service;
	private ImageView iv_rob;
	private final int query_color=0xFF4AB8ED;
	private final int rob_color=0xFF54CDD5;
	private final int sell_color=0xFFF0BC50;
	private final int service_color=0xFF76d149;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		contentView = inflater.inflate(R.layout.train_fragment, container,
				false);
          tripDBDao = new TripDBDao(getActivity());
          rTAdapter = new RecentTripAdapter(getActivity());
		initialUI();
		return contentView;
	}
	// 初始化最近行程记录
	public void initiazleTrip() {	
		trips = tripDBDao.queryAll();
		Log.i("TAG", trips.size() + "");
		listView = (ListView) contentView
				.findViewById(R.id.listView_trainfragment);
		listView.setAdapter(rTAdapter);
		rTAdapter.addDatas(trips, true);
	}

	@Override
	public void onClick(View v) {
		Intent intent1=new Intent(getActivity(),StationListSelectActivity.class);
		Intent intent2=new Intent(getActivity(),TrainListActivity.class);
		switch (v.getId()) {
		case R.id.button_date:
			CommonDataUtils.showAddDateDialog(getActivity(), "日期", this);
			break;
		case R.id.button_query:
			String starting = textview_startingstation.getText().toString();
			String terminal = textview_terminalstation.getText().toString();
			String date = textView_data.getText().toString();
			RecentTrip recentTrip = new RecentTrip(starting, terminal);
			trips = tripDBDao.queryAll();
			if(trips.size()>0){
				for (RecentTrip result : trips) {
					String sta=result.getStart();
					String ter=result.getEnd();
					if (starting.equals(sta)&&terminal.equals(ter)) {
						isTrue=true;
						break;
					}
				}
			}
			if(trips.size()==0||isTrue==false){
			tripDBDao.addnew(recentTrip);
			}
			isTrue=false;
			/*tripDBDao.addnew(recentTrip);*/
			/*for (RecentTrip result : tripDBDao.queryAll()) {
				if (!result.equals(recentTrip)) {
					tripDBDao.addnew(recentTrip);
				}
			}*/
			/*rTAdapter.notifyDataSetChanged();*/
			// 跳转页面至查询的火车票列表页面
			intent2.putExtra("starting", starting);
			intent2.putExtra("terminal", terminal);
			intent2.putExtra("date", date);
			startActivity(intent2);
			break;
		case R.id.imageView_trainfragment_swilcher:
			String strStarting = textview_startingstation.getText().toString();
			String strTerminal = textview_terminalstation.getText().toString();
			textview_startingstation.setText(strTerminal);
			textview_terminalstation.setText(strStarting);
			break;
		case R.id.textView_clear:
			tripDBDao.deleteData();
			trips=null;
			listView.setAdapter(rTAdapter);
			rTAdapter.addDatas(null, true);
			break;
		case R.id.textView_trainfragment_startingstation:
			startActivityForResult(intent1, 100);
			break;
		case R.id.textView_trainfragment_terminalstation:
			startActivityForResult(intent1, 200);
			break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(data!=null){
		String station=data.getStringExtra("station");
		if(requestCode==100){
			textview_startingstation.setText(station);
		}if(requestCode==200){
			textview_terminalstation.setText(station);
		}
		}
	}

	@Override
	public void getDate(String date) {
		try{
		long chooseDate=TimeFormatUtils.stringToLong(date, "yyyy-MM-dd");
		String currentStr=TimeFormatUtils.longToString(System.currentTimeMillis(), "yyyy-MM-dd");
		long currentDate=TimeFormatUtils.stringToLong(currentStr, "yyyy-MM-dd");
		if(chooseDate>=currentDate){
		textView_data.setText(date);
		}else{
			Toast.makeText(getActivity(), "您选择的时间有误，请重新选择", Toast.LENGTH_SHORT).show();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// 初始化页面指示器
	private void setPagerIndicator() {
		RadioButton rBtn = (RadioButton) contentView.findViewById(R.id.rButton_trainfragment_train);
		rBtn.setChecked(true);
	}

	// 初始化ViewPager对象
	private void setViewPager() {
		pager = (ViewPager) contentView
				.findViewById(R.id.viewpager_trainfragment);
		Padapter = new ImgPagerAdapter();
		pager.setOnPageChangeListener(this);
		pager.setAdapter(Padapter);
		/* pager.setOnPageChangeListener(this); */
	}

	class ImgPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imgs != null ? imgs.length : 0;
		}

		@Override
		// 返回值决定显示或者删除的对象
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		// 初始化item
		public Object instantiateItem(ViewGroup container, int position) {
			// 解析xml,构建对象
			View view = View.inflate(getActivity(),
					R.layout.imgview, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.imageView_top);
			// 获得item中的数据
			int data = imgs[position];
			// 将item数据放到itemview上
			imageView.setImageResource(data);
			// 将item添加到容器viewpage中
			container.addView(view);
			return view;
		}

		@Override
		// 销毁item
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}
	}

	@Override
	public void initialUI() {
		// TODO Auto-generated method stub
		
		        iv_sell=(ImageView) contentView.findViewById(R.id.imageView_sell);
				iv_rob=(ImageView) contentView.findViewById(R.id.imageView_rob);
				iv_service=(ImageView) contentView.findViewById(R.id.imageView_service);
				iv_query=(ImageView) contentView.findViewById(R.id.train_fragment_imageView_query);
				
				Bitmap destBitmap_sell=BitmapFactory.decodeResource(getResources(), R.drawable.ic_sell);
				Bitmap destBitmap_service=BitmapFactory.decodeResource(getResources(), R.drawable.ic_service);
				Bitmap destBitmap_rob=BitmapFactory.decodeResource(getResources(), R.drawable.ic_rob);
				Bitmap destBitmap_query=BitmapFactory.decodeResource(getResources(), R.drawable.ic_query);
				//iv_sell.setImageBitmap(ImageManager.formatBitmap(getActivity(), destBitmap,sell_color));
				//iv_rob.setImageBitmap(ImageManager.formatBitmap(getActivity(), destBitmap_sell,rob_color));
				//iv_service.setImageBitmap(ImageManager.formatBitmap(getActivity(), destBitmap_sell,service_color));
				//iv_query.setImageBitmap(ImageManager.formatBitmap(getActivity(), destBitmap_sell,query_color));

				getBitmap(iv_sell,destBitmap_sell,sell_color);
				getBitmap(iv_rob,destBitmap_rob,rob_color);
				getBitmap(iv_service,destBitmap_service,service_color);
				getBitmap(iv_query,destBitmap_query,query_color);	

		textView_clear_record=(TextView)contentView.findViewById(R.id.textView_clear);
		rg = (RadioGroup) contentView
				.findViewById(R.id.radioGroup_title);
		textView_data = (TextView) contentView
				.findViewById(R.id.textView_trainfragment_data);
		button_date = (Button) contentView.findViewById(R.id.button_date);
		button_query = (Button) contentView.findViewById(R.id.button_query);
		imageView_swilcher = (ImageView) contentView
				.findViewById(R.id.imageView_trainfragment_swilcher);
		imageView_swilcher.setOnClickListener(this);
		textView_clear_record.setOnClickListener(this);
		button_date.setOnClickListener(this);
		button_query.setOnClickListener(this);
		String currentTime = TrainQueryDataUtils.getDataFormat(System
				.currentTimeMillis());
		textView_data.setText(currentTime);
		textview_startingstation = (TextView) contentView
				.findViewById(R.id.textView_trainfragment_startingstation);
		textview_terminalstation = (TextView) contentView
				.findViewById(R.id.textView_trainfragment_terminalstation);
		rg.setOnCheckedChangeListener(this);
		textview_startingstation.setOnClickListener(this);
		textview_terminalstation.setOnClickListener(this);
		// 初始化ViewPager；
		setViewPager();
		// 初始化pager指示器
		setPagerIndicator();
		// 初始化最近行程
		initiazleTrip();
		listView.setOnItemClickListener(this);

	}
	/**
	 * @param destBitmap_sell
	 */
	private void getBitmap(ImageView iv,Bitmap destBitmap,int mColor) {
		int width=destBitmap.getWidth();
		int height=destBitmap.getHeight();
		Bitmap bitmap=Bitmap.createBitmap(80, 80, Config.ARGB_8888);
		Canvas c=new Canvas(bitmap);
		Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(mColor);
		paint.setStyle(Paint.Style.FILL);
		//int radius=Math.min(width,height)/2;
		c.drawCircle(40, 40, 40, paint);
		c.drawBitmap(destBitmap, 20, 20, paint);
		iv.setImageBitmap(bitmap);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int index=rg.indexOfChild(group.findViewById(checkedId));
		if(pager!=null){
		pager.setCurrentItem(index);
		}

	}

	@Override
	public void onPageScrollStateChanged(int position) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		RadioButton radioButton=(RadioButton) rg.getChildAt(position);
		radioButton.setChecked(true);

	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initiazleTrip();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView textView_start=(TextView) view.findViewById(R.id.textview_start);
		TextView textView_end=(TextView) view.findViewById(R.id.textView_end);
		textview_startingstation.setText(textView_start.getText().toString());
		textview_terminalstation.setText(textView_end.getText().toString());

	}

}
