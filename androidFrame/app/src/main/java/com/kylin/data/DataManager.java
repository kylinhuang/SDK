package com.kylin.data;

import com.kylin.data.db.IDBManager;
import com.kylin.data.entity.RequestEntity.GetCameraListRequestEntity;
import com.kylin.data.entity.RequestEntity.LoginRequestEntity;
import com.kylin.data.entity.ResponseEntity.GetCameraListResponseEntity;
import com.kylin.data.entity.ResponseEntity.LoginResponseEntity;
import com.kylin.data.http.IHttpManager;
import com.kylin.data.http.OKHttpManager;


/**
 * Created by kylinhuang on 03/11/2016.
 * 数据模块
 * <p>
 * 给UI层提供数据  通过 IUIGetData
 * <p>
 * 获取数据(http DB)  解析数据 组合数据
 */

public class DataManager {

    private static DataManager mInstance;
    /**
     * http 请求
     */
    private IHttpManager mHttpManager = new OKHttpManager();
    /**
     * 解析  --- 将数据解析成实体
     */
    private IResolveManager mResolveManager;
    /**
     * 数据库操作  ---
     */
    private IDBManager mDBManager;

    public DataManager() { }

    public static DataManager getInstance() {
        if (mInstance == null) mInstance = new DataManager();
        return mInstance;
    }

    /**
     * Http 网络请求 策略
     * 使用什么请求 框架
     * OKhttp
     * 系统HttpURLConnection
     * XUtil等
     */
    public void setHttpStrategy(IHttpManager mHttpManager) {
        this.mHttpManager = mHttpManager;
    }

    public void setHttpStrategy(IResolveManager mResolveManager) {
        this.mResolveManager = mResolveManager;
    }

    public void setDBStrategy(IDBManager mDBManager) {
        this.mDBManager = mDBManager;
    }

    public void login(LoginRequestEntity entity) {
        String responseMsg = mHttpManager.login(entity);
        LoginResponseEntity resolveEntity =  mResolveManager.resolveJson(LoginResponseEntity.class, responseMsg);
    }


    public void getCameraList(GetCameraListRequestEntity entity) {
        String responseMsg = mHttpManager.getCameraList(entity);
        LoginResponseEntity resolveEntity =  mResolveManager.resolveJson(GetCameraListResponseEntity.class, responseMsg);
    }


}
