package com.kylin.tencentlivesdk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.tencent.av.sdk.AVContext;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 打印SDK的版本信息
        Log.i("HelloSDK", "SDK Version = " + AVContext.getVersion());
    }
}
