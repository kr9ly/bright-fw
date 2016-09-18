package net.kr9ly.brightfw.dependency.module.event;

import net.kr9ly.brightfw.dependency.scope.SingletonScope;

import dagger.Module;
import dagger.Provides;

@Module
public class EventBusHolderModule {

    @SingletonScope
    @Provides
    EventBusHolder eventBusHolder() {
        return new EventBusHolder();
    }
}
