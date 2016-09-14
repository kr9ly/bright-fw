package net.kr9ly.brightfw.app;

import android.os.Bundle;

import net.kr9ly.brightfw.dependency.lifecycle.OnCreateCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnDestroyCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnPauseCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnResumeCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnSaveInstanceStateCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStartCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStopCallback;

import java.util.LinkedList;
import java.util.List;

public class LifecycleController {

    private final List<OnCreateCallback> onCreateCallbacks = new LinkedList<>();

    private final List<OnStartCallback> onStartCallbacks = new LinkedList<>();

    private final List<OnResumeCallback> onResumeCallbacks = new LinkedList<>();

    private final List<OnPauseCallback> onPauseCallbacks = new LinkedList<>();

    private final List<OnStopCallback> onStopCallbacks = new LinkedList<>();

    private final List<OnDestroyCallback> onDestroyCallbacks = new LinkedList<>();

    private final List<OnSaveInstanceStateCallback> onSaveInstanceStateCallbacks = new LinkedList<>();

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
}
