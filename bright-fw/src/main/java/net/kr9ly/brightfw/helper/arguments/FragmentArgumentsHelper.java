package net.kr9ly.brightfw.helper.arguments;

import android.support.v4.app.Fragment;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;

public class FragmentArgumentsHelper implements ArgumentsHelper {

    private Fragment fragment;

    private BundleHelper bundleHelper;

    public FragmentArgumentsHelper(Fragment fragment, BundleHelper bundleHelper) {
        this.fragment = fragment;
        this.bundleHelper = bundleHelper;
    }

    @Override
    public <T> T getArguments(Class<T> argumentsClass) {
        return bundleHelper.restore(fragment.getArguments(), argumentsClass);
    }
}
