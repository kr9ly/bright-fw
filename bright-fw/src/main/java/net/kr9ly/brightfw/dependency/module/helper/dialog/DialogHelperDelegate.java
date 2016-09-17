package net.kr9ly.brightfw.dependency.module.helper.dialog;

import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.DialogHelper;

public interface DialogHelperDelegate {

    DialogHelper dialogHelper(BundleHelper bundleHelper);
}
