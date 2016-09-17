package net.kr9ly.brightfw.helper.dialog;

import net.kr9ly.brightfw.helper.dialog.action.animation.AnimationAction;

public interface DialogHelper {

    String ARGUMENT_KEY_ANIMATION_ACTION = "ANIMATION_ACTION_INSTANCE";

    void show(Object arguments, AnimationAction action);
}
