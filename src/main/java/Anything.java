import org.springframework.util.ClassUtils;

/**
 * @author zhull
 * @date 2018/6/7
 * <P></P>
 */
public class Anything {
    public static void main(String[] args) {
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
        System.out.println(Anything.class.getClassLoader().getResource("").getPath());
        System.out.println(ClassLoader.getSystemResource("").getPath());
        System.out.println(Anything.class.getResource("").getPath());
        System.out.println(Anything.class.getResource("/").getPath());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
    }
}
