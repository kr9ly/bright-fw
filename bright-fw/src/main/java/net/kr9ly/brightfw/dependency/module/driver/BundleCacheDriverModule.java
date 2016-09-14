package net.kr9ly.brightfw.dependency.module.driver;

import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.brightfw.driver.cache.BundleCacheDriver;
import net.kr9ly.brightfw.driver.cache.BundleCacheSettings;

import dagger.Module;
import dagger.Provides;

@Module
public class BundleCacheDriverModule {

    @MainScope
    @Provides
    BundleCacheDriver bundleCacheDriver(
            BundleCacheSettings settings
    ) {
        return new BundleCacheDriver(settings);
    }

    @MainScope
    @Provides
    BundleCacheSettings defaultBundleCacheSettings() {
        return new BundleCacheSettings() {
            @Override
            public String bundleName() {
                return "BUNDLE_CACHE";
            }
        };
    }
}
