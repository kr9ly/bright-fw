package net.kr9ly.brightfw.helper.transition.action;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.io.Serializable;

public interface ActivityTransitionAction extends Serializable {

    void forward(FragmentActivity activity, Intent intent);

    void back(FragmentActivity activity, Intent intent);
}
