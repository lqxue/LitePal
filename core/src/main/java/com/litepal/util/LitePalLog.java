package com.litepal.util;

import android.util.Log;

public final class LitePalLog {
    public static final int DEBUG = 2;
    public static final int ERROR = 5;
    public static int level = ERROR;

    public static void d(String tagName, String message) {
        if (level <= DEBUG) {
            Log.d(tagName, message);
        }
    }

    public static void e(String tagName, Exception e) {
        if (level <= ERROR) {
            Log.e(tagName, e.getMessage(), e);
        }
    }
}