package com.neusoft.sheng.even_weather.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.neusoft.sheng.even_weather.R;
import com.neusoft.sheng.even_weather.funtion.LocationApplication;




public class MainActivity extends Activity {

    TextView text_location_info;
    Button btn_getlocation;
    LocationManager locationManager;
    LocationListener locationListener;
    Handler handler;
    String result;
    String lat;
    String lng;


    private LocationClient mLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        location();




        mLocationClient = ((LocationApplication)getApplication()).mLocationClient;

        ((LocationApplication)getApplication()).mLocationResult=text_location_info;


        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {


                if (msg.what == 1) {

                    System.out.println(result);
                }
                else if (msg.what==2)
                {


                }

            }
        };


    }

    private void init() {

        text_location_info = (TextView) findViewById(R.id.text_location_info);
        btn_getlocation = (Button) findViewById(R.id.btn_getlocation);
        btn_getlocation.setOnClickListener(btn_listener);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    }


    private void location() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


                lat = String.valueOf(location.getLatitude());
                lng = String.valueOf(location.getLongitude());
                text_location_info.setText("经度：" + lat + "\n" + "纬度：" + lng);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                System.out.println("GPS is open");

            }

            @Override
            public void onProviderDisabled(String provider) {
                System.out.println("GPS is close");

            }
        };


      //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);


    }

    View.OnClickListener btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            initLocation();
mLocationClient.start();
            System.out.println(mLocationClient.getVersion());


//            new Thread() {
//                @Override
//                public void run() {
//
//                    if (lat != null || lng != null) {
//                        String args = "lng=" + lng + "&lat=" + lat + "&from=1&needMoreDay=1&needIndex=1";
//                        result = GetData.requestByGet(Weatherinterface.weatherApi, args, "ee08b51f33d09d3601e22bb28b29e40c");
//                        if (result != null) {
//                            handler.sendEmptyMessage(1);
//                        }
//                    }else
//
//                    {
//                        System.out.println("正在等待GPS数据，请稍后再试...");
//                    }
//
//                }
//            }.start();


        }


    };




    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系，
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClient.setLocOption(option);
    }

}
