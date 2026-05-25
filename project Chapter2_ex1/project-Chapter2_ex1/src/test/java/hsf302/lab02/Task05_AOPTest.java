package hsf302.lab02;

import hsf302.lab02.aspects.LoggingAspect;
import hsf302.lab02.configs.AppConfig;
import hsf302.lab02.pojo.Student;
import hsf302.lab02.services.StudentService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Task05_AOPTest {

    private AnnotationConfigApplicationContext ctx;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        studentService = ctx.getBean(StudentService.class);
    }

    @Test
    public void testAspectAnnotationPresent() {
        assertTrue(LoggingAspect.class.isAnnotationPresent(Aspect.class),
                "LoggingAspect phải có @Aspect");
    }

    @Test
    public void testComponentAnnotationPresent() {
        assertTrue(LoggingAspect.class.isAnnotationPresent(Component.class),
                "LoggingAspect phải có @Component");
    }

    @Test
    public void testBeforeAdvicePrints() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            studentService.addStudent(new Student("Alice", 20));
        } finally {
            System.setOut(original);
        }
        String output = out.toString();
        assertTrue(output.contains("[BEFORE]"),
                "@Before advice phải in '[BEFORE] ...' khi gọi addStudent");
    }

    @Test
    public void testAfterReturningAdvicePrints() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            studentService.getStudentInfo(new Student("Bob", 22));
        } finally {
            System.setOut(original);
        }
        String output = out.toString();
        assertTrue(output.contains("[AFTER]"),
                "@AfterReturning advice phải in '[AFTER] ...' khi gọi getStudentInfo");
    }

    @Test
    public void testAroundAdvicePrints() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            studentService.addStudent(new Student("Charlie", 19));
        } finally {
            System.setOut(original);
        }
        String output = out.toString();
        assertTrue(output.contains("[ELAPSED]"),
                "@Around advice phải in '[ELAPSED] ...' khi gọi addStudent");
    }

    @Test
    public void testBeforeMethodExists() throws NoSuchMethodException {
        boolean found = false;
        for (java.lang.reflect.Method m : LoggingAspect.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Before.class)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "LoggingAspect phải có ít nhất một method với @Before");
    }

    @Test
    public void testAfterReturningMethodExists() {
        boolean found = false;
        for (java.lang.reflect.Method m : LoggingAspect.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(AfterReturning.class)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "LoggingAspect phải có ít nhất một method với @AfterReturning");
    }

    @Test
    public void testAroundMethodExists() {
        boolean found = false;
        for (java.lang.reflect.Method m : LoggingAspect.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Around.class)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "LoggingAspect phải có ít nhất một method với @Around");
    }
}
