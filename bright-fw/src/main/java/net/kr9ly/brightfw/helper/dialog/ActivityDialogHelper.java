package net.kr9ly.brightfw.helper.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.action.animation.AnimationAction;
import net.kr9ly.brightfw.helper.hook.Hook;
import net.kr9ly.brightfw.helper.hook.HookHelper;

public class ActivityDialogHelper implements DialogHelper {

    private Hook hook;

    private FragmentActivity activity;

    private BundleHelper bundleHelper;

    public ActivityDialogHelper(HookHelper hookHelper, FragmentActivity activity, BundleHelper bundleHelper) {
        this.hook = hookHelper.of(DialogHelper.class);
        this.activity = activity;
        this.bundleHelper = bundleHelper;
    }

    @Override
    public void show(Object arguments, AnimationAction action) {
        DialogFragment dialog = DialogUtils.build(arguments);
        dialog.setArguments(bundleHelper.build(arguments));
        dialog.getArguments().putSerializable(ARGUMENT_KEY_ANIMATION_ACTION, action);
        dialog.show(activity.getSupportFragmentManager(), DialogUtils.getTag(arguments));
        hook.hook("show_dialog", action, arguments);
    }
}
