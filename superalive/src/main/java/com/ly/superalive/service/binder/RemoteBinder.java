package com.ly.superalive.service.binder;

import android.os.RemoteException;

import com.ly.superalive.IRemoteAidlInterface;

public class RemoteBinder extends IRemoteAidlInterface.Stub {
    @Override
    public void wakeUp() throws RemoteException {
      //发送通知 提高等级
    }
}
