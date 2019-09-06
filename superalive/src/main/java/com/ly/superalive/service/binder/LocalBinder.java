package com.ly.superalive.service.binder;

import android.os.RemoteException;

import com.ly.superalive.IRemoteAidlInterface;

public class LocalBinder extends IRemoteAidlInterface.Stub{

    @Override
    public void wakeUp() throws RemoteException {

    }
}
