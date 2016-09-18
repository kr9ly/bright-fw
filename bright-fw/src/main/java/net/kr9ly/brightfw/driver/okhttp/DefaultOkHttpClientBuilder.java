package net.kr9ly.brightfw.driver.okhttp;

import okhttp3.OkHttpClient;

public class DefaultOkHttpClientBuilder implements OkHttpClientBuilder {

    @Override
    public OkHttpClient build() {
        return new OkHttpClient();
    }
}
