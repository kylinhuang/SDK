package com.kylin.data.entity.RequestEntity;

/**
 * Created by gaozhongkui on_normal 2016/3/16.
 */
public class GetCameraListRequestEntity extends BaseRequestEntity {
    private String mobileUuid ;
    private int playWithSSL ;

    public String getMobileUuid() {
        return mobileUuid;
    }

    public void setMobileUuid(String mobileUuid) {
        this.mobileUuid = mobileUuid;
    }

    public int getPlayWithSSL() {
        return playWithSSL;
    }

    public void setPlayWithSSL(int playWithSSL) {
        this.playWithSSL = playWithSSL;
    }

    @Override
    public String getBody() {
        return null;
    }

    @Override
    public String getUrl() {
        return "";
    }
}
