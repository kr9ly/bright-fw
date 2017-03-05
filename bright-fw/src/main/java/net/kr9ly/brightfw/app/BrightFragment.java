package net.kr9ly.brightfw.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import net.kr9ly.brightfw.dependency.component.BrightMainComponent;

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
public abstract class BrightFragment<SingletonComponent, MainComponent extends BrightMainComponent> extends Fragment {

    private MainComponent component;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepareComponent(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private void prepareComponent(Bundle savedInstanceState) {
        BrightApplication<SingletonComponent> application = (BrightApplication<SingletonComponent>) getActivity().getApplication();
        component = buildComponent(application.getComponent());
        componentCreated(component);

        component.lifecycleController().onCreate(savedInstanceState);
        component.lifecycleController().onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (component == null) {
            return;
        }

        component.lifecycleController().onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (component == null) {
            return;
        }

        component.lifecycleController().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (component == null) {
            return;
        }

        component.lifecycleController().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (component == null) {
            return;
        }

        component.lifecycleController().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (component == null) {
            return;
        }

        component.lifecycleController().onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (component == null) {
            return;
        }

        component.lifecycleController().onDestroy();
    }

    protected abstract MainComponent buildComponent(SingletonComponent singletonComponent);

    protected abstract void componentCreated(MainComponent component);
}
