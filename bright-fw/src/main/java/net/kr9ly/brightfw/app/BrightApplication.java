package net.kr9ly.brightfw.app;

import android.app.Application;

public abstract class BrightApplication<SingletonComponent> extends Application {

    private SingletonComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = buildComponent();
    }

    public SingletonComponent getComponent() {
        return component;
    }

    protected abstract SingletonComponent buildComponent();
}
