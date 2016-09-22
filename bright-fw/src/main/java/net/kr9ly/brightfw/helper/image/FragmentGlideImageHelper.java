package net.kr9ly.brightfw.helper.image;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.kr9ly.brightfw.helper.hook.Hook;
import net.kr9ly.brightfw.helper.hook.HookHelper;

public class FragmentGlideImageHelper implements ImageHelper {

    private Hook hook;

    private Fragment fragment;

    public FragmentGlideImageHelper(HookHelper hookHelper, Fragment fragment) {
        this.hook = hookHelper.of(ImageHelper.class);
        this.fragment = fragment;
    }

    @Override
    public void load(ImageView imageView, Uri uri) {
        hook.hook("start_image_load", uri);
        Glide.with(fragment)
                .load(uri)
                .into(imageView);
    }
}
