package com.personalstudy.goodstudy.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  17:26 2019-08-19
 * @Description :
 */
public class ZkTest {

    @Test
    public void test1() throws IOException, KeeperException, InterruptedException {
        String host = "192.168.5.33:2181";
        int sessionTimeout = 5000;
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
            }
        };
        ZooKeeper zooKeeper = new ZooKeeper(host, sessionTimeout, watcher);
//        List<ACL> list = new ArrayList<>();
//        String create = zooKeeper.create("/fruit/orange", "yellow".getBytes(), list, CreateMode.EPHEMERAL_SEQUENTIAL);
//        Stat stat = zooKeeper.setData("/fruit", "data".getBytes(), 0);
//        System.out.println("当前版本信息：" + stat.getVersion());

        byte[] resultBytes = zooKeeper.getData("/fruit", new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("/fruit节点的值被修改了！" + Thread.currentThread().getName());
            }
        }, new Stat());
        System.out.println("获取fruit的结果 ： " + new String(resultBytes));

        // ※让程序不能停
        while(true) {
            Thread.sleep(5000);
            System.err.println("药不能停，不能停……[代表程序原本要执行的业务逻辑功能……]"+Thread.currentThread().getName());
        }
    }
}
