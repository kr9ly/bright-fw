package net.kr9ly.brightfw.dependency.module.event;

import net.kr9ly.brightfw.dependency.scope.SingletonScope;
import net.kr9ly.rxeventbus.RxEventBus;

import java.util.WeakHashMap;

import javax.inject.Inject;

@SingletonScope
public class EventBusHolder {

    private WeakHashMap<Object, RxEventBus> cache = new WeakHashMap<>();

    @Inject
    public EventBusHolder() {

    }

    public RxEventBus findOrCreate(Object target, Object parent) {
        RxEventBus bus = cache.get(target);
        if (bus == null) {
            bus = parent != null ? new RxEventBus(findOrCreate(parent, null)) : new RxEventBus();
            cache.put(target, bus);
        }
        return bus;
    }
}
