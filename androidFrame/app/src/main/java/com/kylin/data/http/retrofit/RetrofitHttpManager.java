package com.kylin.data.http.retrofit;


import com.google.gson.JsonObject;
import com.kylin.data.entity.RequestEntity.BaseRequestEntity;
import com.kylin.data.entity.RequestEntity.GetCameraListRequestEntity;
import com.kylin.data.entity.RequestEntity.GetUserInfoRequestEntity;
import com.kylin.data.entity.RequestEntity.LoginRequestEntity;
import com.kylin.data.entity.ResponseEntity.GetCameraListResponseEntity;
import com.kylin.data.entity.ResponseEntity.LoginResponseEntity;
import com.kylin.data.http.IHttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gaozhongkui on_normal 2016/3/15.
 *
 * Retrofit 框架特殊性 可以直接返回实体 （包括 http 获取数据  数据解析实体 ）
 *
 * 使用确实 很方便  但是 对于特使情况 如网络加密传输
 *
 * 对于加密 解密 的方式 以后可能要添加 所有 实现两套方案
 *  1 转jsonString -----------  public <T extends BaseRequestEntity> String login(T requestEntity)
 *  2 转实体  ----------------- public Observable<LoginResponseEntity> login(LoginRequestEntity loginBean)
 *
 * 加密  AES+RSA 结合方式
 *
 */
public final class RetrofitHttpManager implements IHttpManager {

    public static String BASE_URL_PATH = "https://jx1.snap.test.cloud.sengled.com:9000";
    private HttpInterfaceService mHttpInterfaceService;

    public RetrofitHttpManager() {
        mHttpInterfaceService = RetrofitService.createHttpService(HttpInterfaceService.class, BASE_URL_PATH);
    }

    /**
     * @param loginBean
     * @return use
     * <p>
     * Observable<LoginResponseEntity> mObservable =  RetrofitHttpManager.login(loginRequestEntity);
     * mObservable.subscribe(new Subscriber<LoginResponseEntity>() {
     * @Override public void onCompleted() {
     * <p>
     * }
     * @Override public void onError(Throwable e) {
     * <p>
     * }
     * @Override public void onNext(LoginResponseEntity loginResponseEntity) {
     * <p>
     * }
     * });
     */
    public Observable<LoginResponseEntity> login(LoginRequestEntity loginBean) {
        Observable<LoginResponseEntity> mRespLogin = mHttpInterfaceService.login(loginBean);
        return mRespLogin.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<GetCameraListResponseEntity> getCameraList(GetCameraListRequestEntity entity) {
        Observable<GetCameraListResponseEntity> response = mHttpInterfaceService.getCameraList(entity);
        return response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public <T extends BaseRequestEntity> String login(T requestEntity) {
        if (requestEntity instanceof LoginRequestEntity) {
            LoginRequestEntity loginRequestEntity = (LoginRequestEntity) requestEntity;
            Call<JsonObject> call = mHttpInterfaceService.login_Json(loginRequestEntity);
            try {
                Response repo = call.execute();
                if (repo.isSuccessful()) {
                    String body = repo.body().toString();
                    return body;
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public <T extends BaseRequestEntity> String getCameraList(T requestEntity) {
        return null;
    }

    @Override
    public <T extends BaseRequestEntity> String getUserInfo(T requestEntity) {
        if (requestEntity instanceof GetUserInfoRequestEntity) {
            GetUserInfoRequestEntity getUserInfoRequestEntity = (GetUserInfoRequestEntity) requestEntity;
            Call<JsonObject> call = mHttpInterfaceService.getUserInfo_Json(getUserInfoRequestEntity);
            try {
                Response repo = call.execute();
                if (repo.isSuccessful()) {
                    String body = repo.body().toString();
                    return body;
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public void setCookie(String Url, String cookie) {

    }
}
