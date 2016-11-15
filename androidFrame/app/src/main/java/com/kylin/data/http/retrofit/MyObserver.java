package com.kylin.data.http.retrofit;

import rx.Observer;

/**
 * Created by gaozhongkui on_normal 2016/3/15.
 */
public abstract class MyObserver<T> implements Observer<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }


}
