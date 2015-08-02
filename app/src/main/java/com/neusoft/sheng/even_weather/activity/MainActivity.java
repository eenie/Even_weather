package com.neusoft.sheng.even_weather.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.neusoft.sheng.even_weather.R;
import com.neusoft.sheng.even_weather.net.GetData;
import com.neusoft.sheng.even_weather.utils.Weatherinterface;


public class MainActivity extends AppCompatActivity {

    TextView text_location_info;
    Button btn_getlocation;
    LocationManager locationManager;
    LocationListener locationListener;
    Handler handler;
    String result;
    String lat;
    String lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        location();


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


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);


    }

    View.OnClickListener btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            new Thread() {
                @Override
                public void run() {

                    if (lat != null || lng != null) {
                        String args = "lng=" + lng + "&lat=" + lat + "&from=1&needMoreDay=1&needIndex=1";
                        result = GetData.requestByGet(Weatherinterface.weatherApi, args, "ee08b51f33d09d3601e22bb28b29e40c");
                        if (result != null) {
                            handler.sendEmptyMessage(1);
                        }
                    }else

                    {
                        System.out.println("正在等待GPS数据，请稍后再试...");
                    }

                }
            }.start();


        }


    };


}
