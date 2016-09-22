package net.kr9ly.brightfw.helper.transition;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.helper.hook.Hook;
import net.kr9ly.brightfw.helper.hook.HookHelper;
import net.kr9ly.brightfw.helper.intent.IntentBuilder;
import net.kr9ly.brightfw.helper.intent.IntentHelper;
import net.kr9ly.brightfw.helper.transition.action.ActivityTransitionAction;

public class FragmentTransitionHelper implements TransitionHelper {

    private Hook hook;

    private Fragment fragment;

    private IntentHelper intentHelper;

    public FragmentTransitionHelper(HookHelper hookHelper, Fragment fragment, IntentHelper intentHelper) {
        hook = hookHelper.of(TransitionHelper.class);
        this.fragment = fragment;
        this.intentHelper = intentHelper;
    }

    @Override
    public void startActivity(IntentBuilder builder, ActivityTransitionAction action) {
        Intent intent = intentHelper.build(builder);
        intent.putExtra(EXTRA_KEY_TRANSITION_ACTION, action);
        hook.hook("start_activity", fragment.getActivity(), action, intent);
        action.forward(fragment.getActivity(), intent);
    }

    @Override
    public void finish() {
        hook.hook("finish_activity", fragment.getActivity());
        fragment.getActivity().finish();
    }
}
