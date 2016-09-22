package net.kr9ly.brightfw.helper.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.action.animation.AnimationAction;
import net.kr9ly.brightfw.helper.hook.Hook;
import net.kr9ly.brightfw.helper.hook.HookHelper;

public class FragmentDialogHelper implements DialogHelper {

    private Hook hook;

    private Fragment fragment;

    private BundleHelper bundleHelper;

    public FragmentDialogHelper(HookHelper hookHelper, Fragment fragment, BundleHelper bundleHelper) {
        this.hook = hookHelper.of(DialogHelper.class);
        this.fragment = fragment;
        this.bundleHelper = bundleHelper;
    }

    @Override
    public void show(Object arguments, AnimationAction action) {
        DialogFragment dialog = DialogUtils.build(arguments);
        dialog.setArguments(bundleHelper.build(arguments));
        dialog.getArguments().putSerializable(ARGUMENT_KEY_ANIMATION_ACTION, action);
        dialog.show(fragment.getChildFragmentManager(), DialogUtils.getTag(arguments));
        hook.hook("show_dialog", action, arguments);
    }
}
