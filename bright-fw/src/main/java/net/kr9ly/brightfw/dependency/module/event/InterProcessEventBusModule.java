package net.kr9ly.brightfw.dependency.module.event;

import android.content.Context;

import net.kr9ly.brightfw.dependency.scope.SingletonScope;
import net.kr9ly.brightfw.event.InterProcessEventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class InterProcessEventBusModule {

    private Context context;

    public InterProcessEventBusModule(Context context) {
        this.context = context;
    }

    @SingletonScope
    @Provides
    InterProcessEventBus interProcessEventBus() {
        return new InterProcessEventBus(context);
    }
}
