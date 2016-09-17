package net.kr9ly.brightfw.dependency.module.helper.arguments;

import android.support.v4.app.Fragment;

import net.kr9ly.brightfw.helper.arguments.ArgumentsHelper;
import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.arguments.FragmentArgumentsHelper;
import net.kr9ly.brightfw.helper.intent.IntentHelper;

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
