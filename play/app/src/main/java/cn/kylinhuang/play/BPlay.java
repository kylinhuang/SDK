package cn.kylinhuang.play;

import android.util.Log;
import android.view.View;


import cn.kylinhuang.play.media.IjkVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static android.content.ContentValues.TAG;

/**
 * Created by kylinhuang on 16/01/2017.
 */

public class BPlay extends IPlay {


    private IjkVideoView playView;



    @Override
    public void init() {

        Log.e(TAG," BPlay init");
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        playView = new IjkVideoView(PlayApplication.getApplication().getApplicationContext());

    }

    @Override
    public void setVideoPath(String url) {
        playView.setVideoPath(url);
    }

    @Override
    public void stopPlayback() {
//        playView.stopPlayback();

        playView.stopPlayback();
        playView.release(true);
        playView.stopBackgroundPlay();

        //TODO  底层自己处理
        IjkMediaPlayer.native_profileEnd();

    }

    @Override
    public int getPlayStatus() {
        return 0;
    }

    @Override
    public boolean isPlaying() {
        return playView.isPlaying();
    }

    @Override
    public void play() {
        playView.start();
    }

    @Override
    public void pause() {
        playView.pause();
    }

    @Override
    public View getPlayView() {
        return playView;
    }

    @Override
    public void setVideoWidthAndHeight(int w, int h) {

    }

    @Override
    public void setTextureXYAndTimes(int x, int y, int w, int h, double multiple) {
//        playView.setT
        playView.getMediaPlayer().setTextureXYAndTimes(x,y,w,h,(float) multiple);
    }

    @Override
    public void setAudioSize(int size) {

    }

    @Override
    public void getAudioSize() {

    }

    @Override
    public long getCurrentPosition() {
        return playView.getCurrentPosition();
    }

    @Override
    public void seekTo(long position) {
        playView.seekTo((int)position);
    }

    @Override
    public String getVersion() {
        return playView.getMediaPlayer().getVersion();
    }

    @Override
    public String getResolution() {
        return null;
    }


    @Override
    public void setVideoPlayCallBack(IVideoPlayCallBack mVideoPlayCallBack) {
        playView.setOnPreparedListener(mOnPreparedListener);
        playView.setOnCompletionListener(mOnCompletionListener);
        playView.setOnErrorListener(mOnErrorListener);
        playView.setOnInfoListener(mOnInfoListener);
    }

    private IMediaPlayer.OnPreparedListener mOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            Log.e("BPlay","onPrepared");

           if (null != mVideoPlayCallBack ) mVideoPlayCallBack.onPrepared();
        }
    };
    private IMediaPlayer.OnCompletionListener mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            Log.e("BPlay","onCompletion");
            if (null != mVideoPlayCallBack ) mVideoPlayCallBack.onCompletion();
        }
    };
    private IMediaPlayer.OnErrorListener mOnErrorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int what, int extra) {
            Log.e("BPlay","onError " + what + " " + extra);
            if (null != mVideoPlayCallBack ) mVideoPlayCallBack.onError(what,extra);
            return false;
        }
    };
    private IMediaPlayer.OnInfoListener mOnInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int arg0, int arg1) {
            Log.e("BPlay","onInfo " + arg0 +" "+ arg1);
            if (null != mVideoPlayCallBack ) mVideoPlayCallBack.onInfo( arg0, arg1);
            return false;
        }
    };
}
