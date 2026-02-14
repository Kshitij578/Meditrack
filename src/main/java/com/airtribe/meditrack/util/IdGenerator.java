package com.airtribe.meditrack.util;

import java.util.UUID;

public class IdGenerator {

    private static IdGenerator instance;

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        if (instance == null) {
            synchronized (IdGenerator.class) {
                if (instance == null) {
                    instance = new IdGenerator();
                }
            }
        }
        return instance;
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
