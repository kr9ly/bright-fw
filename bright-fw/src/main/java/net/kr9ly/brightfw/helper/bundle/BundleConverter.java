package net.kr9ly.brightfw.helper.bundle;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import net.kr9ly.brightfw.helper.arguments.Argument;
import net.kr9ly.brightfw.helper.arguments.FragmentArguments;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* package */ class BundleConverter {

    public static Bundle buildBundle(Object arguments) {
        Class<?> argumentsClass = null;

        for (Class<?> cl : arguments.getClass().getInterfaces()) {
            FragmentArguments info = cl.getAnnotation(FragmentArguments.class);
            if (info != null) {
                argumentsClass = cl;
                break;
            }
        }

        if (argumentsClass == null) {
            throw new RuntimeException("Arguments interface Must be annotated by FragmentArguments.");
        }
        Bundle bundle = new Bundle();
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
                setValueToBundle(bundle, keyName, valueType, value);
            } catch (Throwable e) {
                //
            }
        }
        return bundle;
    }

    @SuppressWarnings("unchecked")
    public static <T> T restoreFromBundle(Context context, final Bundle bundle, Class<T> argumentsClass) {
        if (!argumentsClass.isInterface()) {
            throw new RuntimeException("Arguments type Must be interface.");
        }
        FragmentArguments typeInfo = argumentsClass.getAnnotation(FragmentArguments.class);
        if (typeInfo == null) {
            throw new RuntimeException("Arguments interface Must be annotated by FragmentArguments.");
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
                return getValueFromBundle(bundle, keyName, valueType);
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

    public static void setValueToBundle(Bundle bundle, String keyName, Class<?> valueType, Object value) {
        if (valueType == String.class) {
            bundle.putString(keyName, (String) value);
            return;
        }
        if (valueType.isPrimitive()) {
            if (valueType == int.class) {
                bundle.putInt(keyName, (int) value);
            } else if (valueType == long.class) {
                bundle.putLong(keyName, (long) value);
            } else if (valueType == float.class) {
                bundle.putFloat(keyName, (float) value);
            } else if (valueType == double.class) {
                bundle.putDouble(keyName, (double) value);
            } else if (valueType == boolean.class) {
                bundle.putBoolean(keyName, (boolean) value);
            } else if (valueType == short.class) {
                bundle.putShort(keyName, (short) value);
            } else if (valueType == byte.class) {
                bundle.putByte(keyName, (byte) value);
            } else if (valueType == char.class) {
                bundle.putChar(keyName, (char) value);
            }
            return;
        }
        if (valueType.isArray()) {
            if (valueType == int[].class) {
                bundle.putIntArray(keyName, (int[]) value);
            } else if (valueType == long[].class) {
                bundle.putLongArray(keyName, (long[]) value);
            } else if (valueType == float[].class) {
                bundle.putFloatArray(keyName, (float[]) value);
            } else if (valueType == double[].class) {
                bundle.putDoubleArray(keyName, (double[]) value);
            } else if (valueType == boolean[].class) {
                bundle.putBooleanArray(keyName, (boolean[]) value);
            } else if (valueType == short[].class) {
                bundle.putShortArray(keyName, (short[]) value);
            } else if (valueType == byte[].class) {
                bundle.putByteArray(keyName, (byte[]) value);
            } else if (valueType == String[].class) {
                bundle.putStringArray(keyName, (String[]) value);
            }
        }
        if (valueType.isAssignableFrom(Bundle.class)) {
            bundle.putBundle(keyName, (Bundle) value);
        } else if (valueType.isAssignableFrom(Parcelable.class)) {
            bundle.putParcelable(keyName, (Parcelable) value);
        } else if (valueType.isAssignableFrom(Parcelable[].class)) {
            bundle.putParcelableArray(keyName, (Parcelable[]) value);
        } else if (valueType.isAssignableFrom(Serializable.class)) {
            bundle.putSerializable(keyName, (Serializable) value);
        } else if (valueType.isAssignableFrom(CharSequence.class)) {
            bundle.putCharSequence(keyName, (CharSequence) value);
        } else if (valueType.isAssignableFrom(CharSequence[].class)) {
            bundle.putCharSequenceArray(keyName, (CharSequence[]) value);
        }
    }

    public static Object getValueFromBundle(Bundle bundle, String keyName, Class<?> valueType) {
        if (valueType == String.class) {
            return bundle.getString(keyName);
        }
        if (valueType.isPrimitive()) {
            if (valueType == int.class) {
                return bundle.getInt(keyName, 0);
            } else if (valueType == long.class) {
                return bundle.getLong(keyName, 0L);
            } else if (valueType == float.class) {
                return bundle.getFloat(keyName, 0f);
            } else if (valueType == double.class) {
                return bundle.getDouble(keyName, 0d);
            } else if (valueType == boolean.class) {
                return bundle.getBoolean(keyName, false);
            } else if (valueType == short.class) {
                return bundle.getShort(keyName, (short) 0);
            } else if (valueType == byte.class) {
                return bundle.getByte(keyName, (byte) 0);
            } else if (valueType == char.class) {
                return bundle.getChar(keyName, (char) 0);
            }
            return null;
        }
        if (valueType.isArray()) {
            if (valueType == int[].class) {
                return bundle.getIntArray(keyName);
            } else if (valueType == long[].class) {
                return bundle.getLongArray(keyName);
            } else if (valueType == float[].class) {
                return bundle.getFloatArray(keyName);
            } else if (valueType == double[].class) {
                return bundle.getDoubleArray(keyName);
            } else if (valueType == boolean[].class) {
                return bundle.getBooleanArray(keyName);
            } else if (valueType == short[].class) {
                return bundle.getShortArray(keyName);
            } else if (valueType == byte[].class) {
                return bundle.getByteArray(keyName);
            } else if (valueType == String[].class) {
                return bundle.getStringArray(keyName);
            }
        }
        if (valueType.isAssignableFrom(Bundle.class)) {
            return bundle.getBundle(keyName);
        } else if (valueType.isAssignableFrom(Parcelable.class)) {
            return bundle.getParcelable(keyName);
        } else if (valueType.isAssignableFrom(Parcelable[].class)) {
            return bundle.getParcelableArray(keyName);
        } else if (valueType.isAssignableFrom(Serializable.class)) {
            return bundle.getSerializable(keyName);
        } else if (valueType.isAssignableFrom(CharSequence.class)) {
            return bundle.getCharSequence(keyName);
        } else if (valueType.isAssignableFrom(CharSequence[].class)) {
            return bundle.getCharSequenceArray(keyName);
        }
        return null;
    }
}
