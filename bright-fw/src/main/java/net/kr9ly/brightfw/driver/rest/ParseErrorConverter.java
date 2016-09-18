package net.kr9ly.brightfw.driver.rest;

public interface ParseErrorConverter<T> {

    T convert(Throwable throwable);
}
