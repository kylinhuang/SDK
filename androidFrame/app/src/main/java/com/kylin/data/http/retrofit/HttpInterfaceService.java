package com.kylin.data.http.retrofit;


import com.kylin.data.entity.RequestEntity.GetCameraListRequestEntity;
import com.kylin.data.entity.RequestEntity.LoginRequestEntity;
import com.kylin.data.entity.ResponseEntity.GetCameraListResponseEntity;
import com.kylin.data.entity.ResponseEntity.LoginResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

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
