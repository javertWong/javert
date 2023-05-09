package com.wjk.dubbo.swimlane.registry;


import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.support.AbstractRegistryFactory;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;

/**
 * @author junkai.wang
 * @description TODO
 * @date 2023/5/9 19:36
 */
public class CustomizeZookeeperRegistryFactory extends AbstractRegistryFactory {
    private ZookeeperTransporter zookeeperTransporter;

    public void setZookeeperTransporter(ZookeeperTransporter zookeeperTransporter) {
        this.zookeeperTransporter = zookeeperTransporter;
    }

    @Override
    public Registry createRegistry(URL url) {
        return new CustomizeZookeeperRegistry(url, this.zookeeperTransporter);
    }
}
