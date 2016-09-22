package net.kr9ly.brightfw.dependency.module.helper.dialog;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.DialogHelper;
import net.kr9ly.brightfw.helper.hook.HookHelper;

public interface DialogHelperDelegate {

    DialogHelper dialogHelper(HookHelper hookHelper, BundleHelper bundleHelper);
}
