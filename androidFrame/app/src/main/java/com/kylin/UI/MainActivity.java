package com.kylin.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kylin.data.DataManager;
import com.kylin.data.R;
import com.kylin.data.entity.RequestEntity.GetCameraListRequestEntity;
import com.kylin.data.entity.RequestEntity.GetUserInfoRequestEntity;
import com.kylin.data.entity.RequestEntity.LoginRequestEntity;
import com.kylin.data.entity.ResponseEntity.GetCameraListResponseEntity;
import com.kylin.data.entity.ResponseEntity.GetUserInfoResponseEntity;
import com.kylin.data.entity.ResponseEntity.LoginResponseEntity;
import com.kylin.data.http.HttpURLConnection.CustomHttpURLConnection;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResponse;
    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_getCameraList).setOnClickListener(this);
        findViewById(R.id.bt_getUserInfo).setOnClickListener(this);


        tvResponse = (TextView)findViewById(R.id.tv_response);

        mDataManager = DataManager.getInstance();
        mDataManager.setHttpStrategy(new CustomHttpURLConnection());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
                Observable.create(new Observable.OnSubscribe<LoginResponseEntity>() {
                    @Override
                    public void call(Subscriber<? super LoginResponseEntity> subscriber) {
                        LoginRequestEntity loninEntity =  new LoginRequestEntity();
                        loninEntity.setUser("zlr@sengled.com");
                        loninEntity.setOsType("android");
                        loninEntity.setPwd("654321");
                        loninEntity.setUuid("65ea2b7f283d21ce");
                        LoginResponseEntity entity = mDataManager.login(loninEntity);
                        subscriber.onNext(entity);
                    }
                }).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<LoginResponseEntity>() {

                            @Override
                            public void onCompleted() { }

                            @Override
                            public void onError(Throwable e) {
                                e.getStackTrace();
                                tvResponse.setText(e.toString());
                            }

                            @Override
                            public void onNext(LoginResponseEntity entity) {
                                tvResponse.setText(entity.toString());
                            }
                        });

                break;
            case R.id.bt_getCameraList:
                Observable.create(new Observable.OnSubscribe<GetCameraListResponseEntity>() {
                    @Override
                    public void call(Subscriber<? super GetCameraListResponseEntity> subscriber) {
                        GetCameraListRequestEntity getCameraListRequestEntity =  new GetCameraListRequestEntity();
//                        getCameraListRequestEntity.setUser("zlr@sengled.com");
//                        getCameraListRequestEntity.setOsType("android");
//                        getCameraListRequestEntity.setPwd("654321");
//                        getCameraListRequestEntity.setUuid("65ea2b7f283d21ce");
                        GetCameraListResponseEntity entity = mDataManager.getCameraList(getCameraListRequestEntity);
                        subscriber.onNext(entity);
                    }
                }).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<GetCameraListResponseEntity>() {
                            @Override
                            public void onCompleted() { }

                            @Override
                            public void onError(Throwable e) {
                                tvResponse.setText(e.toString());
                            }

                            @Override
                            public void onNext(GetCameraListResponseEntity entity) {
                                tvResponse.setText(entity.toString());
                            }
                        });
                break;
            case R.id.bt_getUserInfo:
                Observable.create(new Observable.OnSubscribe<GetUserInfoResponseEntity>() {
                    @Override
                    public void call(Subscriber<? super GetUserInfoResponseEntity> subscriber) {
                        GetUserInfoRequestEntity loninEntity =  new GetUserInfoRequestEntity();
                        GetUserInfoResponseEntity entity = mDataManager.GetUserInfo(loninEntity);
                        subscriber.onNext(entity);
                    }
                }).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<GetUserInfoResponseEntity>() {

                            @Override
                            public void onCompleted() { }

                            @Override
                            public void onError(Throwable e) {
                                tvResponse.setText(e.toString());
                            }

                            @Override
                            public void onNext(GetUserInfoResponseEntity entity) {
                                tvResponse.setText(entity.toString());
                            }
                        });

                break;
        }

    }
}
