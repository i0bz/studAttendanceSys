package repository;

import entity.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentRoster implements Serializable {
    private final HashMap<Integer, Student> studentRoster;

    public StudentRoster() {
        studentRoster = new HashMap<>();
    }


    //Student Roster Management
    public void addStudentToRoster(Student student) {
        studentRoster.putIfAbsent(student.uid(), student);
    }
    public void dropStudentFromRoster(int uid) {
        studentRoster.remove(uid);
    }



    //Query functions
    public Student queryStudent(int uid) {
        return studentRoster.get(uid);
    }
    public boolean studentExists(int uid) {
        return studentRoster.containsKey(uid);
    }
    public Map<Integer, Student> queryAllStudent() {
        return studentRoster;
    }
    public List<Integer> queryAllStudentId() {
        return new ArrayList<>(studentRoster.keySet());
    }
    public List<String> queryAllStudentName() {
        return studentRoster.values().stream().map(Student::name).toList();
    }
}
