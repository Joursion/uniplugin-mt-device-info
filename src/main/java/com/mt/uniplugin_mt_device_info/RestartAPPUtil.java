package com.mt.uniplugin_mt_device_info;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

public class RestartAPPUtil {
    public static void restartApp5(Context context, Boolean isKillProcess) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        if (intent == null) return;
        intent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
        );
        context.startActivity(intent);
        if (!isKillProcess) {
            return;
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static void restartApp1(Context context, long Delayed) {
        Intent intent1 = new Intent(context, KillSelfService.class);
        Log.e("RestartAPPUtil", context.getPackageName());
        intent1.putExtra("PackageName", context.getPackageName());
        intent1.putExtra("Delayed", Delayed);
        context.startService(intent1);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    public static void restartApp2(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
