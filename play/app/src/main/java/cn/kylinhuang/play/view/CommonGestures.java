/*
 * Copyright (C) 2012 YIXIA.COM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.kylinhuang.play.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class CommonGestures {
	public static final int SCALE_STATE_BEGIN = 0;
	public static final int SCALE_STATE_SCALEING = 1;
	public static final int SCALE_STATE_END = 2;

	private boolean mGestureEnabled;

	private GestureDetectorCompat mDoubleTapGestureDetector;
	private GestureDetectorCompat mTapGestureDetector;
	private ScaleGestureDetector mScaleDetector;

	private Activity mContext;

	@SuppressLint("NewApi")
	public CommonGestures(Activity ctx) {
		mContext = ctx;
		mDoubleTapGestureDetector = new GestureDetectorCompat(mContext, new DoubleTapGestureListener());
		mTapGestureDetector = new GestureDetectorCompat(mContext, new TapGestureListener());
		mScaleDetector = new ScaleGestureDetector(mContext, new ScaleDetectorListener());
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (mListener == null)
			return false;
		
		if(null == event)
			return false;

		if (mTapGestureDetector.onTouchEvent(event))
			return true;

		if (event.getPointerCount() > 1) {
			try {
				if (mScaleDetector != null && mScaleDetector.onTouchEvent(event))
					return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (mDoubleTapGestureDetector.onTouchEvent(event))
			return true;

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_UP:
			mListener.onGestureEnd();
			break;
		}

		return true;
	}

	private class TapGestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onSingleTapConfirmed(MotionEvent event) {
			if (mListener != null)
				mListener.onSingleTap();
			return true;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			if (mListener != null && mGestureEnabled)
				mListener.onLongPress();
		}
	}

	@SuppressLint("NewApi")
	private class ScaleDetectorListener implements ScaleGestureDetector.OnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			if (mListener != null && mGestureEnabled)
				mListener.onScale(detector.getScaleFactor(), SCALE_STATE_SCALEING, detector);
			return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector detector) {
			if (mListener != null && mGestureEnabled)
				mListener.onScale(0F, SCALE_STATE_END, detector);
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			if (mListener != null && mGestureEnabled)
				mListener.onScale(0F, SCALE_STATE_BEGIN, detector);
			return true;
		}
	}

	private class DoubleTapGestureListener extends SimpleOnGestureListener {
		private boolean mDown = false;

		@Override
		public boolean onDown(MotionEvent event) {
			mDown = true;
			return super.onDown(event);
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if (mListener != null && mGestureEnabled && e1 != null && e2 != null) {
				if (mDown) {
					mListener.onGestureBegin();
					mDown = false;
				}
				
				float wholeDistanceX = Math.abs(e2.getX() - e1.getX()), wholeDistanceY = Math.abs(e2.getY() - e1.getY());
				if (wholeDistanceX >= wholeDistanceY) {
					float oldX = e1.getX(), oldY = e1.getY();
					int screenWidth = Utils.getScreenWidth(mContext);
					int screenHeight = Utils.getScreenHeight(mContext);
					float horizontalPercent = (oldX - e2.getX(0)) / screenWidth;
					float verticalPpercent = (oldY - e2.getY(0)) / screenHeight;
					
					mListener.onHorizontalSlide(horizontalPercent, verticalPpercent, e2.getX() - e1.getX(), e2.getY() - e1.getY());
				}else{
					float oldX = e1.getX(), oldY = e1.getY();
					int screenWidth = Utils.getScreenWidth(mContext);
					int screenHeight = Utils.getScreenHeight(mContext);
					float horizontalPercent = (oldX - e2.getX(0)) / screenWidth;
					float verticalPpercent = (oldY - e2.getY(0)) / screenHeight;
					
					mListener.onVerticalSlide(horizontalPercent, verticalPpercent, e2.getX() - e1.getX(), e2.getY() - e1.getY());
				}
			}
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		@Override
		public boolean onDoubleTap(MotionEvent event) {
			if (mListener != null && mGestureEnabled)
				mListener.onDoubleTap();
			return super.onDoubleTap(event);
		}
	}

	public void setTouchListener(TouchListener l, boolean enable) {
		mListener = l;
		mGestureEnabled = enable;
	}

	private TouchListener mListener;

	public interface TouchListener {
		public void onGestureBegin();

		public void onGestureEnd();

		public void onHorizontalSlide(float horizontalSlidePercent, float verticalSlidePercent, float distanceX, float distanceY);
		
		public void onVerticalSlide(float horizontalSlidePercent, float verticalSlidePercent, float distanceX, float distanceY);

		public void onSingleTap();

		public void onDoubleTap();

		public void onScale(float scaleFactor, int state, ScaleGestureDetector detector);

		public void onLongPress();
	}
}
