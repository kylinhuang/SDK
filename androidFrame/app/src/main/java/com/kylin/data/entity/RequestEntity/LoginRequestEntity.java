package com.kylin.data.entity.RequestEntity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gaozhongkui on_normal 2016/3/16.
 */
public class LoginRequestEntity extends BaseRequestEntity {


    private String uuid = "xxxxxx";
    @SerializedName("os_type") //属性重命名 @SerializedName 注解的使用
    private String osType = "android";
    private String user;
    private String pwd;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    @Override
    public String getBody() {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(this);
        return jsonObject ;
    }

    @Override
    public String getUrl() {
        return "/camera/v1/customer/login.json";
    }

}
