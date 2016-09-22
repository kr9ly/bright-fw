package net.kr9ly.brightfw.driver.okhttp;

import net.kr9ly.brightfw.helper.hook.Hook;
import net.kr9ly.brightfw.helper.hook.HookHelper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

public class OkHttpDriver {

    private Hook hook;

    public OkHttpDriver(HookHelper hook) {
        this.hook = hook.of(getClass());
    }

    public Observable<Response> request(final OkHttpClient client, final Request request) {
        return Observable.from(new Future<Response>() {

            private Call call;

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                if (mayInterruptIfRunning && call != null) {
                    call.cancel();
                }
                return true;
            }

            @Override
            public boolean isCancelled() {
                return call == null || call.isCanceled();
            }

            @Override
            public boolean isDone() {
                return call != null && call.isExecuted();
            }

            @Override
            public Response get() throws InterruptedException, ExecutionException {
                call = client.newCall(request);
                hook.hook("start_request", call);
                try {
                    Response response = call.execute();
                    hook.hook("finish_request", response);
                    return response;
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            public Response get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                call = client.newBuilder()
                        .connectTimeout(l, timeUnit)
                        .build()
                        .newCall(request);
                hook.hook("start_request", call);
                try {
                    Response response = call.execute();
                    hook.hook("finish_request", response);
                    return response;
                } catch (IOException e) {
                    return null;
                }
            }
        });
    }
}
