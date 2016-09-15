package net.kr9ly.brightfw.dependency.module.helper;

import android.content.Context;

import net.kr9ly.brightfw.dependency.scope.SingletonScope;
import net.kr9ly.brightfw.helper.transition.IntentHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class IntentHelperModule {

    private Context context;

    public IntentHelperModule(Context context) {
        this.context = context;
    }

    @SingletonScope
    @Provides
    IntentHelper intentHelper() {
        return new IntentHelper(context);
    }
}
