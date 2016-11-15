package com.kylin.data.http;


import com.kylin.data.entity.RequestEntity.BaseRequestEntity;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by kylinhuang on 03/11/2016.
 */

public class OKHttpManager implements IHttpManager {

    private OkHttpClient okHttpClient;
    private Callback callback;

    public OKHttpManager() {
        okHttpClient = new OkHttpClient();
    }

    @Override
    public <T extends BaseRequestEntity> String login(T requestEntity) {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", "wangwu")
                .add("password", "hello12345")
                .add("gender", "female")
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.1.170:8088/okhttp/test_simple_post.php")
                .post(requestBody)
                .addHeader("token", "helloworldhelloworldhelloworld")
                .build();
        okHttpClient.newCall(request).enqueue(callback);
        return null;
    }

    @Override
    public <T extends BaseRequestEntity> String getCameraList(T requestEntity) {
        return null;
    }
}
