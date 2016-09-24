package net.kr9ly.brightfw.driver.cache;

import android.os.Bundle;
import android.os.Parcelable;

import net.kr9ly.brightfw.app.callback.OnCreateCallback;
import net.kr9ly.brightfw.app.callback.OnSaveInstanceStateCallback;
import net.kr9ly.brightfw.dependency.scope.MainScope;

import java.io.Serializable;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

@MainScope
public class BundleCacheDriver implements OnCreateCallback, OnSaveInstanceStateCallback {

    private final String bundleName;

    private Bundle bundle = new Bundle();

    private final Subject<String, String> updateQueue = new SerializedSubject<>(PublishSubject.<String>create());

    @Inject
    public BundleCacheDriver(BundleCacheSettings settings) {
        this.bundleName = settings.bundleName();
    }

    public BundleSubject<Boolean> booleanCache(String bundleKey) {
        return new BundleSubject<>(bundleKey, new BundleStrategy<Boolean>() {
            @Override
            public Boolean fetchValue(Bundle bundle, String key) {
                return bundle.getBoolean(key);
            }

            @Override
            public void storeValue(Bundle bundle, String key, Boolean value) {
                bundle.putBoolean(key, value);
            }
        }, bundle, updateQueue);
    }

    public BundleSubject<String> stringCache(String bundleKey) {
        return new BundleSubject<>(bundleKey, new BundleStrategy<String>() {
            @Override
            public String fetchValue(Bundle bundle, String key) {
                return bundle.getString(key);
            }

            @Override
            public void storeValue(Bundle bundle, String key, String value) {
                bundle.putString(key, value);
            }
        }, bundle, updateQueue);
    }

    public BundleSubject<Integer> integerCache(String bundleKey) {
        return new BundleSubject<>(bundleKey, new BundleStrategy<Integer>() {
            @Override
            public Integer fetchValue(Bundle bundle, String key) {
                return bundle.getInt(key);
            }

            @Override
            public void storeValue(Bundle bundle, String key, Integer value) {
                bundle.putInt(key, value);
            }
        }, bundle, updateQueue);
    }

    public BundleSubject<Long> longCache(String bundleKey) {
        return new BundleSubject<>(bundleKey, new BundleStrategy<Long>() {
            @Override
            public Long fetchValue(Bundle bundle, String key) {
                return bundle.getLong(key);
            }

            @Override
            public void storeValue(Bundle bundle, String key, Long value) {
                bundle.putLong(key, value);
            }
        }, bundle, updateQueue);
    }

    public BundleSubject<Float> floatCache(String bundleKey) {
        return new BundleSubject<>(bundleKey, new BundleStrategy<Float>() {
            @Override
            public Float fetchValue(Bundle bundle, String key) {
                return bundle.getFloat(key);
            }

            @Override
            public void storeValue(Bundle bundle, String key, Float value) {
                bundle.putFloat(key, value);
            }
        }, bundle, updateQueue);
    }

    public <T extends Serializable> BundleSubject<T> serializableCache(String bundleKey) {
        return new BundleSubject<>(bundleKey, new BundleStrategy<T>() {
            @Override
            @SuppressWarnings("unchecked")
            public T fetchValue(Bundle bundle, String key) {
                return (T) bundle.getSerializable(key);
            }

            @Override
            public void storeValue(Bundle bundle, String key, T value) {
                bundle.putSerializable(key, value);
            }
        }, bundle, updateQueue);
    }

    public <T extends Parcelable> BundleSubject<T> parcelableCache(String bundleKey) {
        return new BundleSubject<>(bundleKey, new BundleStrategy<T>() {
            @Override
            public T fetchValue(Bundle bundle, String key) {
                return bundle.getParcelable(key);
            }

            @Override
            public void storeValue(Bundle bundle, String key, T value) {
                bundle.putParcelable(key, value);
            }
        }, bundle, updateQueue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        bundle.putAll(savedInstanceState.getBundle(bundleName));

        Observable.from(bundle.keySet()).subscribe(updateQueue);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBundle(bundleName, bundle);
    }
}
