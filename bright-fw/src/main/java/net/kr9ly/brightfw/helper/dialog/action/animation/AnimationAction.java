package net.kr9ly.brightfw.helper.dialog.action.animation;

import android.support.v4.app.DialogFragment;

import java.io.Serializable;

public interface AnimationAction extends Serializable {

    void show(DialogFragment dialog);

    void dismiss(DialogFragment dialog);
}
