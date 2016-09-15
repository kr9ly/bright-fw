package net.kr9ly.brightfw.dependency.module.helper;

import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.brightfw.helper.transition.ArgumentsHelper;
import net.kr9ly.brightfw.helper.transition.BundleHelper;
import net.kr9ly.brightfw.helper.transition.IntentHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class ArgumentsHelperModule {

    private ArgumentsHelperDelegate delegate;

    public ArgumentsHelperModule(ArgumentsHelperDelegate delegate) {
        this.delegate = delegate;
    }

    @MainScope
    @Provides
    ArgumentsHelper argumentsHelper(
            IntentHelper intentHelper,
            BundleHelper bundleHelper
    ) {
        return delegate.argumentsHelper(intentHelper, bundleHelper);
    }
}
