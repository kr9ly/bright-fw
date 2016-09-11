package net.kr9ly.brightfw.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.dependency.component.LifecycleComponent;
import net.kr9ly.brightfw.dependency.lifecycle.OnCreateCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnDestroyCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnPauseCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnResumeCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnSaveInstanceStateCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStartCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStopCallback;

public abstract class BrightActivity<SingletonComponent, MainComponent extends LifecycleComponent> extends FragmentActivity {

    private MainComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareComponent(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private void prepareComponent(Bundle savedInstanceState) {
        BrightApplication<SingletonComponent> application = (BrightApplication<SingletonComponent>) getApplication();
        component = buildComponent(application.getComponent());
        componentCreated(component);

        for (OnCreateCallback callback : component.onCreateCallbacks()) {
            callback.onCreate(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (OnSaveInstanceStateCallback callback : component.onSaveInstanceStateCallbacks()) {
            callback.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        for (OnStartCallback callback : component.onStartCallbacks()) {
            callback.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (OnResumeCallback callback : component.onResumeCallbacks()) {
            callback.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (OnPauseCallback callback : component.onPauseCallbacks()) {
            callback.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        for (OnStopCallback callback : component.onStopCallbacks()) {
            callback.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        for (OnDestroyCallback callback : component.onDestroyCallbacks()) {
            callback.onDestroy();
        }
    }

    protected abstract MainComponent buildComponent(SingletonComponent singletonComponent);

    protected abstract void componentCreated(MainComponent component);
}
