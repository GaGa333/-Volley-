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
    //��λ
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
        //���ö�λ����
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        //ÿ��2���Ӷ�λһ��
        //����1000ֻ��һ��
        option.setScanSpan(2000);
        locationClient.setLocOption(option);
        //��ʼ��λ
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
                //��ԭ��ģ�����õ�ֵ��4.9E-324
                //��γ��
                double latitude = bdLocation.getLatitude();
                //�þ���
                double longitude = bdLocation.getLongitude();
                //�ƶ���ͼ���ĵ�
                //latlngʵ���࣬�ŵ���������Ϣ
                LatLng currentPosition = new LatLng(latitude, longitude);
                //��ͼ��ʾ����4-17 17��ͼ��ʾ����ϸ
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
                        .newLatLngZoom(currentPosition, 17);
                //�Զ����ķ�ʽ�ƶ���currentPosition
                baiduMap.animateMapStatus(mapStatusUpdate);

                //�ڵ�ͼ�ϼ�һ��ͼƬ
                MarkerOptions markerOptions = new MarkerOptions();
                //����ͼƬ�ڵ�ͼ�ϵ�λ��
                markerOptions.position(currentPosition);
                //������ͼ�ϵ�ͼƬ
                BitmapDescriptor bitmapDescriptor =
                        BitmapDescriptorFactory.fromResource(R.drawable.map_overlay_blue);
                markerOptions.icon(bitmapDescriptor);
                //��ͼƬ���ڵ�ͼ��
                baiduMap.addOverlay(markerOptions);

            } catch (Exception e) {
                
            }
        }
    }

	

}
