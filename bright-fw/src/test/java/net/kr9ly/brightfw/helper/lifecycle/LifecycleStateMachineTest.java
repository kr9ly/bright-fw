package net.kr9ly.brightfw.helper.lifecycle;

import net.kr9ly.brightfw.app.LifecycleController;

import org.junit.Test;

import static org.junit.Assert.*;

import rx.observers.TestObserver;

public class LifecycleStateMachineTest {

    @Test
    public void testTransitionObservables() {
        TestObserver<Class<? extends LifecycleState>> createObserver = new TestObserver<>();
        TestObserver<Class<? extends LifecycleState>> startObserver = new TestObserver<>();
        TestObserver<Class<? extends LifecycleState>> resumeObserver = new TestObserver<>();
        TestObserver<Class<? extends LifecycleState>> pauseObserver = new TestObserver<>();
        TestObserver<Class<? extends LifecycleState>> stopObserver = new TestObserver<>();
        TestObserver<Class<? extends LifecycleState>> destroyObserver = new TestObserver<>();

        LifecycleController controller = new LifecycleController();
        LifecycleStateMachine stateMachine = new LifecycleStateMachine(controller);

        stateMachine.onCreateObservable().subscribe(createObserver);
        stateMachine.onStartObservable().subscribe(startObserver);
        stateMachine.onResumeObservable().subscribe(resumeObserver);
        stateMachine.onPauseObservable().subscribe(pauseObserver);
        stateMachine.onStopObservable().subscribe(stopObserver);
        stateMachine.onDestroyObservable().subscribe(destroyObserver);

        stateMachine.onCreate(null);
        assertEquals(1, createObserver.getOnNextEvents().size());
        assertEquals(0, startObserver.getOnNextEvents().size());
        assertEquals(0, resumeObserver.getOnNextEvents().size());
        assertEquals(0, pauseObserver.getOnNextEvents().size());
        assertEquals(0, stopObserver.getOnNextEvents().size());
        assertEquals(0, destroyObserver.getOnNextEvents().size());
        assertEquals(LifecycleState.Living.class, createObserver.getOnNextEvents().get(0));
        stateMachine.onStart();
        assertEquals(1, startObserver.getOnNextEvents().size());
        assertEquals(0, resumeObserver.getOnNextEvents().size());
        assertEquals(0, pauseObserver.getOnNextEvents().size());
        assertEquals(0, stopObserver.getOnNextEvents().size());
        assertEquals(0, destroyObserver.getOnNextEvents().size());
        assertEquals(LifecycleState.Visible.class, startObserver.getOnNextEvents().get(0));
        stateMachine.onResume();
        assertEquals(1, startObserver.getOnNextEvents().size());
        assertEquals(1, resumeObserver.getOnNextEvents().size());
        assertEquals(0, pauseObserver.getOnNextEvents().size());
        assertEquals(0, stopObserver.getOnNextEvents().size());
        assertEquals(0, destroyObserver.getOnNextEvents().size());
        assertEquals(LifecycleState.Running.class, resumeObserver.getOnNextEvents().get(0));
        stateMachine.onPause();
        assertEquals(1, startObserver.getOnNextEvents().size());
        assertEquals(1, pauseObserver.getOnNextEvents().size());
        assertEquals(LifecycleState.Visible.class, pauseObserver.getOnNextEvents().get(0));
        stateMachine.onResume();
        assertEquals(1, startObserver.getOnNextEvents().size());
        assertEquals(2, resumeObserver.getOnNextEvents().size());
        assertEquals(LifecycleState.Running.class, resumeObserver.getOnNextEvents().get(1));
        stateMachine.onPause();
        assertEquals(1, startObserver.getOnNextEvents().size());
        assertEquals(2, resumeObserver.getOnNextEvents().size());
        assertEquals(2, pauseObserver.getOnNextEvents().size());
        assertEquals(LifecycleState.Visible.class, pauseObserver.getOnNextEvents().get(1));
        stateMachine.onStop();
        assertEquals(1, startObserver.getOnNextEvents().size());
        assertEquals(2, resumeObserver.getOnNextEvents().size());
        assertEquals(2, pauseObserver.getOnNextEvents().size());
        assertEquals(1, stopObserver.getOnNextEvents().size());
        assertEquals(0, destroyObserver.getOnNextEvents().size());
        assertEquals(LifecycleState.Living.class, stopObserver.getOnNextEvents().get(0));
        stateMachine.onDestroy();
        assertEquals(1, destroyObserver.getOnNextEvents().size());
        assertEquals(LifecycleState.Destroyed.class, destroyObserver.getOnNextEvents().get(0));
    }
}
