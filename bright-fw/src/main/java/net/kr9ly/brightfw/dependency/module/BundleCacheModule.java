package net.kr9ly.brightfw.dependency.module;

import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.brightfw.driver.cache.BundleCache;
import net.kr9ly.brightfw.driver.cache.BundleCacheSettings;

import dagger.Module;
import dagger.Provides;

@Module
public class BundleCacheModule {

    @MainScope
    @Provides
    BundleCache bundleCache(
            BundleCacheSettings settings
    ) {
        return new BundleCache(settings);
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
