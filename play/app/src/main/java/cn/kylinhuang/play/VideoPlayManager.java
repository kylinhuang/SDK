package cn.kylinhuang.play;

import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;


/**
 * Created by kylinhuang on 8/15/16.
 * 
 * 播放器问题 1 播放完成 需要 开启播放前 需要 playView.stopPlayback(); 再
 * playView.setVideoPath(play); playView.start(); 才能 开始播放 ---- 统一处理 播放完成 统一
 * playView.stopPlayback();
 * 
 * 2 播放 需要先调用 playView.setVideoPath(play); playView.start(); ----- 统一处理 play
 * ==== playView.setVideoPath(play); playView.start();
 * 
 */
public class VideoPlayManager {

	private Map<IVideoPlayCallBack, Integer> mCallbacks = new WeakHashMap<IVideoPlayCallBack, Integer>();

	protected String TAG = "VideoPlayManager";

	private static VideoPlayManager instance;

	private IPlay play = new BPlay();


	private VideoPlayManager() {
		Log.e(TAG,"init");
		play.init();

		Log.e(TAG,"initPlayLitener");
		initPlayLitener();
	}

	public static synchronized VideoPlayManager getInstance() {
		if (instance == null) {
			instance = new VideoPlayManager();
		}
		return instance;
	}


	public  void initPlayLitener() {
		play.setVideoPlayCallBack(mVideoPlayCallBack);
	}



	public View getVideoView() {
		return play.getPlayView();
	}


	public boolean isPlaying() {
		 return play.isPlaying();
	}

	public long getCurrentPosition() {
		return play.getCurrentPosition();
	}


	public void stopPlayback() {
		Log.e(TAG,"stopPlayback");
		play.stopPlayback();
	}

	public void setVideoPath( String url) {
		Log.e(TAG,"setVideoPath : " +url);
		play.setVideoPath(url);
	}

	public void play() {
		Log.e(TAG,"play");
		play.play();
	}


	public long getDuration() {
		return 0 ;
	}

	public void seekTo(long position) {
		Log.e(TAG,"seekTo : " +  position );
		play.seekTo(position);
	}

	public void play(String url) {
		play.setVideoPath(url);
		play.play();
	}
	
	public void pause() {
		Log.e(TAG,"pause");
		play.pause();
	}



	public synchronized void registeredCallBcak(IVideoPlayCallBack callback) {
		mCallbacks.put(callback, 0);
	}

	public synchronized void unregisteredCallBcak(IVideoPlayCallBack callback) {
		mCallbacks.remove(callback);
	}

	/**
	 * @param x
	 * @param y
	 * @param mMediaControllerWidth
	 * @param mMediaControllerHeight
	 * @param size
	 */
	public void setTextureXYAndTimes(int x, int y, int mMediaControllerWidth, int mMediaControllerHeight, int size) {
		Log.e(TAG,"setTextureXYAndTimes " + " X : " + x + " Y : "  + y + " Width : "  + mMediaControllerWidth + " Height : "  + mMediaControllerHeight + " size : "  + size );
		play.setTextureXYAndTimes(x,y,mMediaControllerWidth,mMediaControllerHeight,size);
	}

	public void setAudioVoiceFlag(int i) {
		play.setAudioVoiceFlag(i);
	}


	public interface IVideoPlayCallBack {
		/** 方法在查找操作完成后回调 */
		void onSeeked();

		/** 流媒体 播放完毕后回调的方法 */
		void onCompletion();

		/** 该方法在媒体播放时出现信息或者警告时回调该方法; */
		boolean onInfo(int arg0, int arg1);

		/** 播放位置更新时回调 */
		boolean onPlayingPositionUpdate(int arg0);

		/** 在异步操作中出现错误时会回调该方法, 其它情况下出现错误时直接抛出异常 */
		boolean onError(int what, int extra);

		/** 该方法在进入 Prepared 状态 并 开始播放的时候回调; */
		void onPrepared();
	}

	private synchronized void notifyOnSeeked() {
		Set<IVideoPlayCallBack> keySets = new HashSet<IVideoPlayCallBack>(mCallbacks.keySet());
		for (IVideoPlayCallBack callback : keySets) {
			if (callback != null) {
				callback.onSeeked();
			}
		}
	}
	private synchronized void notifyOnCompletion() {
		Set<IVideoPlayCallBack> keySets = new HashSet<IVideoPlayCallBack>(mCallbacks.keySet());
		for (IVideoPlayCallBack callback : keySets) {
			if (callback != null) {
				callback.onCompletion();
			}
		}
	}
	
	
	private synchronized void notifyOnInfo(int arg0, int arg1) {
		Set<IVideoPlayCallBack> keySets = new HashSet<IVideoPlayCallBack>(mCallbacks.keySet());
		for (IVideoPlayCallBack callback : keySets) {
			if (callback != null) {
				callback.onInfo(arg0, arg1);
			}
		}
	}
	
	private synchronized void notifyOnPlayingPositionUpdate(int arg0) {
		Set<IVideoPlayCallBack> keySets = new HashSet<IVideoPlayCallBack>(mCallbacks.keySet());
		for (IVideoPlayCallBack callback : keySets) {
			if (callback != null) {
				callback.onPlayingPositionUpdate(arg0);
			}
		}
	}
	
	private synchronized void notifyOnError(int what, int extra) {
		Set<IVideoPlayCallBack> keySets = new HashSet<IVideoPlayCallBack>(mCallbacks.keySet());
		for (IVideoPlayCallBack callback : keySets) {
			if (callback != null) {
				callback.onError( what,  extra);
			}
		}
	}
	
	private synchronized void notifyonPrepared() {
		Set<IVideoPlayCallBack> keySets = new HashSet<IVideoPlayCallBack>(mCallbacks.keySet());
		for (IVideoPlayCallBack callback : keySets) {
			if (callback != null) {
				callback.onPrepared();
			}
		}
	}


	private IPlay.IVideoPlayCallBack mVideoPlayCallBack = new IPlay.IVideoPlayCallBack() {
		@Override
		public void onSeeked() {
			notifyOnSeeked();
		}

		@Override
		public void onCompletion() {
			notifyOnCompletion();
		}

		@Override
		public boolean onInfo(int arg0, int arg1) {
			notifyOnInfo(arg0,arg1);
			return false;
		}

		@Override
		public boolean onPlayingPositionUpdate(int arg0) {
			notifyOnPlayingPositionUpdate(arg0);
			return false;
		}

		@Override
		public boolean onError(int what, int extra) {
			notifyOnError(what,extra);
			return false;
		}

		@Override
		public void onPrepared() {
			notifyonPrepared();
		}

		@Override
		public boolean onNetworkStatus(int what, int extra) {
			return false;
		}
	};

	public void setVideoWidthAndHeight(int widthAndHeight,int mMediaControllerHeight) {
		Log.e(TAG,"setVideoWidthAndHeight "  + " Width : "  + widthAndHeight + " Height : "  + mMediaControllerHeight   );

	}

}
