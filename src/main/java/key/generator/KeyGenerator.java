package key.generator;

import java.util.UUID;

/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: 业务主键生成器</P>
 * <p>
 * UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器 都是唯一的。
 * 按照开放软件基金会(OSF)制定的标准计算，用到了以太网卡地址、纳秒级时间、芯片ID码等数字。
 */
public class KeyGenerator {

    /**
     * <P>date:        2018/1/31</P>
     * <P>description: 生成主键长度为32位，由数字、字母组成</P>
     *
     * @return String 字符串
     */
    public static String nextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        // eg: 6ea82adcb3d6485fb51c3d97d111e2b3, e2798b3ded684ee6a31ba826a26d4b2c
        System.out.println("nextId: " + KeyGenerator.nextId());
        System.out.println(KeyGenerator.nextId().length());
    }
}
