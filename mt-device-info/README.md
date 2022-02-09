# mt-device-info

## 介绍

1. 获取安卓设备信息，包括：wifiMac, imei, sn, id, androidId, system_model

2. 重启设备
（后期会增加其他基础信息的查询）

## 使用文档

### 引入

```javascript
const deviceUtil = uni.require('mt-device-info');
```

### 获取基础信息
```javascript
deviceUtil.getDeviceInfoStr((deviceInfo) => {
  console.log(deviceInfo);
})
```

### 重启 app （uni.runtime.restart() 不满足需求的情况下）
```javascript
deviceUtil.restartApp(); // 重启不杀进程
deviceUtil.restartAppAndKillProcess(); // 杀进程后再重启
```