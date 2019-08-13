package com.personalstudy.goodstudy.redis.hack;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  14:54 2019-08-13
 * @Description : 拦截Redis的测试类
 */
public class RedisHack {

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(6379);
            Socket accept = socket.accept();
            byte[] bytes = new byte[1024];
            accept.getInputStream().read(bytes);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
