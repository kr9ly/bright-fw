package net.kr9ly.brightfw.dependency.module.helper.transition;

import net.kr9ly.brightfw.helper.intent.IntentHelper;
import net.kr9ly.brightfw.helper.transition.TransitionHelper;

public interface TransitionHelperDelegate {

    TransitionHelper transitionHelper(IntentHelper intentHelper);
}
