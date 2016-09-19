package net.kr9ly.brightfw.helper.image;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ActivityGlideImageHelper implements ImageHelper {

    private FragmentActivity activity;

    public ActivityGlideImageHelper(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void load(ImageView imageView, Uri uri) {
        Glide.with(activity)
                .load(uri)
                .into(imageView);
    }
}
