package com.wtu.demo.common;

import java.util.HashMap;
import java.util.Map;

import com.wtu.demo.constants.Constants;
import com.wtu.demo.constants.SymbolConstants;
import com.wtu.demo.model.User;


public class AppContext {

    // ThreadLocal<T> can replace the appcontextMap
    private static Map<Thread, AppContext> appContextMap = new HashMap<Thread, AppContext>();

    private Map<String, Object> objects = new HashMap<String, Object>();

    private static String contextPath;

    private AppContext() {
    }

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPath) {
        if (AppContext.contextPath == null) {
            AppContext.contextPath = contextPath;
        }
    }

    public static AppContext getContext() {
        Thread thread = Thread.currentThread();
        AppContext appContext = appContextMap.get(thread);

        if (appContext == null) {
            appContext = new AppContext();
            appContextMap.put(thread, appContext);
        }

        return appContextMap.get(thread);
    }

    public Map<String, Object> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, Object> objects) {
        if (objects == null) {
            objects = new HashMap<String, Object>();
        }

        this.objects = objects;
    }

    public void addObject(String key, Object object) {
        this.objects.put(key, object);
    }

    public void removeObject(String key) {
        this.objects.remove(key);
    }

    public Object getObject(String key) {
        return this.objects.get(key);
    }

    public void clear() {
        objects.clear();
    }

    public User getUser() {
        return (User) objects.get(Constants.APP_CONTEXT_USER);
    }

    public String getUserName() {
        User user = (User) objects.get(Constants.APP_CONTEXT_USER);

        if (user != null) {
            return user.getUserName();
        }

        return SymbolConstants.NULL_QUOTES;
    }
}