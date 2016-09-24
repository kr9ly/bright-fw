package net.kr9ly.brightfw.event;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

public class InterProcessEventBusService extends Service {

    static final int MESSAGE_TYPE_INITIALIZE = 1;

    static final int MESSAGE_TYPE_SEND = 2;

    private Set<Messenger> messengers = new HashSet<>();

    private static class EventHandler extends Handler {

        private Set<Messenger> messengers;

        private EventHandler(Set<Messenger> messengers) {
            this.messengers = messengers;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MESSAGE_TYPE_INITIALIZE:
                    messengers.add(msg.replyTo);
                    break;
                case MESSAGE_TYPE_SEND:
                    for (Messenger messenger : messengers) {
                        try {
                            messenger.send(Message.obtain(null, 0, msg.obj));
                        } catch (RemoteException e) {
                            //
                        }
                    }
                    break;
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(new EventHandler(messengers)).getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }
}
