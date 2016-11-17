package com.kylin.data.http.retrofit;


import com.google.gson.JsonObject;
import com.kylin.data.entity.RequestEntity.GetCameraListRequestEntity;
import com.kylin.data.entity.RequestEntity.GetUserInfoRequestEntity;
import com.kylin.data.entity.RequestEntity.LoginRequestEntity;
import com.kylin.data.entity.ResponseEntity.GetCameraListResponseEntity;
import com.kylin.data.entity.ResponseEntity.GetUserInfoResponseEntity;
import com.kylin.data.entity.ResponseEntity.LoginResponseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by gaozhongkui on_normal 2016/3/15.
 */
public interface HttpInterfaceService {

    /** ---------  login            --------    3.1：客户登录接口  */
    @POST("/camera/v1/customer/login.json")
    Observable<LoginResponseEntity> login(@Body LoginRequestEntity loginBean);
    @POST("/camera/v1/customer/login.json") // 踩坑点 不解析json  JsonObject 为gosn JsonObject
    Call<JsonObject> login_Json(@Body LoginRequestEntity loginBean);


    /** --------  getCameraList     --------   */
    //https://jx1.snap.test.cloud.sengled.com:9000/camera/ipc/getCameraList.json
    @POST("/camera/ipc/getCameraList.json")
    Observable<GetCameraListResponseEntity> getCameraList(@Body GetCameraListRequestEntity entity);
    @POST("/camera/ipc/getCameraList.json")// 踩坑点 不解析json  JsonObject 为gosn JsonObject
    Call<JsonObject> getCameraList_Json(@Body GetCameraListRequestEntity entity);

    /** --------  getUserInfo       --------    3.4：查询客户基本信息接口  */
    //https://snap.cloud.sengled.com:9000/camera/customer/getUserInfo.json
    @POST("/camera/customer/getUserInfo.json")
    Observable<GetUserInfoResponseEntity> getUserInfo(@Body GetUserInfoRequestEntity entity);
    @POST("/camera/customer/getUserInfo.json") // 踩坑点 不解析json  JsonObject 为gosn JsonObject
    Call<JsonObject> getUserInfo_Json(@Body GetUserInfoRequestEntity entity);


}
