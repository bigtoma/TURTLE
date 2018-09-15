package com.team9.admin.turtle001.Mussic_player

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.*
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import com.squareup.picasso.Picasso
import com.team9.admin.turtle001.Mussic_player.bean.MusicList
import com.team9.admin.turtle001.Mussic_player.interfaces.RecordAnimationListener
import com.team9.admin.turtle001.Mussic_player.server.MusicPlayerService
import com.yyp.musicplayer.util.dpToPx
import com.team9.admin.turtle001.R
import com.yyp.musicplayer.util.msecToPlayTime
import kotlinx.android.synthetic.main.activity_music_play.*


class Main_Activity : Activity(), RecordAnimationListener {

    var serviceIntent: Intent? = null

    var serviceConnection: ServiceConnection? = null
    private var binder: MusicPlayerService.MyBinder? = null
    var isFinished = false // 是否结束当前activity的标志
    var isFirstPlay = true // 是否首次播放
    var isPause = true
    val data1 = arrayListOf<MusicList>()

    // 播放列表

    var musicUrl = ""
    var playIndex = -1
    var bgimag = "http://songlist-turtle.oss-cn-hangzhou.aliyuncs.com/7%E5%A4%A9%E8%83%8C%E6%99%AF.jpg"
    var recordimg ="http://songlist-turtle.oss-cn-hangzhou.aliyuncs.com/7%E5%A4%A9%E8%83%8C%E6%99%AF.jpg"

