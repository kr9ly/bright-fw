package net.kr9ly.brightfw.helper.arguments;

import android.support.v4.app.Fragment;

import net.kr9ly.brightfw.helper.dialog.DefaultTagBuilder;
import net.kr9ly.brightfw.helper.dialog.TagBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentArguments {

    Class<? extends Fragment> bindTo();

    Class<? extends TagBuilder> tagBuilder() default DefaultTagBuilder.class;
}
