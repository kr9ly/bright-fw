package net.kr9ly.brightfw.dependency.module.helper;

import net.kr9ly.brightfw.dependency.module.helper.image.ImageHelperDelegate;
import net.kr9ly.brightfw.dependency.scope.MainScope;
import net.kr9ly.brightfw.helper.hook.HookHelper;
import net.kr9ly.brightfw.helper.image.ImageHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageHelperModule {

    private ImageHelperDelegate delegate;

    public ImageHelperModule(ImageHelperDelegate delegate) {
        this.delegate = delegate;
    }

    @MainScope
    @Provides
    ImageHelper imageHelper(HookHelper hookHelper) {
        return delegate.imageHelper(hookHelper);
    }
}
