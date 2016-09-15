package net.kr9ly.brightfw.helper.transition;

import android.app.Fragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentArguments {

    Class<? extends Fragment> bindTo();
}
