package net.kr9ly.brightfw.helper.intent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class UriIntentBuilder implements IntentBuilder {

    public static UriIntentBuilder of(Uri uri) {
        return new UriIntentBuilder(uri);
    }

    private Uri uri;

    protected UriIntentBuilder(Uri uri) {
        this.uri = uri;
    }

    @Override
    public Intent build(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}
