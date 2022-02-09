package com.mt.uniplugin_mt_device_info;

import com.blankj.utilcode.util.AppUtils;

public class RestartAPPUtil {
    public static void restartAPP4(Boolean isKillProcess) {
        AppUtils.relaunchApp(isKillProcess);
    }
}
