package hsf302.lab02.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// TODO 1.1: @Configuration — đánh dấu class là Spring configuration class
// TODO 1.2: @ComponentScan — scan component trong package "hsf302.lab02"
// TODO 1.3: @EnableAspectJAutoProxy — bật AspectJ auto proxy để AOP hoạt động
@Configuration
@ComponentScan("hsf302.lab02")
@EnableAspectJAutoProxy
public class AppConfig {
    // Không cần thêm gì — annotation đã đủ
}
