package aop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: 启动类，扫包</P>
 */
@SpringBootApplication(scanBasePackages = {"aop"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
