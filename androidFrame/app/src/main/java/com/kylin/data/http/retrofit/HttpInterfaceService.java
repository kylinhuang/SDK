package com.kylin.data.http.retrofit;


import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import test.test.retrofit.RequestEntity.GetCameraListRequestEntity;
import test.test.retrofit.RequestEntity.LoginRequestEntity;
import test.test.retrofit.ResponseEntity.GetCameraListResponseEntity;
import test.test.retrofit.ResponseEntity.LoginResponseEntity;

/**
 * Created by gaozhongkui on_normal 2016/3/15.
 */
public interface HttpInterfaceService {

    @POST("/camera/v1/customer/login.json")
    Observable<LoginResponseEntity> login(@Body LoginRequestEntity loginBean);


    //https://jx1.snap.test.cloud.sengled.com:9000/camera/ipc/getCameraList.json
    @POST("/camera/ipc/getCameraList.json")
    Observable<GetCameraListResponseEntity> login(@Body GetCameraListRequestEntity loginBean);

}
