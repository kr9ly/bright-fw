package net.kr9ly.brightfw.driver.cache;

import android.os.Bundle;

import rx.Subscriber;
import rx.functions.Func1;
import rx.subjects.Subject;

public class BundleSubject<T> extends Subject<T, T> {

    private final String bundleKey;

    private final BundleStrategy<T> strategy;

    private final Bundle bundle;

    private final Subject<String, String> updateQueue;

    protected BundleSubject(final String bundleKey, final BundleStrategy<T> strategy, final Bundle bundle, final Subject<String, String> updateQueue) {
        super(new OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                updateQueue.filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String key) {
                        return bundleKey.equals(key);
                    }
                }).map(new Func1<String, T>() {
                    @Override
                    public T call(String key) {
                        return strategy.fetchValue(bundle, key);
                    }
                }).subscribe(subscriber);
            }
        });
        this.bundleKey = bundleKey;
        this.strategy = strategy;
        this.bundle = bundle;
        this.updateQueue = updateQueue;
    }

    @Override
    public boolean hasObservers() {
        return updateQueue.hasObservers();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T value) {
        strategy.storeValue(bundle, bundleKey, value);
    }
}
