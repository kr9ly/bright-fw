package net.kr9ly.brightfw.dependency.module.event;

import android.support.v4.app.Fragment;

import net.kr9ly.rxeventbus.RxEventBus;

public class FragmentEventBusDelegate implements EventBusDelegate {

    private Fragment fragment;

    public FragmentEventBusDelegate(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public RxEventBus eventBus(EventBusHolder holder) {
        return holder.findOrCreate(fragment, fragment.getParentFragment() != null ? fragment.getParentFragment() : fragment.getActivity());
    }
}
