package net.kr9ly.brightfw.helper.transition;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.helper.intent.IntentBuilder;
import net.kr9ly.brightfw.helper.intent.IntentHelper;
import net.kr9ly.brightfw.helper.transition.action.ActivityTransitionAction;

public class ActivityTransitionHelper implements TransitionHelper {

    private FragmentActivity activity;

    private IntentHelper intentHelper;

    public ActivityTransitionHelper(FragmentActivity activity, IntentHelper intentHelper) {
        this.activity = activity;
        this.intentHelper = intentHelper;
    }

    @Override
    public void startActivity(IntentBuilder builder, ActivityTransitionAction action) {
        Intent intent = intentHelper.build(builder);
        intent.putExtra(EXTRA_KEY_TRANSITION_ACTION, action);
        action.forward(activity, intent);
    }

    @Override
    public void finish() {
        activity.finish();
    }
}
