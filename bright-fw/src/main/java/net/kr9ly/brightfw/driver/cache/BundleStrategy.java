package net.kr9ly.brightfw.driver.cache;

import android.os.Bundle;

public interface BundleStrategy<T> {

    T fetchValue(Bundle bundle, String key);

    void storeValue(Bundle bundle, String key, T value);
}
