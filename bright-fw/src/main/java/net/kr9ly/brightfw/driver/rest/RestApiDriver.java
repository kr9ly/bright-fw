package net.kr9ly.brightfw.driver.rest;

import net.kr9ly.brightfw.driver.okhttp.OkHttpDriver;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

public class RestApiDriver<Request, Response, Error> {

    private final OkHttpDriver okHttpDriver;

    private final RequestAdapter<Request> requestAdapter;

    private final ResponseAdapter<Response> responseAdapter;

    private final ResponseValidator<Error> responseValidator;

    private final ParseErrorConverter<Error> parseErrorConverter;

    private final PublishSubject<Response> responseSubject = PublishSubject.create();

    private final PublishSubject<Error> errorSubject = PublishSubject.create();

    public RestApiDriver(OkHttpDriver okHttpDriver,
                         RequestAdapter<Request> requestAdapter,
                         ResponseAdapter<Response> responseAdapter,
                         ResponseValidator<Error> responseValidator,
                         ParseErrorConverter<Error> parseErrorConverter) {
        this.okHttpDriver = okHttpDriver;
        this.requestAdapter = requestAdapter;
        this.responseAdapter = responseAdapter;
        this.responseValidator = responseValidator;
        this.parseErrorConverter = parseErrorConverter;
    }

    public Observable<Response> request(OkHttpClient client, Request request) {
        return okHttpDriver.request(client, requestAdapter.convert(request))
                .filter(new Func1<okhttp3.Response, Boolean>() {
                    @Override
                    public Boolean call(okhttp3.Response response) {
                        Error error = responseValidator.validate(response);
                        if (error != null) {
                            errorSubject.onNext(error);
                            return false;
                        }
                        return true;
                    }
                })
                .map(new Func1<okhttp3.Response, Response>() {
                    @Override
                    public Response call(okhttp3.Response response) {
                        try {
                            return responseAdapter.convert(response);
                        } catch (Throwable throwable) {
                            errorSubject.onNext(parseErrorConverter.convert(throwable));
                            return null;
                        }
                    }
                })
                .filter(new Func1<Response, Boolean>() {
                    @Override
                    public Boolean call(Response response) {
                        return response != null;
                    }
                })
                .doOnNext(new Action1<Response>() {
                    @Override
                    public void call(Response response) {
                        responseSubject.onNext(response);
                    }
                });
    }

    public Observable<Response> responseObservable() {
        return responseSubject;
    }

    public Observable<Error> errorObservable() {
        return errorSubject;
    }
}
