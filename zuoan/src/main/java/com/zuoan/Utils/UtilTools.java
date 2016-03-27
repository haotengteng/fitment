package com.zuoan.Utils;

import java.util.UUID;

/**
 * Created by haotengteng on 2016/3/27.
 */
public class UtilTools {
    public static String UUIDUtil() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
