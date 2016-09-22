package net.kr9ly.brightfw.dependency.module.helper;

import net.kr9ly.brightfw.dependency.scope.SingletonScope;
import net.kr9ly.brightfw.helper.hook.HookHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class HookHelperModule {

    @SingletonScope
    @Provides
    HookHelper hookHelper() {
        return new HookHelper();
    }
}
