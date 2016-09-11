package net.kr9ly.brightfw.dependency.component;

import net.kr9ly.brightfw.dependency.lifecycle.OnCreateCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnDestroyCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnPauseCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnResumeCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnSaveInstanceStateCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStartCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStopCallback;

import java.util.Set;

public interface LifecycleComponent {

    Set<OnCreateCallback> onCreateCallbacks();

    Set<OnStartCallback> onStartCallbacks();

    Set<OnResumeCallback> onResumeCallbacks();

    Set<OnPauseCallback> onPauseCallbacks();

    Set<OnStopCallback> onStopCallbacks();

    Set<OnDestroyCallback> onDestroyCallbacks();

    Set<OnSaveInstanceStateCallback> onSaveInstanceStateCallbacks();
}
