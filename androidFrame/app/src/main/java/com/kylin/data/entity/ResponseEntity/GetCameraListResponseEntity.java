package com.kylin.data.entity.ResponseEntity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by gaozhongkui on_normal 2016/3/16.
 */
public class GetCameraListResponseEntity extends BaseResponseEntity {

    private int messageCode;
    private String info;
    private String description;
    @SerializedName("deviceList")
    protected List<DevicesEntity> deviceList; //


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


    public boolean isRequestSuccess() {
//        if (messageCode == HttpRequestCode.OK) return true;
        return false;
    }



}
