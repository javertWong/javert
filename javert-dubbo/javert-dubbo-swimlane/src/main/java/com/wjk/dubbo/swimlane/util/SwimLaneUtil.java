package com.wjk.dubbo.swimlane.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author junkai.wang
 * @description TODO
 * @date 2023/5/9 19:25
 */
public class SwimLaneUtil {
    private static Boolean FLAG = false;
    private static String SWIM_LANE_NO = null;
    private static String SWIM_LOG = "true";
    private static final Logger logger = LoggerFactory.getLogger(SwimLaneUtil.class);

    public SwimLaneUtil() {
    }

    private static String getHostsSwimLaneNo(String filePath) {
        File file = new File(filePath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Throwable throwable = null;

            try {
                String line = null;

                do {
                    if ((line = br.readLine()) == null) {
                        return null;
                    }
                } while (!line.contains("SWIM_LANE_NO"));

                String swimlane = line.replace("SWIM_LANE_NO=", "");
                return swimlane;
            } catch (Throwable t) {
                throwable = t;
                throw t;
            } finally {
                if (br != null) {
                    if (throwable != null) {
                        try {
                            br.close();
                        } catch (Throwable t) {
                            throwable.addSuppressed(t);
                        }
                    } else {
                        br.close();
                    }
                }

            }
        } catch (IOException e) {
            logger.error("swimlane load balance io error:", e);
            return null;
        }
    }

    public static String getSwimLaneNo() {
        String swimLaneNo = null;
        String swimLog = null;
        if (!FLAG) {
            swimLaneNo = System.getenv("SWIM_LANE_NO");
            swimLog = System.getenv("SWIM_LOG");
            String osName = System.getProperty("os.name");
            String fileName;
            if ("linux".equalsIgnoreCase(osName)) {
                fileName = "/etc/hosts";
            } else {
                fileName = "C://WINDOWS//system32//drivers//etc//hosts";
            }

            if (StringUtils.isEmpty(swimLaneNo)) {
                swimLaneNo = getHostsSwimLaneNo(fileName);
            }

            FLAG = true;
            SWIM_LANE_NO = swimLaneNo;
            SWIM_LOG = swimLog;
        } else {
            swimLaneNo = SWIM_LANE_NO;
        }

        return swimLaneNo;
    }

    public static String getSwimLog() {
        return SWIM_LOG;
    }
}
