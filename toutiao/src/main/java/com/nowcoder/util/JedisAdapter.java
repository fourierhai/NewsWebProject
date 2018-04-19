package com.nowcoder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;
@Service
public class JedisAdapter implements InitializingBean{
    private JedisPool pool;
    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);


    public static void main(String[] args){
        Jedis jedis = new Jedis();
        //Sorted Set

        String rankKey = "rankKey";
        jedis.zadd(rankKey,68,"jim");
        jedis.zadd(rankKey,90,"Ben");
        jedis.zadd(rankKey,32,"Lee");
        jedis.zadd(rankKey,80,"Mei");
        jedis.zadd(rankKey,54,"Lucy");
        System.out.println(jedis.zcard(rankKey));
        System.out.println(jedis.zcount(rankKey,60,100));
        System.out.println(jedis.zscore(rankKey,"jim"));
        //对key里面某个元素增加多少分
        jedis.zincrby(rankKey,-5,"Ben");
        System.out.println(jedis.zscore(rankKey,"Ben"));

        //打印倒数第二名到第四名（索引从0开始，升序）
        System.out.println(jedis.zrange(rankKey,1,3));

        //打印第一名到第四名，降序
        System.out.println(jedis.zrevrange(rankKey,0,3));

        //打印所有元素，带分数
        for(Tuple tuple:jedis.zrangeByScoreWithScores(rankKey,0,100)){
            System.out.println(tuple.getElement()+":"+tuple.getScore());
        }

        //找到某个元素在排行榜里是第几名
        System.out.println(jedis.zrank(rankKey,"Mei"));
        System.out.println(jedis.zrevrank(rankKey,"Mei"));


        JedisPool pool = new JedisPool();
        for(int i = 0; i<100;i++){
            Jedis j = pool.getResource();
            j.get("a");
            System.out.println("pool "+ i);
            j.close();  // 如果不关闭的话，默认是8条线程
        }







    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("localhost",6379);
    }
    private Jedis getJedis(){
        return pool.getResource();
    }
    //用set来存放 赞和踩
    public long sadd(String key,String value){
        Jedis jedis = null;
        try {
            jedis=pool.getResource();
            return jedis.sadd(key,value);
        }catch (Exception e){
            logger.error("发送异常",e.getMessage());
            return 0;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }
    // 取消赞 和 踩
    public long srem(String key,String value){
        Jedis jedis = null;
        try {
            jedis=pool.getResource();
            return jedis.srem(key,value);
        }catch (Exception e){
            logger.error("发送异常",e.getMessage());
            return 0;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    //要知道是否点赞 或 踩了

    // 取消赞 和 踩
    public boolean sismember(String key,String value){
        Jedis jedis = null;
        try {
            jedis=pool.getResource();
            return jedis.sismember(key,value);
        }catch (Exception e){
            logger.error("发送异常",e.getMessage());
            return false;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    //点赞的人数
    public long scard(String key){
        Jedis jedis = null;
        try {
            jedis=pool.getResource();
            return jedis.scard(key);
        }catch (Exception e){
            logger.error("发送异常",e.getMessage());
            return 0;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

}
