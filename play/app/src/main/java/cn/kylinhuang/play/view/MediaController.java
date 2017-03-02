package cn.kylinhuang.play.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MediaController extends FrameLayout implements CommonGestures.TouchListener {
	private static final int GESTURES_FLAG_VIDEO_PROGRESS		= 1;
	private static final int GESTURES_FLAG_VOLUME_PROGRESS		= 2;
	private static final int GESTURES_FLAG_VIDEO_BEGIN_SCALE	= 3;
	private static final int GESTURES_FLAG_VIDEO_SCALING		= 4;
	private static final int GESTURES_FLAG_VIDEO_END_SCALE		= 5;
	private static final int GESTURES_FLAG_IDLE					= 6;
	private static final int GESTURES_FLAG_DRAG_VIEW			= 7;
	
	private static final int MULTIPLE_FLAG_EXPAND				= 8;
	private static final int MULTIPLE_FLAG_SHRINK				= 9;
	private static final int MULTIPLE_FLAG_DEFAULT				= 10;


//	private ViewGroup mVideoProgressLayout, mVolumeLayout;
//	private ImageView mImgVideoProgress;
//	private TextView mTxtVideoProgress, mTxtVol;
//	private ProgressBar mProgressBarVol;
	
	private CommonGestures	mGestures;
	private AudioManager mAudioManager;
	
	private boolean			mIsTouchable = false;
	private boolean			mIsDefaultControllVerticalSlide = false;
	private boolean			mIsDefaultControllHorizontalSlide = false;
	
	private int				mGesturesFlag = GESTURES_FLAG_IDLE;
	private int				mMaxVolume;
	private int				mCurrentVolume;
	private int				mMultipleRefreshFlag = MULTIPLE_FLAG_DEFAULT;
	private int				mMultiple = 1;
	private int				mLastMultiple = -1;
	private float			mBeginSpan = -1f;
	private MediaControllerListener	mListener;
	
	
	@Override
	protected void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		refreshVideoSizeMultiple(MULTIPLE_FLAG_DEFAULT);
	}
	
	//===================Public Method===============================
	
	/**
	 * 设置多媒体控制器回调
	 * @param l
	 */
	public void setMediaControllerListener(MediaControllerListener l) {
		this.mListener = l;
	}
	
	/**
	 * 设置是否可触摸
	 * @param isTouchable
	 */
	public void setTouchable(boolean isTouchable){
		this.mIsTouchable = isTouchable;
	}
	
	public boolean isTouchable(){
		return mIsTouchable;
	}
	
	public boolean isDefaultControllVerticalSlide() {
		return mIsDefaultControllVerticalSlide;
	}

	/**
	 * 设置多媒体控制器是否自动处理竖向滑动
	 * @param useDefaultControll
	 */
	public void setDefaultControllVerticalSlide(boolean useDefaultControll) {
		this.mIsDefaultControllVerticalSlide = useDefaultControll;
	}

	public boolean isDefaultControllHorizontalSlide() {
		return mIsDefaultControllHorizontalSlide;
	}

	/**
	 * 设置多媒体控制器是否自动处理横向滑动
	 * @param useDefaultControll
	 */
	public void setDefaultControllHorizontalSlide(boolean useDefaultControll) {
		this.mIsDefaultControllHorizontalSlide = useDefaultControll;
	}
	
	
	
	//===================Public Method===============================
	


	public MediaController(Context context) {
		super(context);
		init();
	}
	
	public MediaController(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MediaController(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init(){
//		LayoutInflater.from(getContext()).inflate(R.layout.media_controller, this, true);
//		mVideoProgressLayout = (ViewGroup) findViewById(R.id.MediaController_videoProgressLayout);
//		mVolumeLayout = (ViewGroup) findViewById(R.id.MediaController_volumeLayout);
//		mImgVideoProgress = (ImageView) findViewById(R.id.MediaController_videoProgressLayout_img);
//		mTxtVideoProgress = (TextView) findViewById(R.id.MediaController_videoProgressLayout_txt);
//		mProgressBarVol = (ProgressBar) findViewById(R.id.MediaController_volumeLayout_progressBar);
//		mTxtVol = (TextView) findViewById(R.id.MediaController_volumeLayout_txt);
		
		mGestures = new CommonGestures((Activity) getContext());
		mGestures.setTouchListener(MediaController.this, true);
		mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
		mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		refreshVideoSizeMultiple(MULTIPLE_FLAG_DEFAULT);
		onGestureEnd();
	}
	
//	public void setTxtWhenForwardBackward(String text){
//		mTxtVideoProgress.setText(text);
//	}
	
//	/**
//	 * 设置音量
//	 * @param volumn
//	 */
//	private void setVolume(int volumn){
//		if(volumn > mMaxVolume){
//			volumn = mMaxVolume;
//		}else if(volumn < 0){
//			volumn = 0;
//		}
//
//		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumn, 0);
//		int percent = (int)((double)volumn / mMaxVolume * 100);
//		mProgressBarVol.setProgress(percent);
//		mTxtVol.setText("VOL: " + percent + "%");
//	}
	
	@Override
	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouchEvent(MotionEvent event) {
		if(!mIsTouchable){
			return false;
		}
		return mGestures.onTouchEvent(event);
	}

	@Override
	public void onGestureBegin() {
		mCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		if(mCurrentVolume < 0){
			mCurrentVolume = 0;
		}
		if(null != mListener){
			mListener.onGestureBegin();
		}
	}

	@Override
	public void onGestureEnd() {
//		mVolumeLayout.setVisibility(View.GONE);
//		mVideoProgressLayout.setVisibility(View.GONE);
		mGesturesFlag = GESTURES_FLAG_IDLE;
		if(null != mListener){
			mListener.onGestureEnd();
		}
	}

	@Override
	public void onSingleTap() {
		if(null != mListener){
			mListener.onSingleClick();
		}
	}

	@Override
	public void onDoubleTap() {
		if(null != mListener){
			mListener.onDoubleClick();
		}
	}

	@Override
	public void onScale(float scaleFactor, int state, ScaleGestureDetector detector) {
		switch (state) {
		case CommonGestures.SCALE_STATE_BEGIN:
			mGesturesFlag = GESTURES_FLAG_VIDEO_BEGIN_SCALE;
			mBeginSpan = detector.getCurrentSpan();
			if(null != mListener){
				mListener.onScale(mGesturesFlag, scaleFactor);
			}
			break;
			
		case CommonGestures.SCALE_STATE_SCALEING:
			mGesturesFlag = GESTURES_FLAG_VIDEO_SCALING;
			if(null != mListener){
				mListener.onScale(mGesturesFlag, scaleFactor);
			}
			break;
			
		case CommonGestures.SCALE_STATE_END:
			mGesturesFlag = GESTURES_FLAG_VIDEO_END_SCALE;
			float endSpan = detector.getCurrentSpan();
			boolean isExpandScaleRange = endSpan > mBeginSpan;
			if(null != mListener){
				mListener.onScale(mGesturesFlag, scaleFactor);
				int flag = isExpandScaleRange ? MULTIPLE_FLAG_EXPAND : MULTIPLE_FLAG_SHRINK;
				boolean isOprated = refreshVideoSizeMultiple(flag);
				if(isOprated){
					mListener.onScaleComplete(isExpandScaleRange, mLastMultiple, mMultiple);
				}
			}
			mGesturesFlag = GESTURES_FLAG_IDLE;
			mBeginSpan = -1f;
			break;

		default:
			break;
		}
	}
	
	//重置视频尺寸倍数
	public void resetVideoSize(boolean isCallListener){
		refreshVideoSizeMultiple(MULTIPLE_FLAG_DEFAULT);
		if(null != mListener){
			mListener.onScaleComplete(false, mLastMultiple, mMultiple);
		}
	}
	
	//刷新右上角视频尺寸倍数
	private boolean refreshVideoSizeMultiple(int flag){
		boolean returnValue = false;
		
		mMultipleRefreshFlag = flag;
		if(mMultipleRefreshFlag == MULTIPLE_FLAG_EXPAND){
			//扩大
			if(mMultiple >= 1 && mMultiple <= 4){
				switch (mMultiple) {
				case 1:
				case 2:
					mLastMultiple = mMultiple;
					mMultiple = mMultiple * 2;
					returnValue = true;
					break;

				default:
					Toast.makeText(getContext(), "Video can only be zoomed in 4 plus", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		} else if(mMultipleRefreshFlag == MULTIPLE_FLAG_SHRINK){
			//缩小
			if(mMultiple >= 2 && mMultiple <= 4){
				switch (mMultiple) {
				case 4:
				case 2:
					mLastMultiple = mMultiple;
					mMultiple = mMultiple / 2;
					returnValue = true;
					break;

				default:
					Toast.makeText(getContext(), "Video can only be zoomed out 1 plus", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		} else if(mMultipleRefreshFlag == MULTIPLE_FLAG_DEFAULT){
			//默认1倍
			if(mMultiple != 1){
				mMultiple = 1;
			}
			
			if(mLastMultiple != -1){
				mLastMultiple = -1;
			}
		}
		
		return returnValue;
	}

	@Override
	public void onLongPress() {
		if(null != mListener){
			mListener.onLongClick();
		}
	}

	@Override
	public void onVerticalSlide(float horizontalSlidePercent, float verticalSlidePercent, float distanceX, float distanceY) {
		if(null != mListener){
			if(mGesturesFlag == GESTURES_FLAG_IDLE && mMultiple == 1){
				mGesturesFlag = GESTURES_FLAG_VOLUME_PROGRESS;
				if(mIsDefaultControllVerticalSlide){
//					mVolumeLayout.setVisibility(View.VISIBLE);
//					mVideoProgressLayout.setVisibility(View.GONE);
				}
			} else if(mGesturesFlag == GESTURES_FLAG_IDLE && mMultiple > 1){
				mGesturesFlag = GESTURES_FLAG_DRAG_VIEW;
			}
			
			if(mGesturesFlag == GESTURES_FLAG_VOLUME_PROGRESS){
				if(mIsDefaultControllVerticalSlide){
					int volumn = (int) (verticalSlidePercent * mMaxVolume) + mCurrentVolume;
//					setVolume(volumn);
				}else{
					mListener.onVerticalSlide(verticalSlidePercent);
				}
			}else if(mGesturesFlag == GESTURES_FLAG_VIDEO_PROGRESS){
				mListener.onHorizontalSlide(horizontalSlidePercent);
			} else if(mGesturesFlag == GESTURES_FLAG_DRAG_VIEW){
				mListener.onDragViewWhenMultipleSize(mMultiple, distanceX, distanceY);
			}
		}
	}

	@Override
	public void onHorizontalSlide(float horizontalSlidePercent, float verticalSlidePercent, float distanceX, float distanceY) {
		if(null != mListener){
			if(mGesturesFlag == GESTURES_FLAG_IDLE && mMultiple == 1){
				mGesturesFlag = GESTURES_FLAG_VIDEO_PROGRESS;
				if(mIsDefaultControllHorizontalSlide){
//					mVideoProgressLayout.setVisibility(View.VISIBLE);
//					mVolumeLayout.setVisibility(View.GONE);
				}
			}else if(mGesturesFlag == GESTURES_FLAG_IDLE && mMultiple > 1){
				mGesturesFlag = GESTURES_FLAG_DRAG_VIEW;
			}
			
			if(mGesturesFlag == GESTURES_FLAG_VIDEO_PROGRESS){
				if(null != mListener){
					if(horizontalSlidePercent > 0f){
//						mImgVideoProgress.setImageResource(R.drawable.img_fast_backward);
					}else{
//						mImgVideoProgress.setImageResource(R.drawable.img_fast_forward);
					}
					mListener.onHorizontalSlide(horizontalSlidePercent);
				}
			}else if(mGesturesFlag == GESTURES_FLAG_VOLUME_PROGRESS){
				int volumn = (int) (verticalSlidePercent * mMaxVolume) + mCurrentVolume;
//				setVolume(volumn);
			}else if(mGesturesFlag == GESTURES_FLAG_DRAG_VIEW){
				mListener.onDragViewWhenMultipleSize(mMultiple, distanceX, distanceY);
			}
		}
	}
	
	
	
	public interface MediaControllerListener{
		
		/**
		 * 手势开始回调
		 */
		void onGestureBegin();
		
		/**
		 * 手势结束回调
		 */
		void onGestureEnd();
		
		/**
		 * 横向滑动时回调
		 * @param horizontalSlidePercent	滑动比例
		 */
		void onHorizontalSlide(float horizontalSlidePercent);
		
		/**
		 * 竖向滑动时回调
		 * @param verticalSlidePercent
		 */
		void onVerticalSlide(float verticalSlidePercent);
		
		/**
		 * 缩放时回调
		 * @param state			缩放状态
		 * @param scaleFactor	缩放比例
		 */
		void onScale(int state, float scaleFactor);
		
		/**
		 * 缩放完成时回调
		 * @param isExpandScaleRange	是否是扩大缩放
		 * @param lastMultiple			上次倍数
		 * @param multiple				当前倍数
		 */
		void onScaleComplete(boolean isExpandScaleRange, int lastMultiple, int multiple);
		
		/**
		 * 单击回调
		 */
		void onSingleClick();
		
		/**
		 * 双击回调
		 */
		void onDoubleClick();
		
		/**
		 * 长按回调
		 */
		void onLongClick();
		
		/**
		 * 多倍数时滑动回调
		 * @param multiple		当前缩放倍数
		 * @param distanceX		X轴移动距离
		 * @param distanceY		Y轴移动距离
		 */
		void onDragViewWhenMultipleSize(int multiple, float distanceX, float distanceY);
		
	}
	
}
