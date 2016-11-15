package com.kylin.data.http;


import com.kylin.data.entity.RequestEntity.BaseRequestEntity;

/**
 * Created by kylinhuang on 03/11/2016.
 * <p>
 * http工具 使用 Http 获取json数据  xml数据
 */
public interface IHttpManager {
    /**
     * 网络请求重试次数
     */
    int REPEATS_TIME = 1;

    /**
     * 建立连接的超时时间
     */
    int connectTimeout = 5 * 1000;
    /**
     * 建立到资源的连接后从 input 流读入时的超时时间
     */
    int readTimeout = 30 * 1000;

    /**
     * login 登陆 网络请求
     *
     * @param requestEntity LoginRequestEntity extends BaseRequestEntity
     * @param <T>
     * @return String  网络 请求返回的数据
     */
    <T extends BaseRequestEntity> String login(T requestEntity);

    <T extends BaseRequestEntity> String getCameraList(T requestEntity);


    <T extends BaseRequestEntity> String getUserInfo(T requestEntity);
}
