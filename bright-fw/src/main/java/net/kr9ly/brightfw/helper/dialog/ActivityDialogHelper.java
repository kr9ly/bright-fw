package net.kr9ly.brightfw.helper.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.action.animation.AnimationAction;

public class ActivityDialogHelper implements DialogHelper {

    private FragmentActivity activity;

    private BundleHelper bundleHelper;

    public ActivityDialogHelper(FragmentActivity activity, BundleHelper bundleHelper) {
        this.activity = activity;
        this.bundleHelper = bundleHelper;
    }

    @Override
    public void show(Object arguments, AnimationAction action) {
        DialogFragment dialog = DialogUtils.build(arguments);
        dialog.setArguments(bundleHelper.build(arguments));
        dialog.getArguments().putSerializable(ARGUMENT_KEY_ANIMATION_ACTION, action);
        dialog.show(activity.getSupportFragmentManager(), DialogUtils.getTag(arguments));
    }
}
