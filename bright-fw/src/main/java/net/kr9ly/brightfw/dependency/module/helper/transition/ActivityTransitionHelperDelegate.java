package net.kr9ly.brightfw.dependency.module.helper.transition;

import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.helper.intent.IntentHelper;
import net.kr9ly.brightfw.helper.transition.ActivityTransitionHelper;
import net.kr9ly.brightfw.helper.transition.TransitionHelper;

public class ActivityTransitionHelperDelegate implements TransitionHelperDelegate {

    private FragmentActivity activity;

    public ActivityTransitionHelperDelegate(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public TransitionHelper transitionHelper(IntentHelper intentHelper) {
        return new ActivityTransitionHelper(activity, intentHelper);
    }
}
