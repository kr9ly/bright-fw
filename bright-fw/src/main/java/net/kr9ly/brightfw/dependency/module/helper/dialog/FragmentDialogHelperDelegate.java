package net.kr9ly.brightfw.dependency.module.helper.dialog;

import android.support.v4.app.Fragment;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.DialogHelper;
import net.kr9ly.brightfw.helper.dialog.FragmentDialogHelper;

public class FragmentDialogHelperDelegate implements DialogHelperDelegate {

    private Fragment fragment;

    public FragmentDialogHelperDelegate(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public DialogHelper dialogHelper(BundleHelper bundleHelper) {
        return new FragmentDialogHelper(fragment, bundleHelper);
    }
}
