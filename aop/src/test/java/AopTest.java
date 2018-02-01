import aop.app.Application;
import aop.app.Something;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: AOP测试类</P>
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AopTest {

    @Resource
    private Something something;

    /**
     * 测试类不是SpringBean，AOP对测试类的方法不生效
     */
    @Test
    public void test() {
        System.out.println("进入测试方法");
        something.enableAop("a");
        something.unableAop();
    }

}
