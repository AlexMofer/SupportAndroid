package com.am.tool.support.utils;

import java.util.UUID;

/**
 * Long工具
 * Created by Alex on 2022/5/21.
 */
public class LongUtils {

    private LongUtils() {
        //no instance
    }

    /**
     * 随机获取一个唯一Long
     *
     * @param least 最少的
     * @return 唯一Long
     */
    public static long random(boolean least) {
        return least ? UUID.randomUUID().getLeastSignificantBits() :
                UUID.randomUUID().getMostSignificantBits();
    }

    /**
     * 添加到ID
     *
     * @param id   ID
     * @param data 数据
     * @return ID
     */
    public static long addTo(long id, String data) {
        final int len = data == null ? 0 : data.length();
        for (int i = 0; i < len; i++) {
            id = 31 * id + data.charAt(i);
        }
        return id;
    }
}
