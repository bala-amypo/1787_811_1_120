package com.example.demo.util;

import java.util.UUID;

public class ApiKeyGenerator {

    private ApiKeyGenerator() {}

    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
