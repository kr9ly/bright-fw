package net.kr9ly.brightfw.helper.hook;

public interface HookCallback {

    void callback(Class<?> hookClass, String hookName, Object... objects);
}
