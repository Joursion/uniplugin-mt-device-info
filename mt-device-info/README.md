# mt-device-info

## 介绍

获取安卓设备信息，包括：wifiMac, imei, sn, id, androidId, system_model

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