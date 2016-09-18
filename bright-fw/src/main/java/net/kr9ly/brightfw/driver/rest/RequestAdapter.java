package net.kr9ly.brightfw.driver.rest;

import okhttp3.Request;

public interface RequestAdapter<T> {

    Request convert(T request);
}
