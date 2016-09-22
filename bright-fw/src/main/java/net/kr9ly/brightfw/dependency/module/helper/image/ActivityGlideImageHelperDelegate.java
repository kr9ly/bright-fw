package net.kr9ly.brightfw.dependency.module.helper.image;

import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.helper.hook.HookHelper;
import net.kr9ly.brightfw.helper.image.ActivityGlideImageHelper;
import net.kr9ly.brightfw.helper.image.ImageHelper;

public class ActivityGlideImageHelperDelegate implements ImageHelperDelegate {

    private FragmentActivity activity;

    public ActivityGlideImageHelperDelegate(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public ImageHelper imageHelper(HookHelper hookHelper) {
        return new ActivityGlideImageHelper(hookHelper, activity);
    }
}
