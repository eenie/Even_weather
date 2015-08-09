package com.neusoft.sheng.even_weather.funtion;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

/**
 * Created by sheng on 2015/8/8.
 */
public class MyLocationListener implements BDLocationListener{


        @Override
        public void onReceiveLocation(BDLocation location) {

            StringBuffer sb = new StringBuffer(256);

            sb.append("\n经度:" + location.getLatitude());
            sb.append("\n纬度:" + location.getLongitude());


//            sb.append("time : ");
//            sb.append(location.getTime());
//            sb.append("\nerror code : ");
//            sb.append(location.getLocType());
//            sb.append("\nlatitude : ");
//            sb.append(location.getLatitude());
//            sb.append("\nlontitude : ");
//            sb.append(location.getLongitude());
//            sb.append("\nradius : ");
//            sb.append(location.getRadius());
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS��λ���
//                sb.append("\nspeed : ");
//                sb.append(location.getSpeed());// ��λ������ÿСʱ
//                sb.append("\nsatellite : ");
//                sb.append(location.getSatelliteNumber());
//                sb.append("\nheight : ");
//                sb.append(location.getAltitude());// ��λ����
//                sb.append("\ndirection : ");
//                sb.append(location.getDirection());
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                sb.append("\ndescribe : ");
//                sb.append("gps��λ�ɹ�");
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// ���綨λ���
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                //��Ӫ����Ϣ
//                sb.append("\noperationers : ");
//                sb.append(location.getOperators());
//                sb.append("\ndescribe : ");
//                sb.append("���綨λ�ɹ�");
//            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// ���߶�λ���
//                sb.append("\ndescribe : ");
//                sb.append("���߶�λ�ɹ������߶�λ���Ҳ����Ч��");
//            } else if (location.getLocType() == BDLocation.TypeServerError) {
//                sb.append("\ndescribe : ");
//                sb.append("��������綨λʧ�ܣ����Է���IMEI�źʹ��嶨λʱ�䵽loc-bugs@baidu.com��������׷��ԭ��");
//            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                sb.append("\ndescribe : ");
//                sb.append("���粻ͬ���¶�λʧ�ܣ����������Ƿ�ͨ��");
//            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                sb.append("\ndescribe : ");
//                sb.append("�޷���ȡ��Ч��λ���ݵ��¶�λʧ�ܣ�һ���������ֻ���ԭ�򣬴��ڷ���ģʽ��һ���������ֽ�����������������ֻ�");
//            }
//            sb.append("\nlocationdescribe : ");// λ�����廯��Ϣ
//            sb.append(location.getLocationDescribe());
//            List<Poi> list = location.getPoiList();// POI��Ϣ
//            if (list != null) {
//                sb.append("\npoilist size = : ");
//                sb.append(list.size());
//                for (Poi p : list) {
//                    sb.append("\npoi= : ");
//                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
//                }
//            }
          //  logMsg(sb.toString());
            Log.i("BaiduLocationApiDem", sb.toString());
        }




    }









