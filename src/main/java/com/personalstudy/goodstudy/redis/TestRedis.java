package com.personalstudy.goodstudy.redis;

import com.personalstudy.goodstudy.redis.client.Client;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  14:31 2019-08-13
 * @Description :
 */
public class TestRedis {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("152.136.16.111", 6379);
        System.out.println(jedis.set("xyl", "123"));
        System.out.println(jedis.get("xyl"));
    }

    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.get("test"));
    }

    @Test
    public void testRedis1(){
        Client client = new Client("152.136.16.111", 6379);
//        System.out.println(client.set("xyl", "lcp"));
        System.out.println(client.get("xyl"));
    }
}
