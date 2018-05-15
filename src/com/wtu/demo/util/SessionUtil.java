package com.wtu.demo.util;

import java.lang.reflect.Method;

import com.wtu.demo.common.AppContext;
import com.wtu.demo.constants.Constants;

public final class SessionUtil {

    private static final String SET_ATTRIBUTE = "setAttribute";
    private static final String GET_ATTRIBUTE = "getAttribute";
    private static final String REMOVE_ATTRIBUTE = "removeAttribute";

    private static Object getSessionInThread() {
        Object session = AppContext.getContext().getObject(Constants.APP_CONTEXT_SESSION);

        return session;
    }

    public static void addSession(String key, Object value) {
        Object session = getSessionInThread();

        if (session == null) {
            return;
        }

        try {
            Class<?>[] param = new Class[2];
            param[0] = String.class;
            param[1] = Object.class;

            Method method = session.getClass().getMethod(SET_ATTRIBUTE, param);

            Object[] objects = new Object[2];
            objects[0] = key;
            objects[1] = value;

            method.invoke(session, objects);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Object getSession(String key) {
        Object session = getSessionInThread();

        if (session == null) {
            return null;
        }

        try {
            Class<?>[] param = new Class[1];
            param[0] = String.class;

            Method method = session.getClass().getMethod(GET_ATTRIBUTE, param);

            Object[] objects = new Object[1];
            objects[0] = key;

            Object value = method.invoke(session, objects);

            return value;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public static void removeSession(String key) {
        Object session = getSessionInThread();

        if (session == null) {
            return;
        }

        try {
            Class<?>[] param = new Class[1];
            param[0] = String.class;

            Method method = session.getClass().getMethod(REMOVE_ATTRIBUTE, param);

            Object[] objects = new Object[1];
            objects[0] = key;

            method.invoke(session, objects);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}