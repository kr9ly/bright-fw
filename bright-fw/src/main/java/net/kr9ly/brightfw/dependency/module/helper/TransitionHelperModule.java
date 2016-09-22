package net.kr9ly.brightfw.dependency.module.helper;

import net.kr9ly.brightfw.dependency.module.helper.transition.TransitionHelperDelegate;
import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.brightfw.helper.hook.HookHelper;
import net.kr9ly.brightfw.helper.intent.IntentHelper;
import net.kr9ly.brightfw.helper.transition.TransitionHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class TransitionHelperModule {

    private TransitionHelperDelegate delegate;

    public TransitionHelperModule(TransitionHelperDelegate delegate) {
        this.delegate = delegate;
    }

    @MainScope
    @Provides
    TransitionHelper transitionHelper(
            HookHelper hookHelper,
            IntentHelper intentHelper
    ) {
        return delegate.transitionHelper(hookHelper, intentHelper);
    }
}
