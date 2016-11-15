package com.kylin.data.http.HttpURLConnection;

import com.kylin.data.entity.RequestEntity.BaseRequestEntity;
import com.kylin.data.http.IHttpManager;

/**
 * Created by kylinhuang on 14/11/2016.
 */

public class CustomHttpURLConnection implements IHttpManager {

    private final HttpHelper mHttpHelper;

    public CustomHttpURLConnection() {
       mHttpHelper = HttpHelper.getInstance();
    }

    @Override
    public <T extends BaseRequestEntity> String login(T requestEntity) {
        if (requestEntity instanceof BaseRequestEntity){

        }
        return null;
    }

    @Override
    public <T extends BaseRequestEntity> String getCameraList(T requestEntity) {
        return null;
    }
}
