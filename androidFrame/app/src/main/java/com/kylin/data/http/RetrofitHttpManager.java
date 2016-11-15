package com.kylin.data.http;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import test.test.retrofit.HttpInterfaceService;
import test.test.retrofit.RequestEntity.BaseRequestEntity;
import test.test.retrofit.RequestEntity.GetCameraListRequestEntity;
import test.test.retrofit.RequestEntity.LoginRequestEntity;
import test.test.retrofit.ResponseEntity.GetCameraListResponseEntity;
import test.test.retrofit.ResponseEntity.LoginResponseEntity;
import test.test.retrofit.RetrofitService;

/**
 * Created by gaozhongkui on_normal 2016/3/15.
 */
public final class RetrofitHttpManager implements IHttpManager {

    public static String BASE_URL_PATH = "https://jx1.snap.test.cloud.sengled.com:9000";
    private static RetrofitHttpManager mInstance;
    private HttpInterfaceService mHttpInterfaceService;

    public static RetrofitHttpManager getInstance() {
        if (mInstance == null ) {
            synchronized (RetrofitHttpManager.class) {
                mInstance = new RetrofitHttpManager();
            }
        }
        return mInstance;
    }

    private RetrofitHttpManager() {
        mHttpInterfaceService = RetrofitService.createHttpService(HttpInterfaceService.class, BASE_URL_PATH);
    }


    public Observable<LoginResponseEntity> login(LoginRequestEntity loginBean) {
        Observable<LoginResponseEntity> mRespLogin = mHttpInterfaceService.login(loginBean);
        return mRespLogin.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<GetCameraListResponseEntity> getCameraList(GetCameraListRequestEntity entity) {
        Observable<GetCameraListResponseEntity> response = mHttpInterfaceService.login(entity);
        return response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T extends BaseRequestEntity> String login(T requestEntity) {
        return null;
    }

    @Override
    public <T extends BaseRequestEntity> String getCameraList(T requestEntity) {
        return null;
    }
}
