package com.nowcoder.util;


public class RedisKeyUtil {
    private static String SPLIT =":";
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_DISLIKE = "DISLIKE";
    // 赞 的 key 命名
    public static String getLikeKey(int entityId, int enetityType){
        return BIZ_LIKE+SPLIT+String.valueOf(enetityType)+SPLIT+String.valueOf(entityId);
    }
    // 踩 的 key 命名
    public static String getDisLikeKey(int entityId, int enetityType){
        return BIZ_DISLIKE+SPLIT+String.valueOf(enetityType)+SPLIT+String.valueOf(entityId);
    }

}
