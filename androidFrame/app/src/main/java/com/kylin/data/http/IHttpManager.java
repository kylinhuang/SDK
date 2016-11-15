package com.kylin.data.http;


import com.kylin.data.entity.RequestEntity.BaseRequestEntity;

/**
 * Created by kylinhuang on 03/11/2016.
 *
 * http工具 使用 Http 获取json数据  xml数据
 */
public interface IHttpManager {
    /**
     * login 登陆 网络请求
     * @param requestEntity LoginRequestEntity extends BaseRequestEntity
     * @param <T>
     * @return String  网络 请求返回的数据
     */
    <T extends BaseRequestEntity> String login(T requestEntity);

    <T extends BaseRequestEntity> String getCameraList(T requestEntity);
}
