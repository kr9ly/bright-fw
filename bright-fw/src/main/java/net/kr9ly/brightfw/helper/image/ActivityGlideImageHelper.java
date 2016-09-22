package net.kr9ly.brightfw.helper.image;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.kr9ly.brightfw.helper.hook.Hook;
import net.kr9ly.brightfw.helper.hook.HookHelper;

public class ActivityGlideImageHelper implements ImageHelper {

    private Hook hook;

    private FragmentActivity activity;

    public ActivityGlideImageHelper(HookHelper hookHelper, FragmentActivity activity) {
        this.hook = hookHelper.of(ImageHelper.class);
        this.activity = activity;
    }

    @Override
    public void load(ImageView imageView, Uri uri) {
        hook.hook("start_image_load", uri);
        Glide.with(activity)
                .load(uri)
                .into(imageView);
    }
}
