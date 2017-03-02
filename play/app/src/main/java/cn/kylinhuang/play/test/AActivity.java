package cn.kylinhuang.play.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

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

	String mVideoUrl ="rtsps://101.68.222.221:1554/D438197D40E7B7E121B1306D98A75FFC.sdp";
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
			FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
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


//			RelativeLayout.LayoutParams lps = (RelativeLayout.LayoutParams) playLayout.getLayoutParams();
//
//				int viewWidth = lps.width;
//				int viewHeight = lps.height;
//
//
//				int viewLeftMargin = mOldx;
//				int viewTopMargin = mOldy;
//
//				int targetLeftMargin;
//				int targetTopMargin;
//				if (distanceX - mOldDistanceX > 100 || distanceX - mOldDistanceX < -100) {
//					mOldDistanceX = 0;
//					targetLeftMargin = (int) (viewLeftMargin + distanceX / 2);
//				} else
//					targetLeftMargin = (int) (viewLeftMargin + (distanceX - mOldDistanceX) / 2);
//				if (distanceY - mOldDistanceY > 100 || distanceY - mOldDistanceY < -100)
//					targetTopMargin = (int) (viewTopMargin + distanceY / 2);
//				else
//					targetTopMargin = (int) (viewTopMargin + (distanceY - mOldDistanceY) / 2);
//
//				mOldDistanceX = distanceX;
//				mOldDistanceY = distanceY;
//
//				int bestLeftMargin = (mScaleX - viewWidth) / 2; // 划出左边界的margin坐标
//				int bestTopMargin = (mScaleY - viewHeight) / 2; // 划出上边界的margin坐标
//				int bestRightMargin = -bestLeftMargin; // 划出右边界的margin坐标
//				int bestBottomMargin = -bestTopMargin; // 划出下边界的margin坐标
//
//				if (targetLeftMargin > bestLeftMargin) {
//					targetLeftMargin = bestLeftMargin;
//				}
//
//				if (targetLeftMargin < bestRightMargin) {
//					targetLeftMargin = bestRightMargin;
//				}
//
//				if (targetTopMargin > bestTopMargin) {
//					targetTopMargin = bestTopMargin;
//				}
//
//				if (targetTopMargin < bestBottomMargin) {
//					targetTopMargin = bestBottomMargin;
//				}
//
//				mOldx = targetLeftMargin;
//				mOldy = targetTopMargin;
//
//				playLayout.setLayoutParams(lps);
//			int x = ((mScaleX - viewWidth) / 2 - targetLeftMargin) / multiple ;
//			int y = ((mScaleX - viewWidth) / 2 - targetLeftMargin) / multiple ;
//			int w = mScaleX ;
//			int h = mScaleY ;


			int screenH = Utils.getScreenHeight(AActivity.this) ;
			int screenW = Utils.getScreenWidth(AActivity.this) ;

			int x = screenW / multiple   ;
			int y = screenH / multiple   ;

			int w = screenW * multiple  ;
			int h = screenH * multiple  ;


				VideoPlayManager.getInstance().setTextureXYAndTimes(x,y,w,h,multiple);

//        mMediaPlayer.setTextureXYAndTimes(0-a,0-a,1280+100,720+100,2);

//				VideoPlayManager.getInstance().getVideoView().setTextureXYAndTimes(
// ((mScaleX - viewWidth) / 2 - targetLeftMargin) / multiple, ((mScaleY - viewHeight) / 2 - targetTopMargin) / multiple,
// mScaleX, mScaleY, multiple);
//				if (!isScreenHorizontal())
//					getVideoView().setTextureXYAndTimes(((mScaleX - viewWidth) / 2 - targetLeftMargin) / multiple, ((mScaleY - viewHeight) / 2 - targetTopMargin) / multiple, mScaleX, mScaleY, multiple);
//				else
//					getVideoView().setTextureXYAndTimes(((mScaleX - viewWidth) / 2 - targetLeftMargin) / multiple, ((mScaleY - viewHeight) / 2 - targetTopMargin) / multiple, mScaleX, mScaleY, multiple);


		}
	};
}
