package net.kr9ly.brightfw.driver.rest;

import okhttp3.Response;

public interface ResponseAdapter<T> {

    T convert(Response response) throws Throwable;
}
