package net.kr9ly.brightfw.dependency.module.helper.arguments;

import net.kr9ly.brightfw.helper.arguments.ArgumentsHelper;
import net.kr9ly.brightfw.helper.bundle.BundleHelper;
import net.kr9ly.brightfw.helper.intent.IntentHelper;

public interface ArgumentsHelperDelegate {

    ArgumentsHelper argumentsHelper(
            IntentHelper intentHelper,
            BundleHelper bundleHelper
    );
}
