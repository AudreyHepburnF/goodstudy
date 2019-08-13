package com.personalstudy.goodstudy.redis.protocol;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  15:07 2019-08-13
 * @Description :
 */
public class Protocol {

    public static final String DOLLAR_BYTE = "$";
    public static final String ASTERISK_BYTE = "*";
    public static final String PLUS_BYTE = "+";
    public static final String MINUS_BYTE = "-";
    public static final String COLON_BYTE = ":";
    public static final String BLANK_STRING="\r\n";

    public static void sendCommand(OutputStream os , Command command , byte[] ... args){
        StringBuilder sb = new StringBuilder();
        sb.append(Protocol.ASTERISK_BYTE).append(args.length+1).append(Protocol.BLANK_STRING);
        sb.append(Protocol.DOLLAR_BYTE).append(command.name().length()).append(Protocol.BLANK_STRING);
        sb.append(command.name()).append(Protocol.BLANK_STRING);
        for(final byte[] arg : args){
            sb.append(Protocol.DOLLAR_BYTE).append(arg.length).append(Protocol.BLANK_STRING);
            sb.append(new String(arg)).append(Protocol.BLANK_STRING);
        }
//        System.out.println(sb.toString());
        try {
            os.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static enum Command{
        GET,SET,KEYS
    }
}
