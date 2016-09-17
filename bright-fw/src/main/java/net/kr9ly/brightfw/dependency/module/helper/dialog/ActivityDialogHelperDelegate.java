package net.kr9ly.brightfw.dependency.module.helper.dialog;

import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.ActivityDialogHelper;
import net.kr9ly.brightfw.helper.dialog.DialogHelper;

public class ActivityDialogHelperDelegate implements DialogHelperDelegate {

    private FragmentActivity activity;

    public ActivityDialogHelperDelegate(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public DialogHelper dialogHelper(BundleHelper bundleHelper) {
        return new ActivityDialogHelper(activity, bundleHelper);
    }
}
