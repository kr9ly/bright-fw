package net.kr9ly.brightfw.helper.transition;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.helper.intent.IntentBuilder;
import net.kr9ly.brightfw.helper.intent.IntentHelper;
import net.kr9ly.brightfw.helper.transition.action.ActivityTransitionAction;

public class FragmentTransitionHelper implements TransitionHelper {

    private Fragment fragment;

    private IntentHelper intentHelper;

    public FragmentTransitionHelper(Fragment fragment, IntentHelper intentHelper) {
        this.fragment = fragment;
        this.intentHelper = intentHelper;
    }

    @Override
    public void startActivity(IntentBuilder builder, ActivityTransitionAction action) {
        Intent intent = intentHelper.build(builder);
        intent.putExtra(EXTRA_KEY_TRANSITION_ACTION, action);
        action.forward(fragment.getActivity(), intent);
    }

    @Override
    public void finish() {
        fragment.getActivity().finish();
    }
}
