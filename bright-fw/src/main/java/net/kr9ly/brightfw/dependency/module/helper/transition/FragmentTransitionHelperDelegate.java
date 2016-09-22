package net.kr9ly.brightfw.dependency.module.helper.transition;

import android.support.v4.app.Fragment;

import net.kr9ly.brightfw.helper.hook.HookHelper;
import net.kr9ly.brightfw.helper.intent.IntentHelper;
import net.kr9ly.brightfw.helper.transition.FragmentTransitionHelper;
import net.kr9ly.brightfw.helper.transition.TransitionHelper;

public class FragmentTransitionHelperDelegate implements TransitionHelperDelegate {

    private Fragment fragment;

    public FragmentTransitionHelperDelegate(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public TransitionHelper transitionHelper(HookHelper hookHelper, IntentHelper intentHelper) {
        return new FragmentTransitionHelper(hookHelper, fragment, intentHelper);
    }
}
