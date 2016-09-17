package net.kr9ly.brightfw.helper.arguments;

import android.app.Activity;

import net.kr9ly.brightfw.helper.intent.IntentHelper;

public class ActivityArgumentsHelper implements ArgumentsHelper {

    private Activity activity;

    private IntentHelper intentHelper;

    public ActivityArgumentsHelper(Activity activity, IntentHelper intentHelper) {
        this.activity = activity;
        this.intentHelper = intentHelper;
    }

    @Override
    public <T> T getArguments(Class<T> argumentsClass) {
        return intentHelper.restore(activity.getIntent(), argumentsClass);
    }
}
