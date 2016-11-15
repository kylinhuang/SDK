package com.kylin.data.entity.ResponseEntity;

import test.test.Utils.HttpRequestCode;

/**
 * Created by gaozhongkui on_normal 2016/3/16.
 */
public class LoginResponseEntity extends BaseResponseEntity {
//    {
//        "messageCode": "200",
//            "info": "OK",
//            "description": "正常",
//            "jsessionid": "1178C5633B3506623FFD979A630E3435",
//            "nick_name": "alabei看看",
//            "profile_path": null,
//            "appServerAddr": null,
//            "ucenterAddr": null,
//            "snapServiceMinVerison": 0,
//            "ifCheckSessionid": 0
//    }

    private int messageCode;
    private String info;
    private String description;
    private String jsessionid;
    private String nick_name;
    private String profile_path;
    private String appServerAddr;
    private String ucenterAddr;
    private String snapServiceMinVerison;
    private String ifCheckSessionid;

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getAppServerAddr() {
        return appServerAddr;
    }

    public void setAppServerAddr(String appServerAddr) {
        this.appServerAddr = appServerAddr;
    }

    public String getUcenterAddr() {
        return ucenterAddr;
    }

    public void setUcenterAddr(String ucenterAddr) {
        this.ucenterAddr = ucenterAddr;
    }

    public String getSnapServiceMinVerison() {
        return snapServiceMinVerison;
    }

    public void setSnapServiceMinVerison(String snapServiceMinVerison) {
        this.snapServiceMinVerison = snapServiceMinVerison;
    }

    public String getIfCheckSessionid() {
        return ifCheckSessionid;
    }

    public void setIfCheckSessionid(String ifCheckSessionid) {
        this.ifCheckSessionid = ifCheckSessionid;
    }

    public boolean isRequestSuccess() {
        if (messageCode == HttpRequestCode.OK) return true;
        return false;
    }
}
