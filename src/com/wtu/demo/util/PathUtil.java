package com.wtu.demo.util;

import com.wtu.demo.common.AppContext;
import com.wtu.demo.constants.SymbolConstants;


public final class PathUtil {

    public static String getFullPath(String path) {
        if (path == null) {
            path = SymbolConstants.NULL_QUOTES;
        }

        return AppContext.getContextPath() + SymbolConstants.LEFT_LINE + path;
    }
}