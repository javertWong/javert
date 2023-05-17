package com.wjk.dubbo.swimlane.registry;

import com.wjk.dubbo.swimlane.util.SwimLaneUtil;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.zookeeper.ZookeeperRegistry;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;

/**
 * @author junkai.wang
 * @description TODO
 * @date 2023/5/9 19:33
 */
public class CustomizeZookeeperRegistry extends ZookeeperRegistry {

    public CustomizeZookeeperRegistry(URL url, ZookeeperTransporter zookeeperTransporter) {
        super(url, zookeeperTransporter);
    }

    @Override
    public void doRegister(URL url) {
        String swimLaneNo = SwimLaneUtil.getSwimLaneNo();
        if (url.getParameter("SWIM_LANE_NO") == null) {
            url = url.addParameter("SWIM_LANE_NO", swimLaneNo);
        }

        super.doRegister(url);
    }
}
