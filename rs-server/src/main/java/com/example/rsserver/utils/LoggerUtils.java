package com.example.rsserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggerUtils {
    private LoggerUtils() {}

    public static Logger getLogger() {
        final Throwable t = new Throwable();
        t.fillInStackTrace();
        final String clazz = t.getStackTrace()[1].getClassName();
        return LoggerFactory.getLogger(clazz);
    }
}
