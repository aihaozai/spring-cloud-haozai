package spring.cloud.base.datasource.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;

/**
 * @author network
 * @date 2021/7/27
 */
public class SnowflakeUtil {

    /**
     * 开始时间截
     */
    private final static long TWEPOCH = 1288834974657L;

    /**
     * 机器标识位数
     */
    private final static long WORKER_ID_BITS = 5L;

    /**
     * 数据中心标识位数
     */
    private final static long DATA_CENTER_ID_BITS = 5L;

    /**
     * 机器ID最大值 31
     */
    private final static long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 数据中心ID最大值 31
     */
    private final static long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /**
     * 毫秒内自增位
     */
    private final static long SEQUENCE_BITS = 12L;

    /**
     * 机器ID偏左移12位
     */
    private final static long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final static long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间毫秒左移22位
     */
    private final static long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final static long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 上次时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;

    private static volatile SnowflakeUtil snowflake = null;

    private static final Object LOCK = new Object();

    /**
     * 单例禁止new实例化
     * @param workerId 工作机器ID
     * @param dataCenterId 数据中心ID
     */
    private SnowflakeUtil(long workerId, long dataCenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            workerId = getRandom();
        }

        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {

            throw new IllegalArgumentException(String.format("%s 数据中心ID最大值 必须是 %d 到 %d 之间", dataCenterId, 0, MAX_DATA_CENTER_ID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获取单列
     * @return Snowflake
     */
    private static SnowflakeUtil getInstanceSnowflake() {
        if (snowflake == null) {
            synchronized (LOCK) {
                long workerId ;
                long dataCenterId = getRandom();
                try {
                    //第一次使用获取mac地址的
                    workerId = getWorkerId();
                } catch (Exception e) {
                    workerId = getRandom();
                }
                snowflake = new SnowflakeUtil(workerId, dataCenterId);
            }
        }
        return snowflake;
    }

    /**
     * 生成1-31之间的随机数
     * @return long
     */
    private static long getRandom() {
        int max = (int) (MAX_WORKER_ID);
        int min = 1;
        Random random = new Random();
        return (long) (random.nextInt(max - min) + min);
    }

    public static String getSnowflakeId() throws Exception{
        return SnowflakeUtil.getInstanceSnowflake().nextId()+"";
    }

    private synchronized long nextId() throws Exception {
        long timestamp = time();
        if (timestamp < lastTimestamp) {
            throw new Exception("时钟向后移动，拒绝生成id  " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;

        // ID偏移组合生成最终的ID，并返回ID

        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (dataCenterId << DATA_CENTER_ID_SHIFT) | (workerId << WORKER_ID_SHIFT) | sequence;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.time();
        while (timestamp <= lastTimestamp) {
            timestamp = this.time();
        }
        return timestamp;
    }

    private long time() {
        return System.currentTimeMillis();
    }

    @SuppressWarnings("Duplicates")
    private static long getWorkerId() throws SocketException, UnknownHostException, NullPointerException {
        @SuppressWarnings("unused")
        InetAddress ip = InetAddress.getLocalHost();

        NetworkInterface network = null;
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        while (en.hasMoreElements()) {
            NetworkInterface nint = en.nextElement();
            if (!nint.isLoopback() && nint.getHardwareAddress() != null) {
                network = nint;
                break;
            }
        }

        @SuppressWarnings("ConstantConditions")
        byte[] mac = network.getHardwareAddress();

        Random rnd = new Random();
        byte rndByte = (byte) (rnd.nextInt() & 0x000000FF);

        // 取mac地址最后一位和随机数
        return ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) rndByte) << 8))) >> 6;
    }
}
