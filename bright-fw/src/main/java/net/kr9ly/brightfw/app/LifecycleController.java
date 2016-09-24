package net.kr9ly.brightfw.app;

import android.os.Bundle;
import android.view.KeyEvent;

import net.kr9ly.brightfw.app.callback.DispatchKeyEventCallback;
import net.kr9ly.brightfw.app.callback.OnCreateCallback;
import net.kr9ly.brightfw.app.callback.OnDestroyCallback;
import net.kr9ly.brightfw.app.callback.OnPauseCallback;
import net.kr9ly.brightfw.app.callback.OnRestoreInstanceStateCallback;
import net.kr9ly.brightfw.app.callback.OnResumeCallback;
import net.kr9ly.brightfw.app.callback.OnSaveInstanceStateCallback;
import net.kr9ly.brightfw.app.callback.OnStartCallback;
import net.kr9ly.brightfw.app.callback.OnStopCallback;
import net.kr9ly.brightfw.dependency.scope.MainScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@MainScope
public class LifecycleController {

    private final List<OnCreateCallback> onCreateCallbacks = new ArrayList<>();

    private final List<OnStartCallback> onStartCallbacks = new ArrayList<>();

    private final List<OnResumeCallback> onResumeCallbacks = new ArrayList<>();

    private final List<OnPauseCallback> onPauseCallbacks = new ArrayList<>();

    private final List<OnStopCallback> onStopCallbacks = new ArrayList<>();

    private final List<OnDestroyCallback> onDestroyCallbacks = new ArrayList<>();

    private final List<OnSaveInstanceStateCallback> onSaveInstanceStateCallbacks = new ArrayList<>();

    private final List<OnRestoreInstanceStateCallback> onRestoreInstanceStateCallbacks = new ArrayList<>();

    private final List<DispatchKeyEventCallback> dispatchKeyEventCallbacks = new ArrayList<>();

    @Inject
    public LifecycleController() {

    }

    public void register(Object callbacks) {
        if (callbacks instanceof OnCreateCallback) {
            onCreateCallbacks.add((OnCreateCallback) callbacks);
        }
        if (callbacks instanceof OnStartCallback) {
            onStartCallbacks.add((OnStartCallback) callbacks);
        }
        if (callbacks instanceof OnResumeCallback) {
            onResumeCallbacks.add((OnResumeCallback) callbacks);
        }
        if (callbacks instanceof OnPauseCallback) {
            onPauseCallbacks.add((OnPauseCallback) callbacks);
        }
        if (callbacks instanceof OnStopCallback) {
            onStopCallbacks.add((OnStopCallback) callbacks);
        }
        if (callbacks instanceof OnDestroyCallback) {
            onDestroyCallbacks.add((OnDestroyCallback) callbacks);
        }
        if (callbacks instanceof OnSaveInstanceStateCallback) {
            onSaveInstanceStateCallbacks.add((OnSaveInstanceStateCallback) callbacks);
        }
        if (callbacks instanceof OnRestoreInstanceStateCallback) {
            onRestoreInstanceStateCallbacks.add((OnRestoreInstanceStateCallback) callbacks);
        }
        if (callbacks instanceof DispatchKeyEventCallback) {
            dispatchKeyEventCallbacks.add((DispatchKeyEventCallback) callbacks);
        }
    }

    /* package */ void onCreate(Bundle savedInstanceState) {
        for (OnCreateCallback callback : onCreateCallbacks) {
            callback.onCreate(savedInstanceState);
        }
    }

    /* package */ void onStart() {
        for (OnStartCallback callback : onStartCallbacks) {
            callback.onStart();
        }
    }

    /* package */ void onResume() {
        for (OnResumeCallback callback : onResumeCallbacks) {
            callback.onResume();
        }
    }

    /* package */ void onPause() {
        for (OnPauseCallback callback : onPauseCallbacks) {
            callback.onPause();
        }
    }

    /* package */ void onStop() {
        for (OnStopCallback callback : onStopCallbacks) {
            callback.onStop();
        }
    }

    /* package */ void onDestroy() {
        for (OnDestroyCallback callback : onDestroyCallbacks) {
            callback.onDestroy();
        }
    }

    /* package */ void onSaveInstanceState(Bundle outState) {
        for (OnSaveInstanceStateCallback callback : onSaveInstanceStateCallbacks) {
            callback.onSaveInstanceState(outState);
        }
    }

    /* package */ void onRestoreInstanceState(Bundle savedInstanceState) {
        for (OnRestoreInstanceStateCallback callback : onRestoreInstanceStateCallbacks) {
            callback.onRestoreInstanceState(savedInstanceState);
        }
    }

    /* package */ boolean dispatchKeyEvent(KeyEvent event) {
        boolean handled = false;
        for (DispatchKeyEventCallback callback : dispatchKeyEventCallbacks) {
            handled |= callback.dispatchKeyEvent(event);
        }
        return handled;
    }
}
