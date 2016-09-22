package net.kr9ly.brightfw.dependency.module.hook;

import net.kr9ly.brightfw.dependency.scope.SingletonScope;
import net.kr9ly.brightfw.helper.hook.HookHelper;
import net.kr9ly.brightfw.hook.log.LogHook;

import dagger.Module;
import dagger.Provides;

@Module
public class LogHookModule {

    @SingletonScope
    @Provides
    LogHook logHook(HookHelper hookHelper) {
        return new LogHook(hookHelper);
    }
}
