package net.kr9ly.brightfw.helper.hook;

import net.kr9ly.brightfw.dependency.scope.SingletonScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@SingletonScope
public class HookHelper {

    private List<HookCallback> callbacks = new ArrayList<>();

    @Inject
    public HookHelper() {

    }

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
