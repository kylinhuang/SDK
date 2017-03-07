package cn.kylinhuang.play.test;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.kylinhuang.play.R;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_testLivePlay).setOnClickListener(this);
        findViewById(R.id.bt_testTounch).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_testLivePlay:
                //TODO 播放器器bug   当 addView 自动开启播放
                AActivity.actionStart(MainActivity.this);
                break;
            case R.id.bt_testTounch:
                TounchActivity.actionStart(MainActivity.this);
                break;

        }
    }
}
