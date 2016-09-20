package net.kr9ly.brightfw.helper.transition;

import net.kr9ly.brightfw.helper.intent.IntentBuilder;
import net.kr9ly.brightfw.helper.transition.action.ActivityTransitionAction;

public interface TransitionHelper {

    String EXTRA_KEY_TRANSITION_ACTION = "TRANSITION_ACTION_INSTANCE";

    void startActivity(IntentBuilder builder, ActivityTransitionAction action);

    void finish();
}
