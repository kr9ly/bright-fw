package net.kr9ly.brightfw.helper.hook;

public class Hook {

    private HookHelper helper;

    private Class<?> hookClass;

    public Hook(HookHelper helper, Class<?> hookClass) {
        this.helper = helper;
        this.hookClass = hookClass;
    }

    public void hook(String hookName, Object... objects) {
        helper.callback(hookClass, hookName, objects);
    }
}
