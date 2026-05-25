package hsf302.lab02.services;

import hsf302.lab02.pojo.Student;
import org.springframework.stereotype.Service;

// TODO 3.1: @Service — đánh dấu class là Spring service bean
@Service
public class StudentServiceImpl implements StudentService {

    // TODO 3.2: addStudent() — in ra "Added student: " + student.getName()
    @Override
    public void addStudent(Student student) {
        System.out.println("Added student: " + student.getName());
    }

    // TODO 3.3: getStudentInfo() — trả về student.toString() (không được trả về null)
    @Override
    public String getStudentInfo(Student student) {
        return student.toString();
    }
}
