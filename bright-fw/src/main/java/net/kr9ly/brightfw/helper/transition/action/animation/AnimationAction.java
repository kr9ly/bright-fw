package net.kr9ly.brightfw.helper.transition.action.animation;

import android.support.v4.app.FragmentActivity;

import java.io.Serializable;

public interface AnimationAction extends Serializable {

    void forward(FragmentActivity activity);

    void back(FragmentActivity activity);
}
