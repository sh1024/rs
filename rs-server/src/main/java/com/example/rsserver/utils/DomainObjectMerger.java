package com.example.rsserver.utils;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public class DomainObjectMerger {

    public static void merge(Object src, Object dest) {
        Assert.notNull(src, "Source for field copy cannot be null");
        Assert.notNull(dest, "Destination for field copy cannot be null");

        if (!src.getClass().isAssignableFrom(dest.getClass())) {
            throw new IllegalArgumentException("Destination class [" + dest.getClass().getName() +
                    "] must be same or subclass as source class [" + src.getClass().getName() + "]");
        }
        ReflectionUtils.doWithFields(src.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            Object destValue = field.get(dest);
            if (destValue != null) {
                return;
            }
            Object srcValue = field.get(src);
            field.set(dest, srcValue);
        }, ReflectionUtils.COPYABLE_FIELDS);
    }
}

