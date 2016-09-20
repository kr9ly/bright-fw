package net.kr9ly.brightfw.helper.intent;

import android.content.Context;
import android.content.Intent;

public class IntentHelper {

    private Context context;

    public IntentHelper(Context context) {
        this.context = context;
    }

    public Intent build(IntentBuilder builder) {
        return builder.build(context);
    }

    public <T> T restore(Intent intent, Class<T> argumentsClass) {
        return IntentConverter.restoreFromIntent(context, intent, argumentsClass);
    }
}
