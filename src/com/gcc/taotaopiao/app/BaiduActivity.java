package com.gcc.taotaopiao.app;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.gcc.taotaopiao.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BaiduActivity extends Activity {
	private MapView mapView;
	BaiduMap baiduMap;
    //定位
    LocationClient locationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baidu);
		//addListener();
		
		
		/*mapView = (MapView) findViewById(R.id.mapView);
        baiduMap = mapView.getMap();
		
		locationClient = new LocationClient(this);
        MyBdLocationListener listener = new MyBdLocationListener();
        locationClient.registerLocationListener(listener);
        //设置定位参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        //每隔2秒钟定位一次
        //少于1000只得一次
        option.setScanSpan(2000);
        locationClient.setLocOption(option);
        //开始定位
        locationClient.start();*/
		
		
	}

	private void addListener() {
		baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

			@Override
			public void onMapClick(LatLng latLng) {
				MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.map_overlay_blue);
                markerOptions.icon(bitmapDescriptor);
                baiduMap.addOverlay(markerOptions);
			}

			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		}

	
	class MyBdLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            try {
                //4.9E1=49
                //4.9E-1=0.49
                //4.9E-324=0.000000000000000000000000
                //用原生模拟器得到值是4.9E-324
                //得纬度
                double latitude = bdLocation.getLatitude();
                //得经度
                double longitude = bdLocation.getLongitude();
                //移动地图中心点
                //latlng实体类，放的是坐标信息
                LatLng currentPosition = new LatLng(latitude, longitude);
                //地图显示级别4-17 17地图显示最详细
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
                        .newLatLngZoom(currentPosition, 17);
                //以动画的方式移动到currentPosition
                baiduMap.animateMapStatus(mapStatusUpdate);

                //在地图上加一个图片
                MarkerOptions markerOptions = new MarkerOptions();
                //设置图片在地图上的位置
                markerOptions.position(currentPosition);
                //创建地图上的图片
                BitmapDescriptor bitmapDescriptor =
                        BitmapDescriptorFactory.fromResource(R.drawable.map_overlay_blue);
                markerOptions.icon(bitmapDescriptor);
                //把图片放在地图上
                baiduMap.addOverlay(markerOptions);

            } catch (Exception e) {
                
            }
        }
    }

	

}
