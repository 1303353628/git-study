package zhan.wang.config;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;

@Log4j2
public class EnvConfig {

    private EnvConfig() {
        // private constructor
    }

    /**
     * 当前主机名
     */
    public static final String HOSTNAME = genHostname();

    /**
     * 当前主机IP的最后一位，不足3位补零
     */
    public static final String HOST_IP = getHostIp();

    private static String genHostname() {
        String hostname = "Unknown";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            log.warn("获取机器名出错", e);
        }
        return hostname;
    }

    private static String getHostIp() {
        String hostip = "000";
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            hostip = StringUtils.substring(ip, ip.lastIndexOf('.') + 1);
            if (hostip.length() == 1) {
                hostip += "00";
            } else if (hostip.length() == 2) {
                hostip += "0";
            }
        } catch (Exception e) {
            log.warn("获取机器IP地址出错", e);
        }
        return hostip;
    }
}
