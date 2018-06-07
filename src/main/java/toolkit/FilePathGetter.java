package toolkit;

import org.springframework.util.ClassUtils;

/**
 * @author zhull
 * @date 2018/6/7
 * <P>获取本地路径</P>
 */
public class FilePathGetter {

    public static void main(String[] args) {
        // 推荐使用排列靠前的方法
        // 输出到包路径
        System.out.println(FilePathGetter.class.getResource("").getPath());
        // 以下输出结果相同，取到classes根路径
        System.out.println(FilePathGetter.class.getResource("/").getPath());
        System.out.println(ClassLoader.getSystemResource("").getPath());
        System.out.println(FilePathGetter.class.getClassLoader().getResource("").getPath());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
    }
}
