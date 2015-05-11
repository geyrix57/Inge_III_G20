package com.gadroves.gsisinve.utils;

/**
 * Created by geykel on 9/05/2015.
 */
@FunctionalInterface
public interface CallablePredicate<T, S> {
    boolean evaluate(T t, S s);
}
