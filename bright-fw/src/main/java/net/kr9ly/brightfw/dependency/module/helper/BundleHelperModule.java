package net.kr9ly.brightfw.dependency.module.helper;

import android.content.Context;

import net.kr9ly.brightfw.dependency.scope.SingletonScope;
import net.kr9ly.brightfw.helper.bundle.BundleHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class BundleHelperModule {

    private Context context;

    public BundleHelperModule(Context context) {
        this.context = context;
    }

    @SingletonScope
    @Provides
    public BundleHelper bundleHelper() {
        return new BundleHelper(context);
    }
}
