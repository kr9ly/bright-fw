package net.kr9ly.brightfw.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

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
public abstract class BrightDialogFragment<SingletonComponent, MainComponent extends BrightMainComponent> extends DialogFragment {

    private MainComponent component;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog = prepareComponent(savedInstanceState, dialog);

        return dialog;
    }

    @SuppressWarnings("unchecked")
    private Dialog prepareComponent(Bundle savedInstanceState, Dialog dialog) {
        BrightApplication<SingletonComponent> application = (BrightApplication<SingletonComponent>) getActivity().getApplication();
        component = buildComponent(application.getComponent());
        dialog = componentCreated(component, dialog);

        component.lifecycleController().onCreate(savedInstanceState);

        return dialog;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        component.lifecycleController().onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();

        component.lifecycleController().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        component.lifecycleController().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        component.lifecycleController().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        component.lifecycleController().onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        component.lifecycleController().onDestroy();
    }

    protected abstract MainComponent buildComponent(SingletonComponent singletonComponent);

    protected abstract Dialog componentCreated(MainComponent component, Dialog dialog);
}
