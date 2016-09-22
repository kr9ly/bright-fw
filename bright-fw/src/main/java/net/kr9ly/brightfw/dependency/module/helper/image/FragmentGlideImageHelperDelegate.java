package net.kr9ly.brightfw.dependency.module.helper.image;

import android.support.v4.app.Fragment;

import net.kr9ly.brightfw.helper.hook.HookHelper;
import net.kr9ly.brightfw.helper.image.FragmentGlideImageHelper;
import net.kr9ly.brightfw.helper.image.ImageHelper;

public class FragmentGlideImageHelperDelegate implements ImageHelperDelegate {

    private Fragment fragment;

    public FragmentGlideImageHelperDelegate(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public ImageHelper imageHelper(HookHelper hookHelper) {
        return new FragmentGlideImageHelper(hookHelper, fragment);
    }
}
