package cn.kylinhuang.play.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import cn.kylinhuang.play.R;
import cn.kylinhuang.play.VideoPlayManager;
import cn.kylinhuang.play.view.MediaController;
import cn.kylinhuang.play.view.Utils;


/**
 *
 */
public class TounchActivity extends Activity  {

	protected static final String TAG = "Play";
	private MediaController playLayout;

	private ImageView mImg;
	private ScaleGestureDetector mScaleGestureDetector;


	public static void actionStart(Context context) {
        Intent intent = new Intent(context, TounchActivity.class);
        context.startActivity(intent);
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tounch);
		initView();
	}

	private void initView() {

		mScaleGestureDetector = new ScaleGestureDetector(this,mScaleListener);

		mImg = (ImageView) findViewById(R.id.img);
	}



	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mScaleGestureDetector.onTouchEvent(event);
		return true;
//		return super.onTouchEvent(event);
	}


	private android.view.ScaleGestureDetector.OnScaleGestureListener mScaleListener = new ScaleGestureDetector.OnScaleGestureListener() {
		@Override
		public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
			float scaleFactor = scaleGestureDetector.getScaleFactor();
			Log.e(TAG,"onScale: "+ scaleFactor);
			//修改内部 view
			FrameLayout.LayoutParams palyViewpls = (FrameLayout.LayoutParams) mImg.getLayoutParams();
			palyViewpls.width = (int) (palyViewpls.width * scaleFactor);
			palyViewpls.height = (int) (palyViewpls.height * scaleFactor);
			palyViewpls.gravity = Gravity.CENTER;
			mImg.setLayoutParams(palyViewpls);
			return false;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
			Log.e(TAG,"onScaleBegin: "+ scaleGestureDetector.toString());


			return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
			Log.e(TAG,"onScaleEnd: "+ scaleGestureDetector.toString());
		}
	};
}
