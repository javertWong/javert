package com.wjk.dubbo.swimlane.loadbalance;

/**
 * @author junkai.wang
 * @description TODO
 * @date 2023/5/9 19:16
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.wjk.dubbo.swimlane.util.SwimLaneUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.Node;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomizeLoadBalance extends AbstractLoadBalance {
    private static final Logger logger = LoggerFactory.getLogger(CustomizeLoadBalance.class);

    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        String swimLaneNo = SwimLaneUtil.getSwimLaneNo();
        String swimLog = SwimLaneUtil.getSwimLog();
        List<String> hostList = invokers.stream().map(Node::getUrl).collect(Collectors.toList()).stream().map(URL::getHost).collect(Collectors.toList());
        if (StringUtils.isEmpty(swimLog) || !"false".equals(swimLog)) {
            logger.info("load balance start, interface:" + ((Invoker)invokers.get(0)).getInterface().getName() + ", all hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
        }

        List<Invoker<T>> mainLaneInvokers = new ArrayList();
        Iterator var8 = invokers.iterator();

        while(var8.hasNext()) {
            Invoker<T> invoker = (Invoker)var8.next();
            if (StringUtils.isEmpty(invoker.getUrl().getParameter("SWIM_LANE_NO"))) {
                mainLaneInvokers.add(invoker);
            }
        }

        if (StringUtils.isEmpty(swimLaneNo)) {
            hostList = mainLaneInvokers.stream().map(Node::getUrl).collect(Collectors.toList()).stream().map(URL::getHost).collect(Collectors.toList());
            if (StringUtils.isEmpty(swimLog) || !"false".equals(swimLog)) {
                logger.info("load balance end, interface:" + ((Invoker)invokers.get(0)).getInterface().getName() + ", optional hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
            }

            return (new RandomLoadBalance()).select(mainLaneInvokers, url, invocation);
        } else {
            List<Invoker<T>> thisLaneInvokers = new ArrayList();
            Iterator var12 = invokers.iterator();

            while(var12.hasNext()) {
                Invoker<T> invoker = (Invoker)var12.next();
                if (swimLaneNo.equals(invoker.getUrl().getParameter("SWIM_LANE_NO"))) {
                    thisLaneInvokers.add(invoker);
                }
            }

            if (thisLaneInvokers.size() == 0) {
                hostList = mainLaneInvokers.stream().map(Node::getUrl).collect(Collectors.toList()).stream().map(URL::getHost).collect(Collectors.toList());
                if (StringUtils.isEmpty(swimLog) || !"false".equals(swimLog)) {
                    logger.info("load balance end, interface:" + ((Invoker)invokers.get(0)).getInterface().getName() + ", optional hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
                }

                return (new RandomLoadBalance()).select(mainLaneInvokers, url, invocation);
            } else {
                hostList = thisLaneInvokers.stream().map(Node::getUrl).collect(Collectors.toList()).stream().map(URL::getHost).collect(Collectors.toList());
                if (StringUtils.isEmpty(swimLog) || !"false".equals(swimLog)) {
                    logger.info("load balance end, interface:" + ((Invoker)invokers.get(0)).getInterface().getName() + ", optional hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
                }

                return (new RandomLoadBalance()).select(thisLaneInvokers, url, invocation);
            }
        }
    }

    private String getHostsSwimLaneNo(String filePath) {
        File file = new File(filePath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Throwable var4 = null;

            try {
                String line = null;

                do {
                    if ((line = br.readLine()) == null) {
                        return null;
                    }
                } while(!line.contains("SWIM_LANE_NO"));

                String var6 = line.replace("SWIM_LANE_NO=", "");
                return var6;
            } catch (Throwable var17) {
                var4 = var17;
                throw var17;
            } finally {
                if (br != null) {
                    if (var4 != null) {
                        try {
                            br.close();
                        } catch (Throwable var16) {
                            var4.addSuppressed(var16);
                        }
                    } else {
                        br.close();
                    }
                }

            }
        } catch (IOException var19) {
            logger.error("swimlane load balance io error:", var19);
            return null;
        }
    }

}
