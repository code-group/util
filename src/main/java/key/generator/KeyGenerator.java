package key.generator;

import org.apache.commons.lang.time.FastDateFormat;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: 业务主键生成器</P>
 * <p>
 * 1. UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器 都是唯一的。
 * 按照开放软件基金会(OSF)制定的标准计算，用到了以太网卡地址、纳秒级时间、芯片ID码等数字。
 *
 * 2. 自定义主键(40位): yyyyMMddhhmmsss(15位系统时间) + aaabbbcccddd(12位ip地址) + aabbccdd(8位mac地址)+ xyz(5位计数)
 *
 */
public class KeyGenerator {
    /**
     * --------------------- 1. UUID ---------------------
     **/
    private static final int MAX = 99999;

    /**--------------------- 2. 自定义主键 ---------------------**/
    /**
     * 日期格式
     */
    private static final String DATE_FORMAT = "yyyyMMddHHmmsss";
    /**
     * IP地址
     */
    private static String IP;
    /**
     * MAC地址
     */
    private static String MAC;
    /**
     * 计数
     */
    private static AtomicInteger COUNT;

    static {
        String ip;
        InetAddress ia;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            MAC = getLocalMac(InetAddress.getLocalHost());
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
            ip = "127.0.0.1";
        }

        StringBuffer sb = new StringBuffer();
        String[] ipSegs = ip.split("\\.");
        for (String seg : ipSegs) {
            Integer iSeg = Integer.valueOf(seg);
            sb.append(String.format("%03d", iSeg));
        }
        IP = sb.toString();
        COUNT = new AtomicInteger(0);

    }

    /**
     * <P>date:        2018/1/31</P>
     * <P>description: 用UUID生成主键长度为32位，由数字、字母组成</P>
     *
     * @return String 字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getNextId() {
        String systemTime = FastDateFormat.getInstance(DATE_FORMAT).format(new Date());
        StringBuffer timeStamp = new StringBuffer(systemTime);
        timeStamp.append(IP).append(MAC).append(getCOUNT());
        return timeStamp.toString();
    }

    private static String getLocalMac(InetAddress ia) throws SocketException {
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mac.length; i++) {
            //字节转换为整数
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            if (str.length() == 1) {
                sb.append("0");
            }
            sb.append(str);
        }
        return sb.toString().toUpperCase();
    }

    private static String getCOUNT() {
        int num = COUNT.incrementAndGet();
        if (num > MAX) {
            if (COUNT.compareAndSet(num, 0)) {
                num = COUNT.get();
            } else {
                num = COUNT.incrementAndGet();
            }
        }
        return String.format("%03d", num);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            System.out.println(getNextId());
        }
    }
}
