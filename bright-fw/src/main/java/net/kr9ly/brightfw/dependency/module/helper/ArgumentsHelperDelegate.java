package net.kr9ly.brightfw.dependency.module.helper;

import net.kr9ly.brightfw.helper.transition.ArgumentsHelper;
import net.kr9ly.brightfw.helper.transition.BundleHelper;
import net.kr9ly.brightfw.helper.transition.IntentHelper;

public interface ArgumentsHelperDelegate {

    ArgumentsHelper argumentsHelper(
            IntentHelper intentHelper,
            BundleHelper bundleHelper
    );
}
