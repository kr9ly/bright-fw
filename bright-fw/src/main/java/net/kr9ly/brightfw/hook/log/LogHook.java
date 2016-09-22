package net.kr9ly.brightfw.hook.log;

import android.util.Log;

import net.kr9ly.brightfw.helper.hook.HookCallback;
import net.kr9ly.brightfw.helper.hook.HookHelper;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogHook {

    private LogLevel defaultLogLevel = LogLevel.DEBUG;

    private Map<String, LogLevel> logLevelMap = new ConcurrentHashMap<>();

    private LogLevel forceLevel;

    public LogHook(HookHelper hookHelper) {
        hookHelper.addHook(new HookCallback() {
            @Override
            public void callback(Class<?> hookClass, String hookName, Object... objects) {
                LogLevel logLevel;
                if (forceLevel != null) {
                    logLevel = forceLevel;
                } else {
                    logLevel = logLevelMap.get(hookClass.getName() + "." + hookName);
                    if (logLevel == null) {
                        logLevel = defaultLogLevel;
                    }
                }

                switch (logLevel) {
                    case ERROR:
                        Log.e(hookClass.getSimpleName(), String.format("%s, %s", hookName, Arrays.toString(objects)));
                        break;
                    case WARN:
                        Log.w(hookClass.getSimpleName(), String.format("%s, %s", hookName, Arrays.toString(objects)));
                        break;
                    case INFO:
                        Log.i(hookClass.getSimpleName(), String.format("%s, %s", hookName, Arrays.toString(objects)));
                        break;
                    case DEBUG:
                        Log.d(hookClass.getSimpleName(), String.format("%s, %s", hookName, Arrays.toString(objects)));
                        break;
                    case VERBOSE:
                        Log.v(hookClass.getSimpleName(), String.format("%s, %s", hookName, Arrays.toString(objects)));
                        break;
                }
            }
        });
    }

    public void setForceLevel(LogLevel forceLevel) {
        this.forceLevel = forceLevel;
    }

    public void setDefaultLogLevel(LogLevel defaultLogLevel) {
        this.defaultLogLevel = defaultLogLevel;
    }

    public void setLogLevel(Class<?> hookClass, String hookName, LogLevel logLevel) {
        logLevelMap.put(hookClass.getName() + "." + hookName, logLevel);
    }
}
