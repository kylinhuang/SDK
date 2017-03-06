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

import cn.kylinhuang.play.R;
import cn.kylinhuang.play.VideoPlayManager;


public class OtherActivity extends Activity implements OnClickListener {

	protected static final String TAG = "Play";
	private Button play;
	private Button pause;
	private Button stop;
	private FrameLayout playLayout;
	String mVideoUrl ="rtsps://101.68.222.221:1554/7136C7D76F5EE09DD1DA564D02EA56F9.sdp";



	public static void actionStart(Context context) {
        Intent intent = new Intent(context, OtherActivity.class);
        context.startActivity(intent);
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_other);
		initView();
	}

	private void initView() {
		playLayout = (FrameLayout) findViewById(R.id.play_layout);
		play = (Button) findViewById(R.id.bt_play);
		pause = (Button) findViewById(R.id.bt_pause);
		stop = (Button) findViewById(R.id.bt_stop);


		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		stop.setOnClickListener(this);
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

		}
	};
}
