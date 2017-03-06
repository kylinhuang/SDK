package cn.kylinhuang.play.test;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import cn.kylinhuang.play.R;
import cn.kylinhuang.play.VideoPlayManager;
import cn.kylinhuang.play.view.MediaController;
import cn.kylinhuang.play.view.Utils;


/**
 *
 */
public class AActivity extends Activity implements OnClickListener {

	protected static final String TAG = "Play";
	private Button play;
	private Button pause;
	private Button stop;
	private MediaController playLayout;
	private Button btOtherPlay;

	// add by pang
	private int mOldx; // by use for record last mouse location
	private int mOldy; // by use for record last mouse location
	private float mOldDistanceX; // record last mouse distance
	private float mOldDistanceY;
	private int mScaleX = 0; // scale the width size
	private int mScaleY = 0; // scale the height size

	String mVideoUrl ="rtsps://101.68.222.221:1554/27B529C1DDBE30E984991CE96472BA99.sdp";
//	rtsps://101.68.222.221:1554/D438197D40E7B7E121B1306D98A75FFC.sdp
//
//	playUrl":"rtsps://101.68.222.221:1554/27B529C1DDBE30E984991CE96472BA99.sdp

	public static void actionStart(Context context) {
        Intent intent = new Intent(context, AActivity.class);
        context.startActivity(intent);
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_test);
		initView();
	}

	private void initView() {
		playLayout = (MediaController) findViewById(R.id.play_layout);
		playLayout.setMediaControllerListener(mMediaControllerListener);
		playLayout.setTouchable(true);

		play = (Button) findViewById(R.id.bt_play);
		pause = (Button) findViewById(R.id.bt_pause);
		stop = (Button) findViewById(R.id.bt_stop);

		btOtherPlay = (Button) findViewById(R.id.bt_otherPlay);

		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		stop.setOnClickListener(this);
		btOtherPlay.setOnClickListener(this);
	}



	@Override
	protected void onResume() {
		super.onResume();
		removeView();
		addView();
	}

	@Override
	protected void onPause() {
		super.onPause();
		removeView();
		VideoPlayManager.getInstance().unregisteredCallBcak(mVideoCallback);
		VideoPlayManager.getInstance().stopPlayback();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_pause:
			VideoPlayManager.getInstance().pause();
			Log.e(TAG, "pause");
			break;
		case R.id.bt_play:
			VideoPlayManager.getInstance().play(mVideoUrl);
			Log.e(TAG, "play");
			break;
		case R.id.bt_stop:
			VideoPlayManager.getInstance().stopPlayback();
			Log.e(TAG, "stopPlayback");
			break;
		case R.id.bt_otherPlay:
			OtherActivity.actionStart(AActivity.this);
			break;
		default:
			break;
		}
	}


	private void removeView() {
		View videoView = VideoPlayManager.getInstance().getVideoView();
		if (videoView != null && videoView.getParent() != null && videoView.getParent() instanceof ViewGroup) {
			ViewGroup group = (ViewGroup) videoView.getParent();
			group.removeView(videoView);
		}
	}

	private void addView() {
		View videoView = VideoPlayManager.getInstance().getVideoView();
		if (playLayout.getChildAt(0) != videoView ) {
			FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT);
			playLayout.addView(videoView, 0, lps);
		}
    }

	private VideoPlayManager.IVideoPlayCallBack mVideoCallback = new VideoPlayManager.IVideoPlayCallBack() {
		@Override
		public void onSeeked() {

		}

		@Override
		public void onCompletion() {

		}

		@Override
		public boolean onInfo(int arg0, int arg1) {
			return false;
		}

		@Override
		public boolean onPlayingPositionUpdate(int arg0) {
			return false;
		}

		@Override
		public boolean onError(int what, int extra) {
			return false;
		}

		@Override
		public void onPrepared() {
			playLayout.setTouchable(true);
		}
	};


	private MediaController.MediaControllerListener mMediaControllerListener  = new MediaController.MediaControllerListener() {
		@Override
		public void onGestureBegin() {
			Log.e(TAG,"onGestureBegin");

		}

		@Override
		public void onGestureEnd() {
			Log.e(TAG,"onGestureEnd");

		}

		@Override
		public void onHorizontalSlide(float horizontalSlidePercent) {
			Log.e(TAG," onHorizontalSlide " + horizontalSlidePercent );
		}

		@Override
		public void onVerticalSlide(float verticalSlidePercent) {
			Log.e(TAG," onVerticalSlide " +verticalSlidePercent);
		}

		@Override
		public void onScale(int state, float scaleFactor) {
			Log.e(TAG," onScale " + " state: "+state + " scaleFactor: " + scaleFactor );
		}

		@Override
		public void onScaleComplete(boolean isExpandScaleRange, int lastMultiple, int multiple) {
			Log.e(TAG," onScaleComplete " + " isExpandScaleRange: " + isExpandScaleRange + " lastMultiple: " + lastMultiple + "multiple: " + multiple);


			switch (multiple){
				case 1:
					if(!isExpandScaleRange && lastMultiple == 2 ){
						// 从两倍 缩小到一倍


						//int playH = Utils.dip2px(AActivity.this,R.dimen.playH);
						int playH = 500;

						View palyView = VideoPlayManager.getInstance().getVideoView();
						FrameLayout.LayoutParams palyViewpls = (FrameLayout.LayoutParams) palyView.getLayoutParams();
						palyViewpls.width = FrameLayout.LayoutParams.FILL_PARENT;
						palyViewpls.height = playH;
						palyViewpls.gravity = Gravity.CENTER;
						palyView.setLayoutParams(palyViewpls);

					}
					break;
			}

		}

		@Override
		public void onSingleClick() {
			Log.e(TAG,"onSingleClick");
		}

		@Override
		public void onDoubleClick() {
			Log.e(TAG,"onDoubleClick");
		}

		@Override
		public void onLongClick() {
			Log.e(TAG,"onLongClick");
		}

		@Override
		public void onDragViewWhenMultipleSize(int multiple, float distanceX, float distanceY) {
			Log.e(TAG,"onDragViewWhenMultipleSize " + " multiple: " + multiple + " distanceX: " + distanceX + " distanceY: " + distanceY);



			int screenH = Utils.getScreenHeight(AActivity.this) ;
			int screenW = Utils.getScreenWidth(AActivity.this) ;

//			int playH = Utils.dip2px(AActivity.this,R.dimen.playH);
			int playH = 300;

			int x = screenW / multiple   ;
			int y = screenH / multiple   ;

			int w = screenW * multiple  ;
			int h = screenH * multiple  ;

//			onScaleComplete  isExpandScaleRange: false lastMultiple: 2multiple: 1
			//修改 外层layout
//			RelativeLayout.LayoutParams lps = (RelativeLayout.LayoutParams) playLayout.getLayoutParams();
//			lps.width = screenW * multiple;
//			lps.height = playH * multiple;
//
//			Log.e(TAG,"LayoutParams width" + lps.width + " height " + lps.height );
//			playLayout.setLayoutParams(lps);



			//修改内部 view
			View palyView = VideoPlayManager.getInstance().getVideoView();
			FrameLayout.LayoutParams palyViewpls = (FrameLayout.LayoutParams) palyView.getLayoutParams();
			palyViewpls.width = screenW * multiple;
			palyViewpls.height = playH * multiple;
			palyViewpls.gravity = Gravity.CENTER;
			palyView.setLayoutParams(palyViewpls);




		}
	};
}
