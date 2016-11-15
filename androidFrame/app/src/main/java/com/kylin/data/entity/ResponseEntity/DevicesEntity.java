package com.kylin.data.entity.ResponseEntity;

/**
 * Created by kylinhuang on 02/11/2016.
 */

public class DevicesEntity {

    private String uuid;        //"uuid": "B0:CE:18:FF:02:2B",
    private String name;        // "name": "SNAP_02_2B",
    private String ip;          //"ip": "192.168.1.110",
    private String version;     //"version": "2.2.108",
    private String brightness;  //"brightness": 100,
    private int definitionLevel;    //"definitionLevel": 2,

    private int snapOnoff;          //"snapOnoff": 0,
    private int lampOnoff;          //"lampOnoff": 1,
    private int cameraOnoff;        //"cameraOnoff": 0,
    private String playUrl ;        //"playUrl": "rtsps://101.68.222.221:1554/91B86EE0765A4D49935FA1A0548CD240.sdp",
    private String voiceTalkUrl ;   //"voiceTalkUrl":"rtsp://101.68.222.221:2554/91B86EE0765A4D49935FA1A0548CD240_230a00969adc238b.sdp",
    private String voiceTalkStatus ;//"voiceTalkStatus":3,
    private String micOnoff ;       //"micOnoff":1,
    private String micVolume ;      //"micVolume":0,
    private String speakerOnoff ;   //"speakerOnoff":0,
    private String speakerVolume ;  //"speakerVolume":0,
    private String lampAutomaticOnoff ;     //"lampAutomaticOnoff":0,
    private String lightSensorValue   ;     //"lightSensorValue":null,
    private String setSensorValue ;         // "setSensorValue":null,
    private String roiOnoff ;       //"roiOnoff":0,
    private String timezone ;       //"timezone":"GMT+8:00",
    private String timezoneCity ;   //"timezoneCity":"Asia/Shanghai",
    private String description ;    //"description":"",
    private String bigImage ;       //"bigImage":"https://jx1.amazon-storage.test.cloud.sengled.cn:9000/amazon-storage/download?bucketName=sengled-test-image-cn-north-1&filename=91B86EE0765A4D49935FA1A0548CD240_big.jpg",
    private String smallImage ;     //"smallImage":"https://jx1.amazon-storage.test.cloud.sengled.cn:9000/amazon-storage/download?bucketName=sengled-test-image-cn-north-1&filename=91B86EE0765A4D49935FA1A0548CD240_small.jpg",
    private String nightVersionOnoff ; //"nightVersionOnoff":2,
//    private String;"dimmingRecallOnoff":1,
//    private String;"isKeepAlive":0,
//    private String;"isMediaOnline":0,
//    private String;"shareOnoff":0,
//    private String;"recordOnoff":1,
//    private String;"securityStatus":0,
//    private String;"isAvailable":1,
//    private String;"snapshotOnoff":0,
//    private String;"snapshotNotificationOnoff":1,
//    private String;"motionDetectOnoff":1,
//    private String;"motionNotificationOnoff":0,
//    private String;"humanDetectOnoff":0,
//    private String;"humanNotificationOnoff":0,
//    private String;"schemeType":1,
//    private String;"schemeStartTime":"2016-10-12 07:10:51",
//    private String;"schemeEndTime":"2016-11-11 07:10:51",
//    private String;"freeSchemeRemainderDay":-1,
//    private String;"paySchemeRemainderDay":8,
//    private String;"startUsingTime":"2015-10-29 06:45:47.0",
//    private String;"alarmOnoff":0,
//    private String;"playTime":"2016-11-02 18:53:28",
//    private String;"productCode":"snap",
//            "alarmList":[
//
//            ],
//            "customizeZoneList":[
//
//    {
//        "id":12003,
//            "securityType":1,
//            "zoneName":"New Zone 1",
//            "securityFlag":4,
//            "roiAreaCoordinate":"3,8,96,83"
//    }
//
//    ],
//            "customer":
//
//    {
//        "id":614,
//            "account":"zlr@sengled.com",
//            "nickname":"alabei看看",
//            "sex":0,
//            "profileFilename":"http://101.68.222.219:9528/null614_profile_1474884154530.jpg",
//            "areaInfo":null
//    }
//
//    ,
//    private String;"essid":"link_SNAP_5G",
//    private String;"bssid":"88:25:93:57:01:6a",
//    private String;"signalLevel":-51,
//    private String;"noiseLevel":-87,
//    private String;"setupNetworkTime":"2016-10-17 10:03:22.0",
//    private String;"deviceId":10616
////    },


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public int getDefinitionLevel() {
        return definitionLevel;
    }

    public void setDefinitionLevel(int definitionLevel) {
        this.definitionLevel = definitionLevel;
    }

    public int getSnapOnoff() {
        return snapOnoff;
    }

    public void setSnapOnoff(int snapOnoff) {
        this.snapOnoff = snapOnoff;
    }

    public int getLampOnoff() {
        return lampOnoff;
    }

    public void setLampOnoff(int lampOnoff) {
        this.lampOnoff = lampOnoff;
    }

    public int getCameraOnoff() {
        return cameraOnoff;
    }

    public void setCameraOnoff(int cameraOnoff) {
        this.cameraOnoff = cameraOnoff;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getVoiceTalkUrl() {
        return voiceTalkUrl;
    }

    public void setVoiceTalkUrl(String voiceTalkUrl) {
        this.voiceTalkUrl = voiceTalkUrl;
    }

    public String getVoiceTalkStatus() {
        return voiceTalkStatus;
    }

    public void setVoiceTalkStatus(String voiceTalkStatus) {
        this.voiceTalkStatus = voiceTalkStatus;
    }

    public String getMicOnoff() {
        return micOnoff;
    }

    public void setMicOnoff(String micOnoff) {
        this.micOnoff = micOnoff;
    }

    public String getMicVolume() {
        return micVolume;
    }

    public void setMicVolume(String micVolume) {
        this.micVolume = micVolume;
    }

    public String getSpeakerOnoff() {
        return speakerOnoff;
    }

    public void setSpeakerOnoff(String speakerOnoff) {
        this.speakerOnoff = speakerOnoff;
    }

    public String getSpeakerVolume() {
        return speakerVolume;
    }

    public void setSpeakerVolume(String speakerVolume) {
        this.speakerVolume = speakerVolume;
    }

    public String getLampAutomaticOnoff() {
        return lampAutomaticOnoff;
    }

    public void setLampAutomaticOnoff(String lampAutomaticOnoff) {
        this.lampAutomaticOnoff = lampAutomaticOnoff;
    }

    public String getLightSensorValue() {
        return lightSensorValue;
    }

    public void setLightSensorValue(String lightSensorValue) {
        this.lightSensorValue = lightSensorValue;
    }

    public String getSetSensorValue() {
        return setSensorValue;
    }

    public void setSetSensorValue(String setSensorValue) {
        this.setSensorValue = setSensorValue;
    }

    public String getRoiOnoff() {
        return roiOnoff;
    }

    public void setRoiOnoff(String roiOnoff) {
        this.roiOnoff = roiOnoff;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezoneCity() {
        return timezoneCity;
    }

    public void setTimezoneCity(String timezoneCity) {
        this.timezoneCity = timezoneCity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getNightVersionOnoff() {
        return nightVersionOnoff;
    }

    public void setNightVersionOnoff(String nightVersionOnoff) {
        this.nightVersionOnoff = nightVersionOnoff;
    }
}
