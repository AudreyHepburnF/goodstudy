package com.personalstudy.goodstudy.redis.client;

import com.personalstudy.goodstudy.redis.connection.Connection;
import com.personalstudy.goodstudy.redis.protocol.Protocol;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  14:49 2019-08-13
 * @Description : api
 */
public class Client {

    private Connection connection;

    public Client(String host , int port) {
        this.connection = new Connection(host, port);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String set(String key , String value){
        connection.sendCommand(Protocol.Command.SET , key.getBytes() , value.getBytes());
        return connection.getStatusCoderReply();
    }

    public String get(String key){
        connection.sendCommand(Protocol.Command.GET , key.getBytes());
        return connection.getStatusCoderReply();
    }
}
