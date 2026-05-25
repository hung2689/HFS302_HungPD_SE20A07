package hsf302.lab02;

import hsf302.lab02.components.LifecycleBean;
import hsf302.lab02.configs.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Task04_LifecycleBeanTest {

    @Test
    public void testInitializedAfterContextStart() {
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            LifecycleBean bean = ctx.getBean(LifecycleBean.class);
            assertTrue(bean.isInitialized(), "isInitialized() phải trả về true sau khi context khởi động");
        }
    }

    @Test
    public void testNotInitializedAfterContextClose() {
        LifecycleBean bean;
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            bean = ctx.getBean(LifecycleBean.class);
        }
        assertFalse(bean.isInitialized(), "isInitialized() phải trả về false sau khi context đóng");
    }

    @Test
    public void testPostConstructPrintsMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            // context khởi động → @PostConstruct chạy
        } finally {
            System.setOut(original);
        }
        String output = out.toString();
        assertTrue(output.contains("[LIFECYCLE] LifecycleBean initialized"),
                "@PostConstruct phải in '[LIFECYCLE] LifecycleBean initialized'");
    }

    @Test
    public void testPreDestroyPrintsMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            // context đóng → @PreDestroy chạy
        } finally {
            System.setOut(original);
        }
        String output = out.toString();
        assertTrue(output.contains("[LIFECYCLE] LifecycleBean destroyed"),
                "@PreDestroy phải in '[LIFECYCLE] LifecycleBean destroyed'");
    }

    @Test
    public void testHasPostConstructAnnotation() throws NoSuchMethodException {
        assertTrue(LifecycleBean.class.getMethod("init")
                        .isAnnotationPresent(jakarta.annotation.PostConstruct.class),
                "init() phải có @PostConstruct");
    }

    @Test
    public void testHasPreDestroyAnnotation() throws NoSuchMethodException {
        assertTrue(LifecycleBean.class.getMethod("cleanup")
                        .isAnnotationPresent(jakarta.annotation.PreDestroy.class),
                "cleanup() phải có @PreDestroy");
    }
}
