package net.kr9ly.brightfw.dependency.module.helper.arguments;

import android.app.Activity;

import net.kr9ly.brightfw.helper.arguments.ActivityArgumentsHelper;
import net.kr9ly.brightfw.helper.arguments.ArgumentsHelper;
import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.intent.IntentHelper;

public class ActivityArgumentsHelperDelegate implements ArgumentsHelperDelegate {

    private Activity activity;

    public ActivityArgumentsHelperDelegate(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ArgumentsHelper argumentsHelper(IntentHelper intentHelper, BundleHelper bundleHelper) {
        return new ActivityArgumentsHelper(activity, intentHelper);
    }
}
