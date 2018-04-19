package com.nowcoder.service;

import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    JedisAdapter jedisAdapter;

    /**
     * 某个用户对某个元素是否喜欢,如果不喜欢 返回-1，否则返回0
     *
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */

    public int getLikeStatus(int userId, int entityType, int entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        //判断 set 里是否有这个userId的 赞
        if (jedisAdapter.sismember(likeKey, String.valueOf(userId))) {
            return 1;
        }

        String dislikekey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        // 判断 set 里是否有这个userId的踩，有 返回-1， 没有返回0
        if (jedisAdapter.sismember(dislikekey, String.valueOf(userId))) {
            return -1;
        } else {
            return 0;
        }
    }

    public long like(int userId, int entityType, int entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        //如果赞，就把用户的id 加入 set里
        jedisAdapter.sadd(likeKey, String.valueOf(userId));

        //要把用户从 踩 的集合里删掉
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        jedisAdapter.srem(disLikeKey, String.valueOf(userId));
        // 返回点赞的人数
        return  jedisAdapter.scard(likeKey);
    }

    public long dislike(int userId, int entityType, int entityId) {
        String dislikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        //如果踩，就把用户的id 加入 set里
        jedisAdapter.sadd(dislikeKey, String.valueOf(userId));

        //要把用户从 赞 的集合里删掉
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        jedisAdapter.srem(likeKey, String.valueOf(userId));
        // 返回点赞的人数
        return  jedisAdapter.scard(likeKey);
    }
}

