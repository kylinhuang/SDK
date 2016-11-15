package com.kylin.data.entity.RequestEntity;

import com.google.gson.Gson;

/**
 * Created by gaozhongkui on_normal 2016/3/16.
 */
public class GetUserInfoRequestEntity extends BaseRequestEntity {

    @Override
    public String getBody() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String getUrl() {
        return "/camera/customer/getUserInfo.json";
    }

}
