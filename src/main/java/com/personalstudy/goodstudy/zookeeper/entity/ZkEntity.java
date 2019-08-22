package com.personalstudy.goodstudy.zookeeper.entity;

import org.apache.zookeeper.Watcher;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  10:34 2019-08-20
 * @Description :
 */
public class ZkEntity {

    private String connectString;
    private int sessionTimeout;
    private Watcher watcher;

    public ZkEntity() {
    }

    public ZkEntity(String connectString, int sessionTimeout, Watcher watcher) {
        this.connectString = connectString;
        this.sessionTimeout = sessionTimeout;
        this.watcher = watcher;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Watcher getWatcher() {
        return watcher;
    }

    public void setWatcher(Watcher watcher) {
        this.watcher = watcher;
    }
}
