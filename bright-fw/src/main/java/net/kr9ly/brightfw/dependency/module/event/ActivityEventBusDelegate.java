package net.kr9ly.brightfw.dependency.module.event;

import android.app.Activity;

import net.kr9ly.rxeventbus.RxEventBus;

public class ActivityEventBusDelegate implements EventBusDelegate {

    private Activity activity;

    public ActivityEventBusDelegate(Activity activity) {
        this.activity = activity;
    }

    @Override
    public RxEventBus eventBus(EventBusHolder holder) {
        return holder.findOrCreate(activity, activity.getApplication());
    }
}
