package net.kr9ly.brightfw.dependency.module.helper;

import net.kr9ly.brightfw.dependency.module.helper.arguments.ArgumentsHelperDelegate;
import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.brightfw.helper.arguments.ArgumentsHelper;
import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.intent.IntentHelper;

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
