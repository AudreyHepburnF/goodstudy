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
        PING, SET, GET, QUIT, EXISTS, DEL, TYPE, FLUSHDB, KEYS, RANDOMKEY, RENAME, RENAMENX, RENAMEX, DBSIZE, EXPIRE, EXPIREAT, TTL, SELECT, MOVE, FLUSHALL, GETSET, MGET, SETNX, SETEX, MSET, MSETNX, DECRBY, DECR, INCRBY, INCR, APPEND, SUBSTR, HSET, HGET, HSETNX, HMSET, HMGET, HINCRBY, HEXISTS, HDEL, HLEN, HKEYS, HVALS, HGETALL, RPUSH, LPUSH, LLEN, LRANGE, LTRIM, LINDEX, LSET, LREM, LPOP, RPOP, RPOPLPUSH, SADD, SMEMBERS, SREM, SPOP, SMOVE, SCARD, SISMEMBER, SINTER, SINTERSTORE, SUNION, SUNIONSTORE, SDIFF, SDIFFSTORE, SRANDMEMBER, ZADD, ZRANGE, ZREM, ZINCRBY, ZRANK, ZREVRANK, ZREVRANGE, ZCARD, ZSCORE, MULTI, DISCARD, EXEC, WATCH, UNWATCH, SORT, BLPOP, BRPOP, AUTH, SUBSCRIBE, PUBLISH, UNSUBSCRIBE, PSUBSCRIBE, PUNSUBSCRIBE, PUBSUB, ZCOUNT, ZRANGEBYSCORE, ZREVRANGEBYSCORE, ZREMRANGEBYRANK, ZREMRANGEBYSCORE, ZUNIONSTORE, ZINTERSTORE, ZLEXCOUNT, ZRANGEBYLEX, ZREVRANGEBYLEX, ZREMRANGEBYLEX, SAVE, BGSAVE, BGREWRITEAOF, LASTSAVE, SHUTDOWN, INFO, MONITOR, SLAVEOF, CONFIG, STRLEN, SYNC, LPUSHX, PERSIST, RPUSHX, ECHO, LINSERT, DEBUG, BRPOPLPUSH, SETBIT, GETBIT, BITPOS, SETRANGE, GETRANGE, EVAL, EVALSHA, SCRIPT, SLOWLOG, OBJECT, BITCOUNT, BITOP, SENTINEL, DUMP, RESTORE, PEXPIRE, PEXPIREAT, PTTL, INCRBYFLOAT, PSETEX, CLIENT, TIME, MIGRATE, HINCRBYFLOAT, SCAN, HSCAN, SSCAN, ZSCAN, WAIT, CLUSTER, ASKING, PFADD, PFCOUNT, PFMERGE, READONLY, GEOADD, GEODIST, GEOHASH, GEOPOS, GEORADIUS, GEORADIUSBYMEMBER, BITFIELD;
    }
}
