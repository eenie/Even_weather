package com.neusoft.sheng.even_weather.activity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.neusoft.sheng.even_weather.R;
import com.neusoft.sheng.even_weather.funtion.MyLocationListener;

import java.util.List;


public class MainActivity  extends Activity {

    TextView text_location_info;
    Button btn_getlocation, btn_get;
    LocationManager locationManager;
    MyLocationListener locationListener;



    public LocationClient mLocationClient = null;
    //public BDLocationListener bdLocationListener=new MyLocationListener();


    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        mLocationClient = new LocationClient(getApplicationContext());
        locationListener = new MyLocationListener();

        mLocationClient.registerLocationListener(locationListener);




        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        btn_get = (Button) findViewById(R.id.btn_get);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

                Toast.makeText(getApplicationContext(), String.valueOf(sensors.size()), Toast.LENGTH_LONG).show();


            }
        });





    }

    private void init() {

        text_location_info = (TextView) findViewById(R.id.text_location_info);
        btn_getlocation = (Button) findViewById(R.id.btn_getlocation);
        btn_getlocation.setOnClickListener(btn_listener);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    }


    View.OnClickListener btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            initLocation();
            mLocationClient.start();
            System.out.println(mLocationClient.getVersion());


        }


    };


    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，
        int span = 0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(false);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClient.setLocOption(option);
    }

}
