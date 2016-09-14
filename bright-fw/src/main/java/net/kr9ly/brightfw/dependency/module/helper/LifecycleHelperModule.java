package net.kr9ly.brightfw.dependency.module.helper;

import net.kr9ly.brightfw.app.LifecycleController;
import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.brightfw.helper.lifecycle.LifecycleHelper;
import net.kr9ly.brightfw.helper.lifecycle.LifecycleStateMachine;

import dagger.Module;
import dagger.Provides;

@Module
public class LifecycleHelperModule {

    @MainScope
    @Provides
    LifecycleStateMachine lifecycleStateMachine(
            LifecycleController lifecycleController
    ) {
        return new LifecycleStateMachine(lifecycleController);
    }

    @MainScope
    @Provides
    LifecycleHelper lifecycleHelper(
            LifecycleStateMachine stateMachine
    ) {
        return new LifecycleHelper(stateMachine);
    }
}
