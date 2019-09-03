package com.personalstudy.goodstudy.util;

import java.util.UUID;

public class UUIDUtils {
    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
