package net.kr9ly.brightfw.dependency.module.event;

import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.rxeventbus.RxEventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class EventBusModule {

    private EventBusDelegate delegate;

    public EventBusModule(EventBusDelegate delegate) {
        this.delegate = delegate;
    }

    @MainScope
    @Provides
    RxEventBus eventBus(EventBusHolder holder) {
        return delegate.eventBus(holder);
    }
}
