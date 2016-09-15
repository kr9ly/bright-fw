package net.kr9ly.brightfw.helper.transition;

import android.content.Context;
import android.content.Intent;

public class IntentHelper {

    private Context context;

    public IntentHelper(Context context) {
        this.context = context;
    }

    public Intent build(Object arguments) {
        return IntentConverter.buildIntent(context, arguments);
    }

    public <T> T restore(Intent intent, Class<T> argumentsClass) {
        return IntentConverter.restoreFromIntent(context, intent, argumentsClass);
    }
}
