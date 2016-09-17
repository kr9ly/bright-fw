package net.kr9ly.brightfw.helper.bundle;

import android.content.Context;
import android.os.Bundle;

public class BundleHelper {

    private Context context;

    public BundleHelper(Context context) {
        this.context = context;
    }

    public Bundle build(Object arguments) {
        return BundleConverter.buildBundle(arguments);
    }

    public <T> T restore(Bundle bundle, Class<T> argumentsClass) {
        return BundleConverter.restoreFromBundle(context, bundle, argumentsClass);
    }
}
