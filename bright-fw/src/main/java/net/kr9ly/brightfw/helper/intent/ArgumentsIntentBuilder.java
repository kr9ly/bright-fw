package net.kr9ly.brightfw.helper.intent;

import android.content.Context;
import android.content.Intent;

public class ArgumentsIntentBuilder implements IntentBuilder {

    public static ArgumentsIntentBuilder of(Object arguments) {
        return new ArgumentsIntentBuilder(arguments);
    }

    private Object arguments;

    protected ArgumentsIntentBuilder(Object arguments) {
        this.arguments = arguments;
    }

    @Override
    public Intent build(Context context) {
        return IntentConverter.buildIntent(context, arguments);
    }
}
