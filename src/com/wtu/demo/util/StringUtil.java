package com.wtu.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.wtu.demo.constants.Constants;
import com.wtu.demo.constants.SymbolConstants;


public final class StringUtil {

    private static final String NULL = "null";

    public static boolean isEmpty(String data){
        return data == null || SymbolConstants.NULL_QUOTES.equals(data.trim());
    }

    public static String doWithNull(Object object) {
        if (object == null) {
            return SymbolConstants.NULL_QUOTES;
        } else {
            String returnValue = object.toString();

            if (returnValue.equalsIgnoreCase(NULL)) {
                return SymbolConstants.NULL_QUOTES;
            } else {
                return returnValue.trim();
            }
        }
    }

    // The str is just to escape and recognized rapidly.
    public static String escape(String str) {
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");

        return str;
    }

    public static String dataToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATA_FORMAT);
        String str = sdf.format(date);

        return str;
    }
}