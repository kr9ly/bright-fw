package net.kr9ly.brightfw.helper.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.action.animation.AnimationAction;

public class FragmentDialogHelper implements DialogHelper {

    private Fragment fragment;

    private BundleHelper bundleHelper;

    public FragmentDialogHelper(Fragment fragment, BundleHelper bundleHelper) {
        this.fragment = fragment;
        this.bundleHelper = bundleHelper;
    }

    @Override
    public void show(Object arguments, AnimationAction action) {
        DialogFragment dialog = DialogUtils.build(arguments);
        dialog.setArguments(bundleHelper.build(arguments));
        dialog.getArguments().putSerializable(ARGUMENT_KEY_ANIMATION_ACTION, action);
        dialog.show(fragment.getChildFragmentManager(), DialogUtils.getTag(arguments));
    }
}
