package net.kr9ly.brightfw.dependency.module.driver;

import net.kr9ly.brightfw.dependency.scope.SingletonScope;
import net.kr9ly.brightfw.driver.okhttp.DefaultOkHttpClientBuilder;
import net.kr9ly.brightfw.driver.okhttp.OkHttpClientBuilder;
import net.kr9ly.brightfw.driver.okhttp.OkHttpDriver;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class OkHttpDriverModule {

    private OkHttpClientBuilder builder;

    public OkHttpDriverModule() {
        this(new DefaultOkHttpClientBuilder());
    }

    public OkHttpDriverModule(OkHttpClientBuilder builder) {
        this.builder = builder;
    }

    @SingletonScope
    @Provides
    OkHttpDriver okHttpDriver() {
        return new OkHttpDriver();
    }

    @SingletonScope
    @Provides
    OkHttpClient okHttpClient() {
        return builder.build();
    }
}
