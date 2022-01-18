package com.mt.uniplugin_mt_device_info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresApi;

import java.net.NetworkInterface;

public class DeviceUtil {
    public static String getWifiMacAddress() {
        String macAddress;
        StringBuilder buf = new StringBuilder();
        NetworkInterface networkInterface;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            } else {
                byte[] address = networkInterface.getHardwareAddress();
                for (byte ad : address) {
                    buf.append(String.format("%02X", ad));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                macAddress = buf.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;
    }

    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    @SuppressLint("HardwareIds")
    public static String getSerialNumber() {
        try {
            return Build.SERIAL;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getID() {
        return Build.ID;
    }

    public static String getSystemModel() {
        return Build.MODEL;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"ServiceCast", "HardwareIds"})
    public static String getIMEI(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q ||
                Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return null;
        }
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELECOM_SERVICE);
            if (tm == null) {
                return null;
            }
            return tm.getDeviceId();
        } catch (Exception e) {
            return null;
        }
    }

    public static DeviceInfo getDeviceInfo(Context context) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.androidID = getAndroidID(context);
        deviceInfo.wifiMac = getWifiMacAddress();
        deviceInfo.sn = getSerialNumber();
        deviceInfo.id = getID();
        deviceInfo.sm = getSystemModel();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            deviceInfo.imei = getIMEI(context);
        }
        return deviceInfo;
    }
}
