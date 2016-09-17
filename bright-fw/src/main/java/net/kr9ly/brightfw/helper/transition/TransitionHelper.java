package net.kr9ly.brightfw.helper.transition;

import net.kr9ly.brightfw.helper.transition.action.ActivityTransitionAction;

public interface TransitionHelper {

    String EXTRA_KEY_TRANSITION_ACTION = "TRANSITION_ACTION_INSTANCE";

    void startActivity(Object arguments, ActivityTransitionAction action);

    void finish();
}
