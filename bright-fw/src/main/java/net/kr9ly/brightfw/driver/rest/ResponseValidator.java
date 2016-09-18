package net.kr9ly.brightfw.driver.rest;

import okhttp3.Response;

public interface ResponseValidator<T> {

    T validate(Response response);
}
