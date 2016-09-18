package net.kr9ly.brightfw.dependency.module.event;

import net.kr9ly.rxeventbus.RxEventBus;

public interface EventBusDelegate {

    RxEventBus eventBus(EventBusHolder holder);
}
