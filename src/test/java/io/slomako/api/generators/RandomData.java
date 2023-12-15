package io.slomako.api.generators;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

    private static final int LENGTH = 10;

    public static String randomString() {
        return "test" + RandomStringUtils.randomAlphabetic(LENGTH);
    }
}
