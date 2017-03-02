package cn.kylinhuang.play;


import android.view.View;

/**
 * Created by kylinhuang on 16/01/2017.
 */

public abstract class IPlay {
    /**
     * 初始化完成 状态   资源释放
     */
    public int PlayStatus_inited = 1;
    /**
     * 开启播放准备中 状态
     */
    public int PlayStatus_prepareing = 2;
    /**
     * 播放中
     */
    public int PlayStatus_playing = 3;
    /**
     * 主动暂停  可继续播放
     */
    public int PlayStatus_pause = 4;
    /**
     * 播放停止 播放完成
     */
    public int PlayStatus_stop = 5;

    /**
     * 播放完成
     */
    public int PlayStatus_Complete = 6;

    /**
     *
     * 简单播放器  可以不关心这些状态   简单播放器 isplaying
     *
     *
     * 播放器状态
     * 播放状态  可以使用枚举
     * 介意枚举 内存问题
     * 暂时用 int
     */
    public int mPlayStatus = PlayStatus_inited;



    //---------------------播放器状态---------------------//

    /**
     * 获取播放器状态
     * @return
     */
    public abstract int getPlayStatus();

    /**
     * 是否 播放中
     * @return
     */
    public abstract boolean isPlaying();



    //---------------------播放器 控制 设置---------------------//

    /**
     * 初始化播放器
     */
    public  abstract void init();

    /**
     * 设置播放器  资源
     * @param url
     */
    public abstract void setVideoPath(String url);

    /**
     * 开始播放
     */
    public abstract void play();

    /**
     * 暂停播放
     */
    public abstract void pause();


    /**
     * 停止 播放  and release
     */
    public abstract void stopPlayback();

    /**
     * 获取播放器控件 View
     * @return
     */
    public abstract View getPlayView();


    //---------------------播放器大小---------------------//
    /** 设置 播放器 宽高
     * @param w
     * @param h
     */
    public abstract void setVideoWidthAndHeight(int w, int h);
    /**
     * 设置视频缩放 特殊功能
     * @param x 缩放起始位置 x轴
     * @param y 缩放起始位置 y轴
     *
     * @param w 视频缩放后 显示宽度
     * @param h 视频缩放后 显示高度
     *
     */
    public abstract void setTextureXYAndTimes(int x, int y, int w, int h,double multiple);


    //---------------------播放器音量---------------------//
    /**
     * 设置 视频音量
     *
     * android  系统 可以 AudioManager 控制音量  但是  这改变了系统的
     * 其实 可以控制播放器 的 视频 的 音量
     *
     *
     * @param size  0 - 100
     *
     */
    public abstract void setAudioSize(int size );

    /**
     * 获取视频 音量大小
     */
    public abstract void getAudioSize();


    //---------------------播放器位置---------------------//
    /**
     * 获取当前播放位置
     */
    public abstract long getCurrentPosition();

    /**
     * 跳转 至 某位置播放
     * @param position
     */
    public abstract void seekTo(long position);

    //---------------------播放器 信息---------------------//

    /**
     * 获取播放器版本 信息
     */
    public abstract String getVersion();


    /**
     * 视频分辨率
     *
     * @deprecated
     */
    public abstract String getResolution();


//    /**
//     * 视频分辨率  kbps
//     * @deprecated
//     */
//    public abstract String getBitRate();
//
//
//    /**
//     * 视频帧率  kbps
//     * @return
//     */
//    public abstract String getFrameRate();







    //------------------- 播放器监听 -----------------------//
    public IVideoPlayCallBack mVideoPlayCallBack ;
    /**
     * @param mVideoPlayCallBack
     */
    public void setVideoPlayCallBack(IVideoPlayCallBack mVideoPlayCallBack){
        this.mVideoPlayCallBack = mVideoPlayCallBack ;
    };


    public  void setAudioVoiceFlag(int i){

    };


    public interface IVideoPlayCallBack {
        /** 方法在查找操作完成后回调 */
        void onSeeked();

        /** 流媒体 播放完毕后回调的方法 */
        void onCompletion();

        /**
         * 该方法在媒体播放时出现信息或者警告时回调该方法;
         *
         *
         *
         * */
        boolean onInfo(int arg0, int arg1);

        /** 播放位置更新时回调 */
        boolean onPlayingPositionUpdate(int arg0);

        /**
         *
         * 在异步操作中出现错误时会回调该方法, 其它情况下出现错误时直接抛出异常
         * 播放器 错误值  建议明确规定
         *
         *
         * 统计错误code 不同播放器设定不同区间
         *
         * 错误 统计 可以使用 Umeng   计数统计 UmengUtils  （TODO 添加 复杂计数统计 接口）
         *
         * */
        boolean onError(int what, int extra);

        /** 该方法在进入 Prepared 状态 并 开始播放的时候回调; */
        void onPrepared();


        /**
         * 视频中 网络状态   传输 速度
         *
         * 对于 单纯播放器 可以 不需要 传输速度
         *
         * 需要网络 状态   网络 不佳
         *
         *
         * @deprecated
         *
         * @param what
         * @param extra
         * @return
         */
        boolean onNetworkStatus(int what, int extra);
    }
}
