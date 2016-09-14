package net.kr9ly.brightfw.helper.lifecycle;

import net.kr9ly.rxstatemachine.RxStateBinder;

import rx.Observable;

public class LifecycleHelper {

    private LifecycleStateMachine stateMachine;

    private RxStateBinder<LifecycleState> binder;

    public LifecycleHelper(LifecycleStateMachine stateMachine) {
        binder = new RxStateBinder<>(stateMachine);
    }

    public <T> Observable.Transformer<? super T, ? extends T> bindOnCreate() {
        return bind(LifecycleState.Living.class, LifecycleState.Destroyed.class);
    }

    public <T> Observable.Transformer<? super T, ? extends T> bindOnStart() {
        return bind(LifecycleState.Visible.class, LifecycleState.Living.class);
    }

    public <T> Observable.Transformer<? super T, ? extends T> bindOnResume() {
        return bind(LifecycleState.Running.class, LifecycleState.Visible.class);
    }

    public <T> Observable.Transformer<? super T, ? extends T> bind(Class<? extends LifecycleState> start, Class<? extends LifecycleState> end) {
        return binder.bindToState(start, end);
    }
}