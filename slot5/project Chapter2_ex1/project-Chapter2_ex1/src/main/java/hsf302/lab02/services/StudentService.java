package hsf302.lab02.services;

import hsf302.lab02.pojo.Student;

public interface StudentService {

    void addStudent(Student student);

    String getStudentInfo(Student student);
}
