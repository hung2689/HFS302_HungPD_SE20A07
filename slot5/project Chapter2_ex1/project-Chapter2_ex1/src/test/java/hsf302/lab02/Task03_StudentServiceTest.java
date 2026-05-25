package hsf302.lab02;

import hsf302.lab02.configs.AppConfig;
import hsf302.lab02.pojo.Student;
import hsf302.lab02.services.StudentService;
import hsf302.lab02.services.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Task03_StudentServiceTest {

    private AnnotationConfigApplicationContext ctx;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        studentService = ctx.getBean(StudentService.class);
    }

    @Test
    public void testServiceBeanExists() {
        assertNotNull(studentService, "StudentService bean phải tồn tại trong context");
    }

    @Test
    public void testImplementsInterface() {
        // StudentService interface phải được implement bởi StudentServiceImpl
        assertTrue(StudentService.class.isAssignableFrom(StudentServiceImpl.class),
                "StudentServiceImpl phải implement StudentService");
    }

    @Test
    public void testAddStudentPrintsCorrectly() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            Student s = new Student("Alice", 20);
            studentService.addStudent(s);
        } finally {
            System.setOut(original);
        }
        String output = out.toString();
        assertTrue(output.contains("Added student: Alice"),
                "addStudent() phải in 'Added student: Alice', nhưng in: " + output);
    }

    @Test
    public void testGetStudentInfoNotNull() {
        Student s = new Student("Bob", 22);
        String info = studentService.getStudentInfo(s);
        assertNotNull(info, "getStudentInfo() không được trả về null");
    }

    @Test
    public void testGetStudentInfoContainsData() {
        Student s = new Student("Charlie", 19);
        String info = studentService.getStudentInfo(s);
        assertTrue(info.contains("Charlie"), "getStudentInfo() phải chứa tên student");
    }

    @Test
    public void testServiceHasServiceAnnotation() {
        assertTrue(StudentServiceImpl.class.isAnnotationPresent(
                org.springframework.stereotype.Service.class),
                "StudentServiceImpl phải có @Service");
    }
}
