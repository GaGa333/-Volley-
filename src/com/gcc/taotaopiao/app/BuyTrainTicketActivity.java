package com.gcc.taotaopiao.app;

import java.text.ParseException;
import java.util.List;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.BaseFragment.BaseActivity;
import com.gcc.taotaopiao.Manager.HttpLoadTrainsManager;
import com.gcc.taotaopiao.Manager.HttpLoadTrainsManager.GetStationsCodeInfoListener;
import com.gcc.taotaopiao.Manager.HttpLoadTrainsManager.GetTrainListener;
import com.gcc.taotaopiao.Utils.TimeFormatUtils;
import com.gcc.taotaopiao.adapter.StationCodeListAdapter;
import com.gcc.taotaopiao.entity.A;
import com.gcc.taotaopiao.entity.StationCodeInfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BuyTrainTicketActivity extends BaseActivity implements
		OnClickListener {
	private TextView tv_start_station;
	private TextView tv_arrvial_station;
	private TextView tv_start_time;
	private TextView tv_arrvial_time;
	private TextView tv_arrvial_days;
	private TextView tv_yesterday;
	private TextView tv_currentDate;
	private TextView tv_tomorrow;
	private TextView tv_ticket_type_sw_rw;
	private TextView tv_ticket_type_yd_yw;
	private TextView tv_ticket_type_ed_yz;
	private TextView tv_ticket_type_wz;
	private TextView tv_ticket_price_sw_rw;
	private TextView tv_ticket_price_yd_yw;
	private TextView tv_ticket_price_ed_yz;
	private TextView tv_ticket_price_wz;
	private TextView tv_ticket_left_sw_rw;
	private TextView tv_ticket_left_yd_yw;
	private TextView tv_ticket_left_ed_yz;
	private TextView tv_ticket_left_wz;
	private LinearLayout ll_ticket_wz;
	private LinearLayout ll_ticket_sw;
	private LinearLayout ll_ticket_yd;
	private LinearLayout ll_ticket_ed;
	private Button btn_ticket_type_sw_rw;
	private Button btn_ticket_type_yd_yw;
	private Button btn_ticket_type_ed_yz;
	private Button btn_ticket_type_wz;
	private A a;
	private String currentDate;
	private ImageView img_back;
	private ProgressDialog progressDialog;
	String train_code;
	String sst_code;
	String tst_code;
	private LinearLayout ll_skb;
	private ListView listView;
	private Button button_ditu;
	private StationCodeListAdapter stationCodeListAdapter;
	private StationCodeInfo stationsInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_train_ticket);
		Intent intent = getIntent();
		sst_code = intent.getStringExtra("sst_code");
		tst_code = intent.getStringExtra("tst_code");
		currentDate = intent.getStringExtra("currentDate");
		a = (A) intent.getParcelableExtra("A_data");
		initialUI();
		addListener();
	}

	private void addListener() {
		tv_yesterday.setOnClickListener(this);
		tv_tomorrow.setOnClickListener(this);
		btn_ticket_type_sw_rw.setOnClickListener(this);
		btn_ticket_type_yd_yw.setOnClickListener(this);
		btn_ticket_type_ed_yz.setOnClickListener(this);
		btn_ticket_type_wz.setOnClickListener(this);
		img_back = (ImageView) findViewById(R.id.imageView_Actionbar_Left);
		img_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BuyTrainTicketActivity.this.finish();
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(BuyTrainTicketActivity.this,
				LoginActivity.class);
		long querydate;
		switch (v.getId()) {
		case R.id.textView_Yesterday:
			try {
				progressDialog = ProgressDialog.show(this, "鏁版嵁鍔犺浇",
						"姝ｅ湪鍔犺浇鏁版嵁璇风◢鍚�...");
				querydate = TimeFormatUtils.stringToLong(currentDate,
						"yyyy-MM-dd");
				long date1 = querydate - 3600000 * 24;
				currentDate = TimeFormatUtils.longToString(date1, "yyyy-MM-dd");
				requestTrains(sst_code, tst_code, currentDate, true);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case R.id.textView_Tomorrow:
			try {

				progressDialog = ProgressDialog.show(this, "鏁版嵁鍔犺浇",
						"姝ｅ湪鍔犺浇鏁版嵁璇风◢鍚�...");
				querydate = TimeFormatUtils.stringToLong(currentDate,
						"yyyy-MM-dd");
				long date1 = querydate + 3600000 * 24;
				currentDate = TimeFormatUtils.longToString(date1, "yyyy-MM-dd");
				requestTrains(sst_code, tst_code, currentDate, true);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.but_buy_sw_rw:
			// 鍋囪璋冨埌鐧诲綍鐣岄潰
			startActivity(intent);
			break;
		case R.id.but_buy_yd_yw:
			// 鍋囪璋冨埌鐧诲綍鐣岄潰
			startActivity(intent);

			break;
		case R.id.but_buy_ed_yz:
			// 鍋囪璋冨埌鐧诲綍鐣岄潰
			startActivity(intent);

			break;
		case R.id.but_buy_wz:
			// 鍋囪璋冨埌鐧诲綍鐣岄潰
			startActivity(intent);

			break;

		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buy_train_ticket, menu);
		return true;
	}

	@Override
	public void initialUI() {
		String start_station = a.getFrom_station_name();
		String arrvial_station = a.getTo_station_name();
		train_code = a.getTrain_code();

		ll_skb = (LinearLayout) findViewById(R.id.ll_buy_train_skb);
		button_ditu = (Button) findViewById(R.id.btn_ditu);
		listView = (ListView) findViewById(R.id.listView_stationCode);
		stationCodeListAdapter = new StationCodeListAdapter(this);
		listView.setAdapter(stationCodeListAdapter);

		actionbar = (RelativeLayout) findViewById(R.id.actionbar_ticket_list);
		initialActionbar(R.drawable.ic_back2, train_code, -1);
		TextView textView = (TextView) findViewById(R.id.tv_Actionbar_Right);
		textView.setVisibility(View.VISIBLE);

		tv_start_station = (TextView) findViewById(R.id.tv_ticket_startstation);
		tv_arrvial_station = (TextView) findViewById(R.id.tv_ticket_terminusstation);
		tv_start_time = (TextView) findViewById(R.id.tv_ticket_starttime);
		tv_arrvial_time = (TextView) findViewById(R.id.tv_ticket_arrivaltime);
		tv_arrvial_days = (TextView) findViewById(R.id.tv_ticket_arrivaldays);
		tv_yesterday = (TextView) findViewById(R.id.textView_Yesterday);
		tv_currentDate = (TextView) findViewById(R.id.textView_currentDate);
		tv_tomorrow = (TextView) findViewById(R.id.textView_Tomorrow);
		tv_ticket_type_sw_rw = (TextView) findViewById(R.id.tv_ticket_type_sw_rw);
		tv_ticket_type_yd_yw = (TextView) findViewById(R.id.tv_ticket_type_yd_yw);
		tv_ticket_type_ed_yz = (TextView) findViewById(R.id.tv_ticket_type_ed_yz);
		tv_ticket_type_wz = (TextView) findViewById(R.id.tv_ticket_type_wz);
		tv_ticket_price_sw_rw = (TextView) findViewById(R.id.tv_price_sw_rw);
		tv_ticket_price_yd_yw = (TextView) findViewById(R.id.tv_price_yd_yw);
		tv_ticket_price_ed_yz = (TextView) findViewById(R.id.tv_price_ed_yz);
		tv_ticket_price_wz = (TextView) findViewById(R.id.tv_price_wz);
		tv_ticket_left_sw_rw = (TextView) findViewById(R.id.tv_ticket_left_sw_rw);
		tv_ticket_left_yd_yw = (TextView) findViewById(R.id.tv_ticket_left_yd_yw);
		tv_ticket_left_ed_yz = (TextView) findViewById(R.id.tv_ticket_left_ed_yz);
		tv_ticket_left_wz = (TextView) findViewById(R.id.tv_ticket_left_wz);
		btn_ticket_type_sw_rw = (Button) findViewById(R.id.but_buy_sw_rw);
		btn_ticket_type_yd_yw = (Button) findViewById(R.id.but_buy_yd_yw);
		btn_ticket_type_ed_yz = (Button) findViewById(R.id.but_buy_ed_yz);
		btn_ticket_type_wz = (Button) findViewById(R.id.but_buy_wz);
		ll_ticket_wz = (LinearLayout) findViewById(R.id.ll_buy_train_ticket_type_wz);
		ll_ticket_sw = (LinearLayout) findViewById(R.id.ll_buy_train_ticket_type_sw_rw);
		ll_ticket_yd = (LinearLayout) findViewById(R.id.ll_buy_train_ticket_type_yd_yw);
		ll_ticket_ed = (LinearLayout) findViewById(R.id.ll_buy_train_ticket_type_ed_yz);
		setAllViewText();
		requestStationsInfo(train_code,start_station,arrvial_station,currentDate,true);
		textView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ll_skb.setVisibility(View.VISIBLE);
			}
		});

	}

	private void setButtonColor() {
		if (a.getSwz_num().equals("0") || a.getRw_num().equals("0")) {
			btn_ticket_type_sw_rw
					.setBackgroundResource(R.drawable.train_ticket_button_shape3);
			btn_ticket_type_sw_rw.setEnabled(false);
		} else {
			btn_ticket_type_sw_rw
					.setBackgroundResource(R.drawable.buy_ticket_item_button_selector);
			btn_ticket_type_sw_rw.setEnabled(true);
		}
		if (a.getYdz_num().equals("0") || a.getYw_num().equals("0")) {
			btn_ticket_type_yd_yw
					.setBackgroundResource(R.drawable.train_ticket_button_shape3);
			btn_ticket_type_yd_yw.setEnabled(false);
		} else {
			btn_ticket_type_yd_yw
					.setBackgroundResource(R.drawable.buy_ticket_item_button_selector);
			btn_ticket_type_yd_yw.setEnabled(true);
		}
		if (a.getEdz_num().equals("0") || a.getYz_num().equals("0")) {
			btn_ticket_type_ed_yz
					.setBackgroundResource(R.drawable.train_ticket_button_shape3);
			btn_ticket_type_ed_yz.setEnabled(false);
		} else {
			btn_ticket_type_ed_yz
					.setBackgroundResource(R.drawable.buy_ticket_item_button_selector);
			btn_ticket_type_ed_yz.setEnabled(true);
		}
		if (a.getWz_num().equals("0")) {
			btn_ticket_type_wz
					.setBackgroundResource(R.drawable.train_ticket_button_shape3);
			btn_ticket_type_wz.setEnabled(false);
		} else {
			btn_ticket_type_wz
					.setBackgroundResource(R.drawable.buy_ticket_item_button_selector);
			btn_ticket_type_wz.setEnabled(true);
		}
	}

	/**
	 * 
	 */
	private void setAllViewText() {
		setButtonColor();
		tv_currentDate.setText(currentDate);
		tv_start_station.setText(a.getFrom_station_name());
		tv_arrvial_station.setText(a.getTo_station_name());
		tv_start_time.setText(a.getStart_time());
		tv_arrvial_time.setText(a.getArrive_time());
		int days = Integer.parseInt(a.getArrive_days());
		if (days > 0) {
			if (days == 1)
				tv_arrvial_days.setText("娆℃棩");
			if (days == 2)
				tv_arrvial_days.setText("鍚庢棩");
		} else {
			tv_arrvial_days.setVisibility(View.GONE);
		}
		if (a.getTrain_type().equals("G")) {
			Log.i("TAG", "G");
			ll_ticket_wz.setVisibility(View.GONE);
			tv_ticket_type_sw_rw.setText("鍟嗗姟搴�");
			tv_ticket_type_yd_yw.setText("涓�绛夊骇");
			tv_ticket_type_ed_yz.setText("浜岀瓑搴�");
			tv_ticket_price_sw_rw.setText("楼" + a.getSwz_price());
			tv_ticket_price_yd_yw.setText("楼" + a.getYdz_price());
			tv_ticket_price_ed_yz.setText("楼" + a.getEdz_price());
			tv_ticket_left_sw_rw.setText(a.getSwz_num() + "寮�");
			tv_ticket_left_yd_yw.setText(a.getYdz_num() + "寮�");
			tv_ticket_left_ed_yz.setText(a.getEdz_num() + "寮�");
		} else if (a.getTrain_type().equals("C")) {
			Log.i("TAG", "C");
			ll_ticket_sw.setVisibility(View.GONE);
			tv_ticket_type_yd_yw.setText("涓�绛夊骇");
			tv_ticket_type_ed_yz.setText("浜岀瓑搴�");
			tv_ticket_type_wz.setText("鏃犲骇");
			tv_ticket_price_yd_yw.setText("楼" + a.getYdz_price());
			tv_ticket_price_ed_yz.setText("楼" + a.getEdz_price());
			tv_ticket_price_wz.setText("楼" + a.getWz_price());
			tv_ticket_left_yd_yw.setText(a.getYdz_num() + "寮�");
			tv_ticket_left_ed_yz.setText(a.getEdz_num() + "寮�");
			tv_ticket_left_wz.setText(a.getWz_num() + "寮�");
		} else if (a.getTrain_type().equals("D")) {
			Log.i("TAG", "D");
			tv_ticket_type_yd_yw.setText("涓�绛夊骇");
			tv_ticket_type_ed_yz.setText("浜岀瓑搴�");
			tv_ticket_type_wz.setText("鏃犲骇");
			tv_ticket_price_yd_yw.setText("楼" + a.getYdz_price());
			tv_ticket_price_ed_yz.setText("楼" + a.getEdz_price());
			tv_ticket_price_wz.setText("楼" + a.getWz_price());
			tv_ticket_left_yd_yw.setText(a.getYdz_num() + "寮�");
			tv_ticket_left_ed_yz.setText(a.getEdz_num() + "寮�");
			tv_ticket_left_wz.setText(a.getWz_num() + "寮�");
			if (!a.getSwz_num().equals("--")) {
				tv_ticket_type_sw_rw.setText("鍟嗗姟搴�");
				tv_ticket_price_sw_rw.setText("楼" + a.getSwz_price());
				tv_ticket_left_sw_rw.setText(a.getSwz_num() + "寮�");
			} else {
				ll_ticket_sw.setVisibility(View.GONE);
			}
		} else {
			Log.i("TAG", "KTZ");
			Log.i("TAG", a.getYz_num() + "");
			Log.i("TAG", a.getTrain_start_date() + "");
			if (!a.getRw_num().equals("--")) {
				tv_ticket_type_sw_rw.setText("杞崸");
				tv_ticket_price_sw_rw.setText("楼" + a.getRw_price());
				tv_ticket_left_sw_rw.setText(a.getRw_num() + "寮�");
			} else {
				ll_ticket_sw.setVisibility(View.GONE);
			}
			if (!a.getYw_num().equals("--")) {
				tv_ticket_type_yd_yw.setText("纭崸");
				tv_ticket_price_yd_yw.setText("楼" + a.getYw_price());
				tv_ticket_left_yd_yw.setText(a.getYw_num() + "寮�");
			} else {
				ll_ticket_yd.setVisibility(View.GONE);
			}
			if (!a.getYz_num().equals("--")) {
				tv_ticket_type_ed_yz.setText("纭骇");
				tv_ticket_price_ed_yz.setText("楼" + a.getYz_price());
				tv_ticket_left_ed_yz.setText(a.getYz_num() + "寮�");
			} else {
				ll_ticket_ed.setVisibility(View.GONE);
			}
			if (!a.getWz_num().equals("--")) {
				tv_ticket_type_wz.setText("鏃犲骇");
				tv_ticket_price_wz.setText("楼" + a.getWz_price());
				tv_ticket_left_wz.setText(a.getWz_num() + "寮�");
			} else {
				ll_ticket_wz.setVisibility(View.GONE);
			}
		}
	}

	// 寮傛鍔犺浇鍒楄溅鏃跺埢琛ㄧ殑鏁版嵁
	public void requestStationsInfo(String stationCode, String start_station,
			String terminal_station, String date, final boolean isClear) {
		HttpLoadTrainsManager.getStationCodeData(this, stationCode,
				start_station, terminal_station, date,
				new GetStationsCodeInfoListener() {

					@Override
					public void getStations(List<StationCodeInfo> stationInfos) {
						if (stationInfos != null) {
							stationCodeListAdapter.addDatas(stationInfos, true);
						} else {
							Toast.makeText(BuyTrainTicketActivity.this,
									"浜诧紝鏃犵鍚堟潯浠剁殑杞︽淇℃伅", Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	public void requestTrains(String start_station, String terminal_station,
			String date, final boolean isClear) {
		HttpLoadTrainsManager.getTrainsData(this, start_station,
				terminal_station, date, new GetTrainListener() {
					@Override
					public void getTrains(List<A> trains5) {
						progressDialog.dismiss();
						ll_ticket_sw.setVisibility(View.VISIBLE);
						ll_ticket_yd.setVisibility(View.VISIBLE);
						ll_ticket_ed.setVisibility(View.VISIBLE);
						ll_ticket_wz.setVisibility(View.VISIBLE);
						a = null;
						if (trains5 != null) {
							for (A aa : trains5) {
								if (aa.getTrain_code().equals(train_code)) {
									Log.i("TAG", train_code);
									a = aa;
									setAllViewText();
								} else {
									continue;
								}
							}
							if (a == null) {
								setNoTrainInfo();
							}

						} else {
							setNoTrainInfo();
						}
					}
				});
	}

	public void setNoTrainInfo() {
		ll_ticket_sw.setVisibility(View.GONE);
		ll_ticket_yd.setVisibility(View.GONE);
		ll_ticket_ed.setVisibility(View.GONE);
		ll_ticket_wz.setVisibility(View.GONE);
		tv_currentDate.setText(currentDate);
		Toast.makeText(BuyTrainTicketActivity.this, "浜诧紝褰撴棩鏃犵鍚堟潯浠剁殑杞︽",
				Toast.LENGTH_SHORT).show();
	}

}
