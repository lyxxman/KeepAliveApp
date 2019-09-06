package com.ly.superalive.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.ly.superalive.IRemoteAidlInterface;

public class RemoteServiceConnection implements ServiceConnection {
    private Context mContext;
    private IRemoteAidlInterface iRemoteAidlInterface;

    public RemoteServiceConnection(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.e("remoteService:", "onServiceConnected");
        try {
            iRemoteAidlInterface = IRemoteAidlInterface.Stub.asInterface(service);
            iRemoteAidlInterface.wakeUp();
        } catch (Exception e) {

        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        //重新启动localService
        mContext.startService(new Intent(mContext, LocalService.class));
        Log.e("remoteService:", "onServiceDisconnected and restart");
        mContext.bindService(new Intent(mContext, LocalService.class), this, Context.BIND_IMPORTANT);
    }

    @Override
    public void onBindingDied(ComponentName name) {

    }

    @Override
    public void onNullBinding(ComponentName name) {

    }
}
