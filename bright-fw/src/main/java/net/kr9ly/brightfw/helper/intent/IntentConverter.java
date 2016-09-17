package net.kr9ly.brightfw.helper.intent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import net.kr9ly.brightfw.helper.arguments.ActivityArguments;
import net.kr9ly.brightfw.helper.arguments.Argument;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* package */ class IntentConverter {

    public static Intent buildIntent(Context context, Object arguments) {
        Class<?> argumentsClass = null;
        ActivityArguments typeInfo = null;

        for (Class<?> cl : arguments.getClass().getInterfaces()) {
            ActivityArguments info = cl.getAnnotation(ActivityArguments.class);
            if (info != null) {
                typeInfo = info;
                argumentsClass = cl;
            }
        }

        if (typeInfo == null) {
            throw new RuntimeException("Arguments interface Must be annotated by ActivityArguments.");
        }

        Intent intent = new Intent(context, typeInfo.bindTo());
        for (Method method : argumentsClass.getDeclaredMethods()) {
            Argument methodInfo = method.getAnnotation(Argument.class);
            if (methodInfo == null) {
                continue;
            }
            String keyName = methodInfo.key().isEmpty() ? method.getName() : methodInfo.key();
            Class<?> valueType = method.getReturnType();
            if (valueType == void.class) {
                continue;
            }
            if (method.getParameterTypes().length > 0) {
                continue;
            }
            try {
                Object value = method.invoke(arguments);
                setValueToIntent(intent, keyName, valueType, value);
            } catch (Throwable e) {
                //
            }
        }
        return intent;
    }

    @SuppressWarnings("unchecked")
    public static <T> T restoreFromIntent(Context context, final Intent intent, Class<T> argumentsClass) {
        if (!argumentsClass.isInterface()) {
            throw new RuntimeException("Arguments type Must be interface.");
        }
        ActivityArguments typeInfo = argumentsClass.getAnnotation(ActivityArguments.class);
        if (typeInfo == null) {
            throw new RuntimeException("Arguments interface Must be annotated by ActivityArguments.");
        }
        return (T) Proxy.newProxyInstance(context.getClassLoader(), new Class<?>[]{argumentsClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                Argument methodInfo = method.getAnnotation(Argument.class);
                if (methodInfo == null) {
                    return getDefaultValue(method.getReturnType());
                }
                String keyName = methodInfo.key().isEmpty() ? method.getName() : methodInfo.key();
                Class<?> valueType = method.getReturnType();
                return getValueFromIntent(intent, keyName, valueType);
            }
        });
    }

    public static Object getDefaultValue(Class<?> valueType) {
        if (valueType.isPrimitive()) {
            if (valueType == int.class) {
                return 0;
            } else if (valueType == long.class) {
                return 0L;
            } else if (valueType == float.class) {
                return 0f;
            } else if (valueType == double.class) {
                return 0d;
            } else if (valueType == boolean.class) {
                return false;
            } else if (valueType == short.class) {
                return (short) 0;
            } else if (valueType == byte.class) {
                return (byte) 0;
            } else if (valueType == char.class) {
                return (char) 0;
            }
        }
        return null;
    }

    public static void setValueToIntent(Intent intent, String keyName, Class<?> valueType, Object value) {
        if (valueType == String.class) {
            intent.putExtra(keyName, (String) value);
            return;
        }
        if (valueType.isPrimitive()) {
            if (valueType == int.class) {
                intent.putExtra(keyName, (int) value);
            } else if (valueType == long.class) {
                intent.putExtra(keyName, (long) value);
            } else if (valueType == float.class) {
                intent.putExtra(keyName, (float) value);
            } else if (valueType == double.class) {
                intent.putExtra(keyName, (double) value);
            } else if (valueType == boolean.class) {
                intent.putExtra(keyName, (boolean) value);
            } else if (valueType == short.class) {
                intent.putExtra(keyName, (short) value);
            } else if (valueType == byte.class) {
                intent.putExtra(keyName, (byte) value);
            } else if (valueType == char.class) {
                intent.putExtra(keyName, (char) value);
            }
            return;
        }
        if (valueType.isArray()) {
            if (valueType == int[].class) {
                intent.putExtra(keyName, (int[]) value);
            } else if (valueType == long[].class) {
                intent.putExtra(keyName, (long[]) value);
            } else if (valueType == float[].class) {
                intent.putExtra(keyName, (float[]) value);
            } else if (valueType == double[].class) {
                intent.putExtra(keyName, (double[]) value);
            } else if (valueType == boolean[].class) {
                intent.putExtra(keyName, (boolean[]) value);
            } else if (valueType == short[].class) {
                intent.putExtra(keyName, (short[]) value);
            } else if (valueType == byte[].class) {
                intent.putExtra(keyName, (byte[]) value);
            } else if (valueType == String[].class) {
                intent.putExtra(keyName, (String[]) value);
            }
        }
        if (valueType.isAssignableFrom(Bundle.class)) {
            intent.putExtra(keyName, (Bundle) value);
        } else if (valueType.isAssignableFrom(Parcelable.class)) {
            intent.putExtra(keyName, (Parcelable) value);
        } else if (valueType.isAssignableFrom(Parcelable[].class)) {
            intent.putExtra(keyName, (Parcelable[]) value);
        } else if (valueType.isAssignableFrom(Serializable.class)) {
            intent.putExtra(keyName, (Serializable) value);
        } else if (valueType.isAssignableFrom(CharSequence.class)) {
            intent.putExtra(keyName, (CharSequence) value);
        } else if (valueType.isAssignableFrom(CharSequence[].class)) {
            intent.putExtra(keyName, (CharSequence[]) value);
        }
    }

    public static Object getValueFromIntent(Intent intent, String keyName, Class<?> valueType) {
        if (valueType == String.class) {
            return intent.getStringExtra(keyName);
        }
        if (valueType.isPrimitive()) {
            if (valueType == int.class) {
                return intent.getIntExtra(keyName, 0);
            } else if (valueType == long.class) {
                return intent.getLongExtra(keyName, 0L);
            } else if (valueType == float.class) {
                return intent.getFloatExtra(keyName, 0f);
            } else if (valueType == double.class) {
                return intent.getDoubleExtra(keyName, 0d);
            } else if (valueType == boolean.class) {
                return intent.getBooleanExtra(keyName, false);
            } else if (valueType == short.class) {
                return intent.getShortExtra(keyName, (short) 0);
            } else if (valueType == byte.class) {
                return intent.getByteExtra(keyName, (byte) 0);
            } else if (valueType == char.class) {
                return intent.getCharExtra(keyName, (char) 0);
            }
            return null;
        }
        if (valueType.isArray()) {
            if (valueType == int[].class) {
                return intent.getIntArrayExtra(keyName);
            } else if (valueType == long[].class) {
                return intent.getLongArrayExtra(keyName);
            } else if (valueType == float[].class) {
                return intent.getFloatArrayExtra(keyName);
            } else if (valueType == double[].class) {
                return intent.getDoubleArrayExtra(keyName);
            } else if (valueType == boolean[].class) {
                return intent.getBooleanArrayExtra(keyName);
            } else if (valueType == short[].class) {
                return intent.getShortArrayExtra(keyName);
            } else if (valueType == byte[].class) {
                return intent.getByteArrayExtra(keyName);
            } else if (valueType == String[].class) {
                return intent.getStringArrayExtra(keyName);
            }
        }
        if (valueType.isAssignableFrom(Bundle.class)) {
            return intent.getBundleExtra(keyName);
        } else if (valueType.isAssignableFrom(Parcelable.class)) {
            return intent.getParcelableExtra(keyName);
        } else if (valueType.isAssignableFrom(Parcelable[].class)) {
            return intent.getParcelableArrayExtra(keyName);
        } else if (valueType.isAssignableFrom(Serializable.class)) {
            return intent.getSerializableExtra(keyName);
        } else if (valueType.isAssignableFrom(CharSequence.class)) {
            return intent.getCharSequenceExtra(keyName);
        } else if (valueType.isAssignableFrom(CharSequence[].class)) {
            return intent.getCharSequenceArrayExtra(keyName);
        }
        return null;
    }
}
