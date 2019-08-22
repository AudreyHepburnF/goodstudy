package com.personalstudy.goodstudy.redis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  20:54 2019-08-22
 * @Description :
 */
public class LRUCache extends LinkedHashMap {

    private final int CACHE_SIZE;

    public LRUCache(int cacheSize) {
        //true 表示让 linkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
        super((int) Math.ceil(cacheSize/0.75)+1,0.75f,true);
        this.CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        // 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
        return size() > CACHE_SIZE;
    }
}
