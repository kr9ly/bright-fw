package net.kr9ly.brightfw.helper.dialog;

public class DefaultTagBuilder implements TagBuilder {

    @Override
    public String build(Object object) {
        return object.getClass().getName();
    }
}
