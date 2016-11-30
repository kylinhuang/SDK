package com.kylin.UI;


import com.kylin.data.entity.ResponseEntity.BaseResponseEntity;

import rx.Observer;

/**
 * Created by gaozhongkui on_normal 2016/3/15.
 */
public class UIObserver<T extends BaseResponseEntity> implements Observer<T> {

    private final UIGetDataCallBack mUIGetDataCallBack;

    public UIObserver(UIGetDataCallBack mUIGetDataCallBack) {
        this.mUIGetDataCallBack = mUIGetDataCallBack;
    }

    @Override
    public void onNext(T t) {
        if (t instanceof BaseResponseEntity) {
            BaseResponseEntity entity = (BaseResponseEntity) t;
            if (null != mUIGetDataCallBack) mUIGetDataCallBack.getDataFinish(entity.isSuccess(), entity);
        } else {
            if (null != mUIGetDataCallBack) mUIGetDataCallBack.getDataFinish(false, null);
        }
    }

    @Override
    public void onCompleted() { }

    @Override
    public void onError(Throwable e) {
        if (null != mUIGetDataCallBack) mUIGetDataCallBack.getDataFinish(false, null);
        e.printStackTrace();
    }

}
