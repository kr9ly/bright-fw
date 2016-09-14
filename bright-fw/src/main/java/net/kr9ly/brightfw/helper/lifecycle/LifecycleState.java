package net.kr9ly.brightfw.helper.lifecycle;

public interface LifecycleState {

    interface Default extends LifecycleState {
        Living create();
    }

    interface Living extends LifecycleState {
        Visible start();

        Destroyed destroy();
    }

    interface Visible extends LifecycleState {
        Running resume();

        Living stop();
    }

    interface Running extends LifecycleState {
        Visible pause();
    }

    interface Destroyed extends LifecycleState {

    }
}