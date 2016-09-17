package net.kr9ly.brightfw.helper.transition.action;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.helper.transition.action.animation.AnimationAction;
import net.kr9ly.brightfw.helper.transition.action.animation.DefaultAnimationAction;

public class DefaultTransitionAction implements ActivityTransitionAction {

    private AnimationAction animationAction;

    public DefaultTransitionAction() {
        this(new DefaultAnimationAction());
    }

    public DefaultTransitionAction(AnimationAction animationAction) {
        this.animationAction = animationAction;
    }

    @Override
    public void forward(FragmentActivity activity, Intent intent) {
        animationAction.forward(activity);
        activity.startActivity(intent);
    }

    @Override
    public void back(FragmentActivity activity, Intent intent) {
        animationAction.back(activity);
    }
}
