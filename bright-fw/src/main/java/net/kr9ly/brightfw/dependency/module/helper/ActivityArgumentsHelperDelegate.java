package net.kr9ly.brightfw.dependency.module.helper;

import android.app.Activity;

import net.kr9ly.brightfw.helper.transition.ActivityArgumentsHelper;
import net.kr9ly.brightfw.helper.transition.ArgumentsHelper;
import net.kr9ly.brightfw.helper.transition.BundleHelper;
import net.kr9ly.brightfw.helper.transition.IntentHelper;

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
