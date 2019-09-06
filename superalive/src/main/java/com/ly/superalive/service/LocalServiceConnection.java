package com.ly.superalive.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.ly.superalive.IRemoteAidlInterface;
import com.ly.superalive.service.binder.LocalBinder;

public class LocalServiceConnection implements ServiceConnection {

    private Context mContext;
    private IRemoteAidlInterface iRemoteAidlInterface;

    public LocalServiceConnection(Context mContext) {
        this.mContext = mContext;

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.e("localService:", "onServiceConnected");
        try {
            iRemoteAidlInterface =   IRemoteAidlInterface.Stub.asInterface(service);
            iRemoteAidlInterface.wakeUp();
        } catch (Exception e) {

        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.e("localService:", "onServiceDisconnected and restart");
        //端口的时候，重新绑定远程进程
        mContext.bindService(new Intent(mContext, RemoteService.class), this, Context.BIND_IMPORTANT);
    }

    @Override
    public void onBindingDied(ComponentName name) {

    }

    @Override
    public void onNullBinding(ComponentName name) {

    }
}
