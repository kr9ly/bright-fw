package net.kr9ly.brightfw.helper.lifecycle;

import android.os.Bundle;

import net.kr9ly.brightfw.app.LifecycleController;
import net.kr9ly.brightfw.app.callback.OnCreateCallback;
import net.kr9ly.brightfw.app.callback.OnDestroyCallback;
import net.kr9ly.brightfw.app.callback.OnPauseCallback;
import net.kr9ly.brightfw.app.callback.OnResumeCallback;
import net.kr9ly.brightfw.app.callback.OnStartCallback;
import net.kr9ly.brightfw.app.callback.OnStopCallback;
import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.rxstatemachine.RxStateMachine;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

@MainScope
public class LifecycleStateMachine extends RxStateMachine<LifecycleState> implements
        OnCreateCallback,
        OnStartCallback,
        OnResumeCallback,
        OnPauseCallback,
        OnStopCallback,
        OnDestroyCallback {

    @Inject
    public LifecycleStateMachine(
            LifecycleController lifecycleController
    ) {
        super(LifecycleState.Default.class);

        lifecycleController.register(this);
    }

    public Observable<Class<? extends LifecycleState>> onTransitionObservable(final Class<? extends LifecycleState> enter, final Class<? extends LifecycleState> exit) {
        return Observable.zip(enterObservable().skip(1), exitObservable(), new Func2<Class<? extends LifecycleState>, Class<? extends LifecycleState>, Boolean>() {
            @Override
            public Boolean call(Class<? extends LifecycleState> enterState, Class<? extends LifecycleState> exitState) {
                return enterState.equals(enter) && exitState.equals(exit);
            }
        }).filter(new Func1<Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean matched) {
                return matched;
            }
        }).map(new Func1<Boolean, Class<? extends LifecycleState>>() {
            @Override
            public Class<? extends LifecycleState> call(Boolean aBoolean) {
                return enter;
            }
        });
    }

    public Observable<Class<? extends LifecycleState>> onCreateObservable() {
        return onTransitionObservable(LifecycleState.Living.class, LifecycleState.Default.class);
    }

    public Observable<Class<? extends LifecycleState>> onStartObservable() {
        return onTransitionObservable(LifecycleState.Visible.class, LifecycleState.Living.class);
    }

    public Observable<Class<? extends LifecycleState>> onResumeObservable() {
        return onTransitionObservable(LifecycleState.Running.class, LifecycleState.Visible.class);
    }

    public Observable<Class<? extends LifecycleState>> onPauseObservable() {
        return onTransitionObservable(LifecycleState.Visible.class, LifecycleState.Running.class);
    }

    public Observable<Class<? extends LifecycleState>> onStopObservable() {
        return onTransitionObservable(LifecycleState.Living.class, LifecycleState.Visible.class);
    }

    public Observable<Class<? extends LifecycleState>> onDestroyObservable() {
        return onTransitionObservable(LifecycleState.Destroyed.class, LifecycleState.Living.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        expect(LifecycleState.Default.class).create();
    }

    @Override
    public void onDestroy() {
        expect(LifecycleState.Living.class).destroy();
    }

    @Override
    public void onPause() {
        expect(LifecycleState.Running.class).pause();
    }

    @Override
    public void onResume() {
        expect(LifecycleState.Visible.class).resume();
    }

    @Override
    public void onStart() {
        expect(LifecycleState.Living.class).start();
    }

    @Override
    public void onStop() {
        expect(LifecycleState.Visible.class).stop();
    }
}
