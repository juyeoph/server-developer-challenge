package com.juyeoph.utils;

public class TodoUtils {
    private TodoUtils() {}

    public static TodoException TODO(final String message) {
        return new TodoException(message);
    }
}
