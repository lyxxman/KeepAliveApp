package com.ly.superalive;

import android.app.Application;
import android.content.Intent;

import com.ly.superalive.service.LocalService;
import com.ly.superalive.service.RemoteService;

/**
 * @author ly
 * @version 1.0.0
 * @descrprition superalive
 * @date 2019年9月6日 15:20:18
 */
public class SuperAlive {

    private static SuperAlive instance = null;
    public final int LEVEL_1 = 1; //双进程唤醒
    public final int LEVEL_2 = 2; //增加1像素activity
    public final int LEVEL_3 = 3; //增加无声音乐播放
    public final int LEVEL_4 = 4; //可以扩展自定义第三方唤醒

    public int level = 1;
    public boolean DEUBG = false;

    private SuperAlive() {

    }

    public static SuperAlive getInstance() {
        if (instance == null) {
            instance = new SuperAlive();
        }
        return instance;
    }

    public void init(int level, boolean isDebug) {
        this.level = level;
        this.DEUBG = isDebug;
    }

    public int getLevel() {
        return level;
    }

    public boolean isDebug() {
        return DEUBG;
    }

    /**
     * 启动服务
     */
    public void start(Application application) {
        Intent local = new Intent(application, LocalService.class);
//        Intent remote = new Intent(application, RemoteService.class);
        application.startService(local);
//        application.startService(remote);
    }

    /**
     * 关闭服务
     */
    public void close(Application application) {
        Intent local = new Intent(application, LocalService.class);
        Intent remote = new Intent(application, RemoteService.class);
        application.stopService(local);
        application.stopService(remote);
    }

}
