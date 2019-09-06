package com.ly.superalive.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.TokenWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ly.superalive.service.binder.LocalBinder;


/**
 * version 1.0.0
 * description 本地service
 * author ly
 * date 2019年9月6日 10:34:25
 */
public class LocalService extends Service {
    private LocalServiceConnection connection;
    private LocalBinder localBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("localService:", "onCreate");
        connection = new LocalServiceConnection(this);
        Toast.makeText(this, "localService启动", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("localService:", "onBind");
        localBinder = new LocalBinder();
        return localBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("localService:", "onstartCommend");
        Intent intentRemote = new Intent(this, RemoteService.class);
        startService(intentRemote);
        bindService(intentRemote, connection,BIND_ABOVE_CLIENT);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        Log.e("localService:", "onDestrory");
    }
}
