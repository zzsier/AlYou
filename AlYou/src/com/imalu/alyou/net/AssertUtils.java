package com.imalu.alyou.net;

/**
 * Internal class, used to make some asserts, throw AssertError if asserts fail.
 */
class AssertUtils {

    private AssertUtils() {
    }

    public static void asserts(final boolean expression, final String failedMessage) {
        if (!expression) {
            throw new AssertionError(failedMessage);
        }
    }
}
