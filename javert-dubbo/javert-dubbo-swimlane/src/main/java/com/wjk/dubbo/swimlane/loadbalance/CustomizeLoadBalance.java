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
            logger.info("load balance start, interface:" + ((Invoker) invokers.get(0)).getInterface().getName() + ", all hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
        }

        List<Invoker<T>> mainLaneInvokers = new ArrayList();
        Iterator iterator = invokers.iterator();

        while (iterator.hasNext()) {
            Invoker<T> invoker = (Invoker) iterator.next();
            if (StringUtils.isEmpty(invoker.getUrl().getParameter("SWIM_LANE_NO"))) {
                mainLaneInvokers.add(invoker);
            }
        }

        if (StringUtils.isEmpty(swimLaneNo)) {
            hostList = mainLaneInvokers.stream().map(Node::getUrl).collect(Collectors.toList()).stream().map(URL::getHost).collect(Collectors.toList());
            if (StringUtils.isEmpty(swimLog) || !"false".equals(swimLog)) {
                logger.info("load balance end, interface:" + ((Invoker) invokers.get(0)).getInterface().getName() + ", optional hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
            }

            return (new RandomLoadBalance()).select(mainLaneInvokers, url, invocation);
        } else {
            List<Invoker<T>> thisLaneInvokers = new ArrayList();
            Iterator invokerIterator = invokers.iterator();

            while (invokerIterator.hasNext()) {
                Invoker<T> invoker = (Invoker) invokerIterator.next();
                if (swimLaneNo.equals(invoker.getUrl().getParameter("SWIM_LANE_NO"))) {
                    thisLaneInvokers.add(invoker);
                }
            }

            if (thisLaneInvokers.size() == 0) {
                hostList = mainLaneInvokers.stream().map(Node::getUrl).collect(Collectors.toList()).stream().map(URL::getHost).collect(Collectors.toList());
                if (StringUtils.isEmpty(swimLog) || !"false".equals(swimLog)) {
                    logger.info("load balance end, interface:" + ((Invoker) invokers.get(0)).getInterface().getName() + ", optional hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
                }

                return (new RandomLoadBalance()).select(mainLaneInvokers, url, invocation);
            } else {
                hostList = thisLaneInvokers.stream().map(Node::getUrl).collect(Collectors.toList()).stream().map(URL::getHost).collect(Collectors.toList());
                if (StringUtils.isEmpty(swimLog) || !"false".equals(swimLog)) {
                    logger.info("load balance end, interface:" + ((Invoker) invokers.get(0)).getInterface().getName() + ", optional hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
                }

                return (new RandomLoadBalance()).select(thisLaneInvokers, url, invocation);
            }
        }
    }

}
