package com.mt.uniplugin_mt_device_info;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/***
 * 该服务只用来让APP重启，生命周期也仅仅是只是重启APP。重启完即自我杀死
 */
public class KillSelfService extends Service {
    /**关闭应用后多久重新启动*/
    private static  long stopDelayed = 2000;
    private Handler handler;
    private String PackageName;
    public KillSelfService() {
        handler=new Handler();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        stopDelayed = intent.getLongExtra("Delayed",2000);
        PackageName = intent.getStringExtra("PackageName");
        Log.e("StartCommand", "PackageName: " + PackageName);
        handler.postDelayed(() -> {
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
            startActivity(LaunchIntent);
            KillSelfService.this.stopSelf();
        }, stopDelayed);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}