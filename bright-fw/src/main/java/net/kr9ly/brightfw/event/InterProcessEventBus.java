package net.kr9ly.brightfw.event;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.io.Serializable;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class InterProcessEventBus {

    private PublishSubject<Serializable> subject = PublishSubject.create();

    private BehaviorSubject<Messenger> messengerSubject = BehaviorSubject.create();

    public InterProcessEventBus(Context context) {
        context.bindService(new Intent(context, InterProcessEventBusService.class), new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Messenger messenger = new Messenger(iBinder);
                try {
                    Message message = Message.obtain(null, InterProcessEventBusService.MESSAGE_TYPE_INITIALIZE);
                    message.replyTo = new Messenger(new ReplyHandler(subject));
                    messenger.send(message);

                    messengerSubject.onNext(messenger);
                } catch (RemoteException e) {
                    //
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                messengerSubject.onNext(null);
            }
        }, Context.BIND_AUTO_CREATE);
    }

    public <T extends Serializable> Observer<T> sender(Class<T> eventClass) {
        return new Observer<T>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final T t) {
                messengerSubject.filter(new Func1<Messenger, Boolean>() {
                    @Override
                    public Boolean call(Messenger messenger) {
                        return messenger != null;
                    }
                }).subscribe(new Action1<Messenger>() {
                    @Override
                    public void call(Messenger messenger) {
                        try {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("object", t);
                            messenger.send(Message.obtain(null, InterProcessEventBusService.MESSAGE_TYPE_SEND, bundle));
                        } catch (RemoteException e) {
                            //
                        }
                    }
                });
            }
        };
    }

    public <T extends Serializable> Observable<T> listener(final Class<T> eventClass) {
        return subject
                .filter(new Func1<Serializable, Boolean>() {
                    @Override
                    public Boolean call(Serializable serializable) {
                        return eventClass.isInstance(serializable);
                    }
                })
                .map(new Func1<Serializable, T>() {
                    @Override
                    public T call(Serializable serializable) {
                        return eventClass.cast(serializable);
                    }
                })
                .onBackpressureBuffer();
    }

    private static class ReplyHandler extends Handler {

        private PublishSubject<Serializable> subject;

        public ReplyHandler(PublishSubject<Serializable> subject) {
            this.subject = subject;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            subject.onNext(((Bundle) msg.obj).getSerializable("object"));
        }
    }
}