    private var currentValue = 0f
    private var objAnim: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_play)


        data1.run {
            add(MusicList(
                "1-正念冥想基础",
                    bgimag,
                    recordimg,
                    "http://music-mingxiang.oss-cn-hangzhou.aliyuncs.com/1-%E6%AD%A3%E5%BF%B5%E5%86%A5%E6%83%B3%E5%9F%BA%E7%A1%80.mp3"
        ))
            add(MusicList(
                "2-提示呼吸法",
                    bgimag,
                    recordimg,
                "http://music-mingxiang.oss-cn-hangzhou.aliyuncs.com/2-%E6%8F%90%E7%A4%BA%E5%91%BC%E5%90%B8%E6%B3%95.mp3"
        ))
            add(MusicList(
                "3-有意识的注意力",
                    bgimag,
                    recordimg,
                "http://music-mingxiang.oss-cn-hangzhou.aliyuncs.com/3-%E6%9C%89%E6%84%8F%E8%AF%86%E7%9A%84%E6%B3%A8%E6%84%8F%E5%8A%9B.mp3"

        ))
            add(MusicList(
                "4-摆脱“无意识”模式",
                    bgimag,
                    recordimg,
                "http://music-mingxiang.oss-cn-hangzhou.aliyuncs.com/4-%E6%91%86%E8%84%B1%E2%80%9C%E6%97%A0%E6%84%8F%E8%AF%86%E2%80%9D%E6%A8%A1%E5%BC%8F.mp3"

        ))
            add(MusicList(
                "5-无为的价值",
                    bgimag,
                    recordimg,
                "http://music-mingxiang.oss-cn-hangzhou.aliyuncs.com/5-%E6%97%A0%E4%B8%BA%E7%9A%84%E4%BB%B7%E5%80%BC.mp3"

        ))
            add(MusicList(
                "6-耐心",
                    bgimag,
                    recordimg,
                "http://music-mingxiang.oss-cn-hangzhou.aliyuncs.com/6-%E8%80%90%E5%BF%83.mp3"

        ))
            add(MusicList(
                "7-觉知",
                    bgimag,
                    recordimg,
                "http://music-mingxiang.oss-cn-hangzhou.aliyuncs.com/7-%E8%A7%89%E7%9F%A5.mp3"

        ))
        }
        init()
    }

    fun init(){
        serviceIntent = Intent(this, MusicPlayerService::class.java)

        initAnimation()
        initMediaPlayer()
        setListener()

        setPlayView(data1[++playIndex])
    }

    /**
     * 初始化动画
     */
    override fun initAnimation(){
        // 设置动画，从上次停止位置开始,这里是顺时针旋转360度
        objAnim = ObjectAnimator.ofFloat(programPlayRecordImage, "Rotation",
                currentValue - 360, currentValue)
        // 设置持续时间
        objAnim!!.duration = 30000
        // 设置循环播放
        objAnim!!.repeatCount = ObjectAnimator.INFINITE
        // 设置匀速播放
        val lin: LinearInterpolator = LinearInterpolator()
        objAnim!!.interpolator = lin
        // 设置动画监听
        objAnim!!.addUpdateListener({ animation ->
            // 监听动画执行的位置，以便下次开始时，从当前位置开始
            currentValue = animation.animatedValue as Float
        })
    }

    /**
     * 开启动画
     */
    override fun startAnimation() {
        if(!objAnim!!.isStarted) {
            // 每次暂停动画后，都得初始化一次
            initAnimation()
            objAnim!!.start()
        }
    }

    /**
     * 停止动画
     */
    override fun stopAnimation() {
        if(objAnim!!.isStarted){
            objAnim!!.end()
            currentValue = 0f // 重置起始位置
        }
    }

    /**
     * 暂停动画
     */
    override fun pauseAnimation() {
        if(objAnim!!.isRunning){
            objAnim!!.cancel()
        }
    }

    fun initMediaPlayer(){
        serviceIntent!!.putExtra("action", "init")
        serviceIntent!!.putExtra("musicUrl", "")
        startService(serviceIntent)
    }

    fun setListener(){
        programPlayStart.setOnClickListener { playPause() }
        programPlayPrevious.setOnClickListener {
            // 播放到第一首歌时，下标移动到最后一首
            if(playIndex == 0){
                playIndex = data1.size
                playPause()
            }

            resetPlayer()
            setPlayView(data1[--playIndex])
            playPause()
        }
        programPlayNext.setOnClickListener {
            // 播放到最后一首歌时，下标移动到第一首
            if(playIndex==0){
                playPause()
            }
            if(playIndex == data1.size.minus(1)){
                playIndex = -1
            }

            resetPlayer()
            setPlayView(data1[++playIndex])
            playPause()
        }
    }

    private var handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            when(msg!!.what){
                INIT_SEEKBAR -> {
                    // 设置进度条的最大长度
                    programPlayProgressBar.max = binder!!.musicDuration
                    programPlayProgressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                            // 手动控制进度：需要先控制播放，再设置进度
                            if(fromUser){
                                isPause = true // 改变进度条的值，即可播放
                                playPause()
                                binder!!.seekTo(progress)

                            }
                        }

                        override fun onStartTrackingTouch(seekBar: SeekBar) {

                        }

                        override fun onStopTrackingTouch(seekBar: SeekBar) {

                        }
                    })
                }
                UPDATE_SEEKBAR -> {
                    // 改变当前进度条的值、起始时间
                    programPlayProgressBar.progress = binder!!.musicCurrentPosition
                    programPlayStartTime.text = msecToPlayTime(binder!!.musicCurrentPosition)
                }
                UPDATE_SEEKBAR_BUFFER -> {
                    // 设置缓存进度
                    programPlayProgressBar.secondaryProgress = msg.arg1
                }
                NEXT_MUSIC -> { // 循环播放
                    resetPlayer()
                    if(playIndex == data1.size.minus(1)){
                        playIndex = -1
                    }
                    setPlayView(data1[++playIndex])
                    playPause()
                }
                MUSIC_DURATION -> {
                    // 设置歌曲总时长
                    programPlayEndTime.text = msecToPlayTime(binder!!.musicDuration)
                }
            }
        }
    }

    /**
     * 绑定服务
     */
    fun bindService(){
        if (serviceConnection == null) {
            serviceConnection = object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName, service: IBinder) {

                    binder = service as MusicPlayerService.MyBinder

                    val msg1 = handler.obtainMessage()
                    msg1.what = INIT_SEEKBAR
                    handler.sendMessage(msg1)

                    // 连接之后启动子线程获取当前进度
                    object : Thread() {
                        override fun run() {
                            while (true) {

                                if(isFinished){
                                    break
                                }

                                val msg2 = handler.obtainMessage()
                                msg2.what = UPDATE_SEEKBAR
                                handler.sendMessage(msg2)

                                binder!!.setOnMusicBufferingListener{ progress ->
                                    val msg3 = handler.obtainMessage()
                                    msg3.what = UPDATE_SEEKBAR_BUFFER
                                    msg3.arg1 = progress
                                    handler.sendMessage(msg3)
                                }

                                binder!!.setOnCompleteListener {
                                    val msg4 = handler.obtainMessage()
                                    msg4.what = NEXT_MUSIC
                                    handler.sendMessage(msg4)
                                }

                                val msg5 = handler.obtainMessage()
                                msg5.what = MUSIC_DURATION
                                handler.sendMessage(msg5)

                                // 100ms更新一次
                                try {
                                    Thread.sleep(100)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    }.start()
                }

                override fun onServiceDisconnected(name: ComponentName) {
                }
            }

            // 以绑定方式连接服务
            bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    /**
     * 设置播放界面
     */
    fun setPlayView(item: MusicList){
        // 设置背景、专辑图
     Picasso.with(this)
                .load(item.bg_img)
                .resize(dpToPx(400), dpToPx(330))
                .config(Bitmap.Config.RGB_565)
                .into(programPlayHeaderBg)
        Picasso.with(this)
                .load(item.record_img)
                .resize(dpToPx(150), dpToPx(150))
                .config(Bitmap.Config.RGB_565)
                .into(programPlayRecordImage)
        // 设置歌单、节目名
        programPlayName.text = item.name
        // 设置歌曲总时长
        programPlayEndTime.text = "00:00"
        // 歌曲Url
        musicUrl = item.audio_url
    }

    /**
     * 播放/停止操作
     */
    fun playPause(){
        if(isPause){
            isPause = false
            programPlayStart.setImageResource(R.drawable.program_play_pause)
            // 开启图片旋转动画
            startAnimation()

            // 是否第一次播放
            if(!isFirstPlay){
                serviceIntent!!.putExtra("action", "rePlay")
                serviceIntent!!.putExtra("musicUrl", "")
                startService(serviceIntent)
            }else{
                isFirstPlay = false
                serviceIntent!!.putExtra("action", "play")
                serviceIntent!!.putExtra("musicUrl", musicUrl)
                startService(serviceIntent)
            }
        }else {
            isPause = true
            programPlayStart.setImageResource(R.drawable.program_play_start)
            // 暂停动画
            pauseAnimation()

            serviceIntent!!.putExtra("action", "pause")
            serviceIntent!!.putExtra("musicUrl", "")
            startService(serviceIntent)
        }

        // 播放后，再绑定服务
        bindService()
    }

    /**
     * 重置播放器
     */
    fun resetPlayer(){
        // 停止服务
        stopService(serviceIntent)
        // 关闭动画
        stopAnimation()

        isPause = true
        isFirstPlay = true
        programPlayStart.setImageResource(R.drawable.program_play_start)
        programPlayProgressBar.progress = 0
        programPlayStartTime.text = "00:00"
    }

    companion object{
        val INIT_SEEKBAR = 1    // 初始化seekbar信息
        val UPDATE_SEEKBAR = 2  // 更新seekbar
        val UPDATE_SEEKBAR_BUFFER = 4  // 更新seekbar缓存进度
        val NEXT_MUSIC = 5      // 播放下一首
        val MUSIC_DURATION = 6  // 获取音乐总时长
    }

    override fun onDestroy() {
        super.onDestroy()
        // 停止更新UI
        isFinished = true
        // 释放播放器资源
        serviceIntent!!.putExtra("action", "release")
        serviceIntent!!.putExtra("musicUrl", "")
        startService(serviceIntent)
        // 解绑服务
        if(serviceConnection != null){
            unbindService(serviceConnection)
        }
    }
}
