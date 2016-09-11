package net.kr9ly.brightfw.app;

import android.app.Fragment;
import android.os.Bundle;

import net.kr9ly.brightfw.dependency.component.LifecycleComponent;
import net.kr9ly.brightfw.dependency.lifecycle.OnCreateCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnDestroyCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnPauseCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnResumeCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnSaveInstanceStateCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStartCallback;
import net.kr9ly.brightfw.dependency.lifecycle.OnStopCallback;

public abstract class BrightFragment<SingletonComponent, MainComponent extends LifecycleComponent> extends Fragment {

    private MainComponent component;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareComponent(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private void prepareComponent(Bundle savedInstanceState) {
        BrightApplication<SingletonComponent> application = (BrightApplication<SingletonComponent>) getActivity().getApplication();
        component = buildComponent(application.getComponent());
        componentCreated(component);

        for (OnCreateCallback callback : component.onCreateCallbacks()) {
            callback.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (OnSaveInstanceStateCallback callback : component.onSaveInstanceStateCallbacks()) {
            callback.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        for (OnStartCallback callback : component.onStartCallbacks()) {
            callback.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        for (OnResumeCallback callback : component.onResumeCallbacks()) {
            callback.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        for (OnPauseCallback callback : component.onPauseCallbacks()) {
            callback.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        for (OnStopCallback callback : component.onStopCallbacks()) {
            callback.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        for (OnDestroyCallback callback : component.onDestroyCallbacks()) {
            callback.onDestroy();
        }
    }

    protected abstract MainComponent buildComponent(SingletonComponent singletonComponent);

    protected abstract void componentCreated(MainComponent component);
}
