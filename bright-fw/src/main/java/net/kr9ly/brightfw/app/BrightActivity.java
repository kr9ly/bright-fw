package net.kr9ly.brightfw.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import net.kr9ly.brightfw.dependency.component.BrightMainComponent;
import net.kr9ly.brightfw.helper.transition.TransitionHelper;
import net.kr9ly.brightfw.helper.transition.action.ActivityTransitionAction;

import java.io.Serializable;

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
public abstract class BrightActivity<SingletonComponent, MainComponent extends BrightMainComponent> extends FragmentActivity {

    private MainComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareComponent(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private void prepareComponent(Bundle savedInstanceState) {
        BrightApplication<SingletonComponent> application = (BrightApplication<SingletonComponent>) getApplication();
        component = buildComponent(application.getComponent());
        componentCreated(component);

        component.lifecycleController().onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        component.lifecycleController().onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        component.lifecycleController().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        component.lifecycleController().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        component.lifecycleController().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        component.lifecycleController().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        component.lifecycleController().onDestroy();
    }

    @Override
    public void finish() {
        Intent intent = getIntent();
        if (intent != null) {
            ActivityTransitionAction action = (ActivityTransitionAction) intent.getSerializableExtra(TransitionHelper.EXTRA_KEY_TRANSITION_ACTION);
            if (action != null) {
                action.back(this, intent);
            }
        }

        super.finish();
    }

    protected abstract MainComponent buildComponent(SingletonComponent singletonComponent);

    protected abstract void componentCreated(MainComponent component);
}
