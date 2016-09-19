package net.kr9ly.brightfw.helper.image;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FragmentGlideImageHelper implements ImageHelper {

    private Fragment fragment;

    public FragmentGlideImageHelper(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void load(ImageView imageView, Uri uri) {
        Glide.with(fragment)
                .load(uri)
                .into(imageView);
    }
}
