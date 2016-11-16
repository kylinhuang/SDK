package com.kylin.data;

import android.text.TextUtils;
import android.util.Log;

import com.kylin.MyApplication;
import com.kylin.Utils.PreferencesUtils;
import com.kylin.data.db.IDBManager;
import com.kylin.data.entity.RequestEntity.GetCameraListRequestEntity;
import com.kylin.data.entity.RequestEntity.GetUserInfoRequestEntity;
import com.kylin.data.entity.RequestEntity.LoginRequestEntity;
import com.kylin.data.entity.ResponseEntity.GetCameraListResponseEntity;
import com.kylin.data.entity.ResponseEntity.GetUserInfoResponseEntity;
import com.kylin.data.entity.ResponseEntity.LoginResponseEntity;
import com.kylin.data.http.IHttpManager;
import com.kylin.data.http.okhttp.OKHttpManager;
import com.kylin.data.parser.IParserManager;
import com.kylin.data.parser.gson.GsonParser;


/**
 * Created by kylinhuang on 03/11/2016.
 * 数据模块
 * <p>
 * 给UI层提供数据  通过 IUIGetData
 * <p>
 * 获取数据(http DB)  解析数据 组合数据
 */

public class DataManager {
    private  final String TAG = getClass().getSimpleName() ;
    private String host = "";
    private static DataManager mInstance;
    /**
     * http 请求
     */
    private IHttpManager mHttpManager = new OKHttpManager();
    /**
     * 解析  --- 将数据解析成实体
     */
    private IParserManager mResolveManager = new GsonParser();
    /**
     * 数据库操作  ---
     */
    private IDBManager mDBManager;
    private String cookie = "";
    private String COOKIE = "cookie";

    public DataManager() {
        host = "https://jx1.snap.test.cloud.sengled.com:9000";
//        host = "http://jx1.snap.test.cloud.sengled.com:8000";
    }

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

    public void setHttpStrategy(IParserManager mResolveManager) {
        this.mResolveManager = mResolveManager;
    }

    public void setDBStrategy(IDBManager mDBManager) {
        this.mDBManager = mDBManager;
    }

    public LoginResponseEntity login(LoginRequestEntity entity) {
        String responseMsg = mHttpManager.login(entity);  //网络请求
        Log.e(TAG,"responseMsg " + responseMsg);
        LoginResponseEntity resolveEntity = mResolveManager.parserJson(LoginResponseEntity.class, responseMsg);

        if (null != resolveEntity) {
            mHttpManager.setCookie(entity.getRequestURL(),resolveEntity.getJsessionid());
            setCookie(resolveEntity.getJsessionid());
        }
        return resolveEntity;
    }


    public GetCameraListResponseEntity getCameraList(GetCameraListRequestEntity entity) {
        String responseMsg = mHttpManager.getCameraList(entity);
        Log.e(TAG,"responseMsg " + responseMsg);
        GetCameraListResponseEntity resolveEntity = mResolveManager.parserJson(GetCameraListResponseEntity.class, responseMsg);
        return resolveEntity;
    }

    public GetUserInfoResponseEntity GetUserInfo(GetUserInfoRequestEntity entity) {
        String responseMsg = mHttpManager.getUserInfo(entity);
        Log.e(TAG,"responseMsg " + responseMsg);
        GetUserInfoResponseEntity resolveEntity = mResolveManager.parserJson(GetUserInfoResponseEntity.class, responseMsg);
        return resolveEntity;
    }


    /**
     * 获取网络请求 host
     *
     * @return
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置 获取网络请求 host
     *
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    public String getCookie() {
        if (TextUtils.isEmpty(cookie)) {
            cookie = PreferencesUtils.getString(MyApplication.getApplication().getApplicationContext(), COOKIE, "");
        }
        return cookie;
    }

    public void setCookie(String cookie) {
        if (!TextUtils.isEmpty(cookie)) {
            PreferencesUtils.putString(MyApplication.getApplication(), COOKIE, cookie);
            this.cookie = cookie;
        }
    }
}
