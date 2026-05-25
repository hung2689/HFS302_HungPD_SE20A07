package hsf302.lab02;

import hsf302.lab02.configs.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class Task01_AppConfigTest {

    @Test
    public void testAppConfigHasConfigurationAnnotation() {
        assertTrue(AppConfig.class.isAnnotationPresent(
                org.springframework.context.annotation.Configuration.class),
                "AppConfig phải có @Configuration");
    }

    @Test
    public void testAppConfigHasComponentScan() {
        assertTrue(AppConfig.class.isAnnotationPresent(
                org.springframework.context.annotation.ComponentScan.class),
                "AppConfig phải có @ComponentScan");
        org.springframework.context.annotation.ComponentScan cs =
                AppConfig.class.getAnnotation(org.springframework.context.annotation.ComponentScan.class);
        boolean found = false;
        for (String pkg : cs.value()) {
            if (pkg.equals("hsf302.lab02")) found = true;
        }
        for (String pkg : cs.basePackages()) {
            if (pkg.equals("hsf302.lab02")) found = true;
        }
        assertTrue(found, "ComponentScan phải scan package 'hsf302.lab02'");
    }

    @Test
    public void testAppConfigHasEnableAspectJAutoProxy() {
        assertTrue(AppConfig.class.isAnnotationPresent(
                org.springframework.context.annotation.EnableAspectJAutoProxy.class),
                "AppConfig phải có @EnableAspectJAutoProxy");
    }

    @Test
    public void testContextLoads() {
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            assertNotNull(ctx, "ApplicationContext phải khởi tạo được");
        }
    }
}
