package net.kr9ly.brightfw.dependency.module.helper;

import net.kr9ly.brightfw.dependency.module.helper.dialog.DialogHelperDelegate;
import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.dialog.DialogHelper;
import net.kr9ly.brightfw.helper.hook.HookHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class DialogHelperModule {

    private DialogHelperDelegate delegate;

    public DialogHelperModule(DialogHelperDelegate delegate) {
        this.delegate = delegate;
    }

    @MainScope
    @Provides
    DialogHelper dialogHelper(
            HookHelper hookHelper,
            BundleHelper bundleHelper
    ) {
        return delegate.dialogHelper(hookHelper, bundleHelper);
    }
}
