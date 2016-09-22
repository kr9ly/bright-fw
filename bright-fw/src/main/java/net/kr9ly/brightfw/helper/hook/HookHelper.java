package net.kr9ly.brightfw.helper.hook;

import java.util.ArrayList;
import java.util.List;

public class HookHelper {

    private List<HookCallback> callbacks = new ArrayList<>();

    public void addHook(HookCallback callback) {
        callbacks.add(callback);
    }

    public Hook of(Class<?> hookClass) {
        return new Hook(this, hookClass);
    }

    /* package */ void callback(Class<?> hookClass, String hookName, Object... objects) {
        for (HookCallback callback : callbacks) {
            callback.callback(hookClass, hookName, objects);
        }
    }
}
