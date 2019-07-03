package com.nyw.libthird.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.CoordinateConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BakerJ
 * @date 2018/7/15
 */
public class LocationClient implements AMapLocationListener, LocationListener {
    private static volatile LocationClient INSTANCE;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private List<AMapLocationListener> listeners = new ArrayList<>();
    private AMapLocation aMapLocation;
    private LocationManager locationManager;
    private CoordinateConverter coordinateConverter;

    private LocationClient(Context context) {
        initLocation(context);
    }

    public static synchronized LocationClient getDetault(Context context) {
        if (INSTANCE == null) {
            synchronized (LocationClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocationClient(context);
                }
            }
        }
        return INSTANCE;
    }

    private void initLocation(Context context) {
        //初始化client
        locationClient = new AMapLocationClient(context.getApplicationContext());
        locationOption = getDefaultOption();
        locationClient.setLocationOption(locationOption);
        locationClient.setLocationListener(this);
        coordinateConverter = new CoordinateConverter(context);
        coordinateConverter.from(CoordinateConverter.CoordType.GPS);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
//        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
//        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
//        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
//        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,
// 会自动变为单次定位，持续定位时不要使用
//        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption
//                .AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
//        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
//        mOption.setWifiScan(true);
// 可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
//        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
//        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);
        //可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    public void start() {
//        if (!locationClient.isStarted()) {
//        locationClient.startLocation();
        List<String> providers = locationManager.getProviders(true);
        String provider;
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            provider = LocationManager.GPS_PROVIDER;
        }
        locationManager.requestSingleUpdate(provider, this, null);
        onLocationChanged(locationManager.getLastKnownLocation(provider));
//        }
    }

    public void stop() {
//        if (locationClient.isStarted()) {
//        locationClient.stopLocation();
        locationManager.removeUpdates(this);
//        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
//        double[] location = delta(aMapLocation.getLatitude(), aMapLocation.getLongitude());
//        aMapLocation.setLatitude(location[0]);
//        aMapLocation.setLongitude(location[1]);
        this.aMapLocation = aMapLocation;
        for (AMapLocationListener listener : listeners) {
            listener.onLocationChanged(aMapLocation);
        }
    }

    public void register(AMapLocationListener listener) {
        if (!listeners.contains(listener))
            listeners.add(listener);
    }

    public void unRegister(AMapLocationListener listener) {
        listeners.remove(listener);
    }

    public double[] getLastLocation() {
        double[] location = {0, 0};
        if (aMapLocation != null) {
            location[0] = aMapLocation.getLongitude();
            location[1] = aMapLocation.getLatitude();
        }
        return location;
    }

    private double[] delta(double lat, double lon) {
        double a = 6378245.0;//克拉索夫斯基椭球参数长半轴a
        double ee = 0.00669342162296594323;//克拉索夫斯基椭球参数第一偏心率平方
        double dLat = this.transformLat(lon - 105.0, lat - 35.0);
        double dLon = this.transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * Math.PI;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * Math.PI);

        return new double[]{lat - dLat, lon - dLon};
    }

    //转换经度
    private double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x / 30.0 * Math.PI)) * 2.0 / 3.0;
        return ret;
    }

    //转换纬度
    private double transformLat(double x, double y) {
        double ret =
                -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y * Math.PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    @Override
    public void onLocationChanged(Location location) {
//        DPoint dPoint = new DPoint();
//        dPoint.setLatitude(location.getLatitude());
//        dPoint.setLongitude(location.getLongitude());
//        try {
//            dPoint = coordinateConverter.convert();
//            location.setLatitude(dPoint.getLatitude());
//            location.setLongitude(dPoint.getLongitude());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (location == null) {
            location = new Location(LocationManager.GPS_PROVIDER);
        }
        AMapLocation aMapLocation = new AMapLocation(location);
        onLocationChanged(aMapLocation);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
