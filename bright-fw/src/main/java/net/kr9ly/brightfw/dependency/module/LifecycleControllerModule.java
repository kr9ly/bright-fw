package net.kr9ly.brightfw.dependency.module;

import net.kr9ly.brightfw.app.LifecycleController;
import net.kr9ly.brightfw.dependency.scope.MainScope;

import dagger.Module;
import dagger.Provides;

@Module
public class LifecycleControllerModule {

    @MainScope
    @Provides
    LifecycleController lifecycleController() {
        return new LifecycleController();
    }
}
