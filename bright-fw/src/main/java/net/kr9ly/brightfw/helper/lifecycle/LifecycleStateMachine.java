package net.kr9ly.brightfw.helper.lifecycle;

import android.os.Bundle;

import net.kr9ly.brightfw.app.LifecycleController;
import net.kr9ly.brightfw.dependency.lifecycle.OnCreateCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnDestroyCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnPauseCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnResumeCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStartCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStopCallback;
import net.kr9ly.rxstatemachine.RxStateMachine;

public class LifecycleStateMachine extends RxStateMachine<LifecycleState> implements
        OnCreateCallback,
        OnStartCallback,
        OnResumeCallback,
        OnPauseCallback,
        OnStopCallback,
        OnDestroyCallback
{
    public LifecycleStateMachine(
            LifecycleController lifecycleController
    ) {
        super(LifecycleState.Default.class);

        lifecycleController.register(this);
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
