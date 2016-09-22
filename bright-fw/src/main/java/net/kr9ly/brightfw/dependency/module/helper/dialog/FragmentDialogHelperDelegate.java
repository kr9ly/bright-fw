package net.kr9ly.brightfw.dependency.module.helper.dialog;

import android.support.v4.app.Fragment;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.DialogHelper;
import net.kr9ly.brightfw.helper.dialog.FragmentDialogHelper;
import net.kr9ly.brightfw.helper.hook.HookHelper;

public class FragmentDialogHelperDelegate implements DialogHelperDelegate {

    private Fragment fragment;

    public FragmentDialogHelperDelegate(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public DialogHelper dialogHelper(HookHelper hookHelper, BundleHelper bundleHelper) {
        return new FragmentDialogHelper(hookHelper, fragment, bundleHelper);
    }
}
