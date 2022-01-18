package com.mt.uniplugin_mt_device_info;

import com.alibaba.fastjson.JSON;

import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

public class Main extends UniModule {
    @UniJSMethod(uiThread = true)
    public void getDeviceInfoStr(UniJSCallback callback) {
        DeviceInfo deviceInfo = DeviceUtil.getDeviceInfo(mUniSDKInstance.getContext());
        callback.invokeAndKeepAlive(JSON.toJSONString(deviceInfo));
    }
}
