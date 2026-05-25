package hsf302.lab02;

import hsf302.lab02.pojo.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task02_StudentPojoTest {

    @Test
    public void testConstructorAndGetters() {
        Student s = new Student("Alice", 20);
        assertEquals("Alice", s.getName());
        assertEquals(20, s.getAge());
    }

    @Test
    public void testSetName() {
        Student s = new Student("Alice", 20);
        s.setName("Bob");
        assertEquals("Bob", s.getName());
    }

    @Test
    public void testSetAge() {
        Student s = new Student("Alice", 20);
        s.setAge(25);
        assertEquals(25, s.getAge());
    }

    @Test
    public void testFieldName() throws NoSuchFieldException {
        java.lang.reflect.Field f = Student.class.getDeclaredField("name");
        assertEquals(String.class, f.getType());
    }

    @Test
    public void testFieldAge() throws NoSuchFieldException {
        java.lang.reflect.Field f = Student.class.getDeclaredField("age");
        assertEquals(int.class, f.getType());
    }

    @Test
    public void testToString() {
        Student s = new Student("Alice", 20);
        String str = s.toString();
        assertNotNull(str);
        assertTrue(str.contains("Alice"));
        assertTrue(str.contains("20"));
    }
}
