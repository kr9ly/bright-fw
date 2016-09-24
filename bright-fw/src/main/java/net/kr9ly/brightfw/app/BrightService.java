package net.kr9ly.brightfw.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Copyright 2016 kr9ly
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public abstract class BrightService<SingletonComponent> extends Service {

    @Override
    @SuppressWarnings("unchecked")
    public final int onStartCommand(Intent intent, int flags, int startId) {
        BrightApplication<SingletonComponent> application = (BrightApplication<SingletonComponent>) getApplication();
        return onStartCommand(application.getComponent(), intent, flags, startId);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public final IBinder onBind(Intent intent) {
        BrightApplication<SingletonComponent> application = (BrightApplication<SingletonComponent>) getApplication();
        return onBind(application.getComponent(), intent);
    }

    @StickyPolicy
    protected abstract int onStartCommand(SingletonComponent component, Intent intent, int flags, int startId);

    protected abstract IBinder onBind(SingletonComponent component, Intent intent);

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {START_STICKY_COMPATIBILITY, START_STICKY, START_NOT_STICKY, START_REDELIVER_INTENT})
    public @interface StickyPolicy {
    }
}
