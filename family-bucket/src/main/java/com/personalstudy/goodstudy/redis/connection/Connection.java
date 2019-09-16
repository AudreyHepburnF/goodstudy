package com.personalstudy.goodstudy.redis.connection;

import com.personalstudy.goodstudy.redis.protocol.Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  14:45 2019-08-13
 * @Description : 传输层
 */
public class Connection {

    private Socket socket;

    private String host;

    private int port;

    private OutputStream outputStream;

    private InputStream inputStream;

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // 建立连接
    public Connection connection(){
        try {
            if(!isConnected()){
                socket = new Socket(host , port);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    // 发送命令
    public Connection sendCommand(Protocol.Command command , byte[]... args){
        this.connection();
        Protocol.sendCommand(outputStream, command, args);
        return this;
    }

    // 返回数据
    public String getStatusCoderReply(){
        byte[] bytes = new byte[1024];
        try {
            socket.getInputStream().read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }

    public boolean isConnected() {
        return this.socket != null && this.socket.isBound() && !this.socket.isClosed() && this.socket.isConnected() && !this.socket.isInputShutdown() && !this.socket.isOutputShutdown();
    }
}
