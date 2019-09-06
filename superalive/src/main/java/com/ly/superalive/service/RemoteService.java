package com.ly.superalive.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ly.superalive.service.binder.RemoteBinder;

/**
 * version 1.0.0
 * description 远程service (独立进程)
 * author ly
 * date 2019年9月6日 10:34:25
 */
public class RemoteService extends Service {
    private RemoteServiceConnection connection;
    private RemoteBinder remoteBinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("remoteService:", "onBind");
        remoteBinder = new RemoteBinder();
        return remoteBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("remoteService:", "onCreate");
        connection = new RemoteServiceConnection(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //重启的时候重新启动localService
        Log.e("remoteService:", "onStartCommand");
        Intent localIntent = new Intent(RemoteService.this, LocalService.class);
//        startService(localIntent);
        bindService(localIntent, connection, BIND_ABOVE_CLIENT);
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("remoteService:", "onDestrory");
        super.onDestroy();
        unbindService(connection);
    }
}
