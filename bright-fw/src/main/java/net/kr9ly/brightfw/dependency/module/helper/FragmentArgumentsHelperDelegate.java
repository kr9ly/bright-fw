package net.kr9ly.brightfw.dependency.module.helper;

import android.app.Fragment;

import net.kr9ly.brightfw.helper.transition.ArgumentsHelper;
import net.kr9ly.brightfw.helper.transition.BundleHelper;
import net.kr9ly.brightfw.helper.transition.FragmentArgumentsHelper;
import net.kr9ly.brightfw.helper.transition.IntentHelper;

public class FragmentArgumentsHelperDelegate implements ArgumentsHelperDelegate {

    private Fragment fragment;

    public FragmentArgumentsHelperDelegate(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public ArgumentsHelper argumentsHelper(IntentHelper intentHelper, BundleHelper bundleHelper) {
        return new FragmentArgumentsHelper(fragment, bundleHelper);
    }
}
