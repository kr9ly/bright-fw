package net.kr9ly.brightfw.helper.transition;

import android.app.Activity;

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
