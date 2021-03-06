package net.kr9ly.brightfw.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;

import net.kr9ly.brightfw.dependency.component.BrightMainComponent;
import net.kr9ly.brightfw.helper.dialog.DialogHelper;
import net.kr9ly.brightfw.helper.dialog.action.animation.AnimationAction;

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
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return component.lifecycleController().dispatchKeyEvent(keyEvent);
            }
        });

        return dialog;
    }

    @SuppressWarnings("unchecked")
    private Dialog prepareComponent(Bundle savedInstanceState, Dialog dialog) {
        BrightApplication<SingletonComponent> application = (BrightApplication<SingletonComponent>) getActivity().getApplication();
        component = buildComponent(application.getComponent());
        dialog = componentCreated(component, dialog);

        component.lifecycleController().onCreate(savedInstanceState);
        component.lifecycleController().onRestoreInstanceState(savedInstanceState);

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

        AnimationAction action = (AnimationAction) getArguments().getSerializable(DialogHelper.ARGUMENT_KEY_ANIMATION_ACTION);
        if (action != null) {
            action.show(this);
        }

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

    @Override
    public void dismiss() {
        AnimationAction action = (AnimationAction) getArguments().getSerializable(DialogHelper.ARGUMENT_KEY_ANIMATION_ACTION);
        if (action != null) {
            action.dismiss(this);
        }

        super.dismiss();
    }

    protected abstract MainComponent buildComponent(SingletonComponent singletonComponent);

    protected abstract Dialog componentCreated(MainComponent component, Dialog dialog);
}
