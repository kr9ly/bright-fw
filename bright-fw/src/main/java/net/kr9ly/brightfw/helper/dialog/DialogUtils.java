package net.kr9ly.brightfw.helper.dialog;

import android.support.v4.app.DialogFragment;

import net.kr9ly.brightfw.helper.arguments.FragmentArguments;

/* package */ class DialogUtils {

    public static DialogFragment build(Object arguments) {
        FragmentArguments typeInfo = null;

        for (Class<?> cl : arguments.getClass().getInterfaces()) {
            FragmentArguments info = cl.getAnnotation(FragmentArguments.class);
            if (info != null) {
                typeInfo = info;
                break;
            }
        }

        if (typeInfo == null) {
            throw new RuntimeException("Arguments interface Must be annotated by FragmentArguments.");
        }

        try {
            return (DialogFragment) typeInfo.bindTo().newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTag(Object arguments) {
        FragmentArguments typeInfo = null;

        for (Class<?> cl : arguments.getClass().getInterfaces()) {
            FragmentArguments info = cl.getAnnotation(FragmentArguments.class);
            if (info != null) {
                typeInfo = info;
                break;
            }
        }

        if (typeInfo == null) {
            throw new RuntimeException("Arguments interface Must be annotated by FragmentArguments.");
        }

        try {
            return typeInfo.tagBuilder().newInstance().build(arguments);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
