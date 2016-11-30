package com.kylin.UI;


import com.kylin.data.entity.ResponseEntity.BaseResponseEntity;

/**
 * Created by kylinhuang on 18/11/2016.
 */

public interface UIGetDataCallBack <T extends BaseResponseEntity> {

    void getDataFinish(boolean isSuccess, T t);
}
