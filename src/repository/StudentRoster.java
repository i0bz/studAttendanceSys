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

    public void addStudentToRoster(Student student) {
        studentRoster.putIfAbsent(student.uid(), student);
    }
    public void dropStudentFromRoster(int uid) {
        studentRoster.remove(uid);
    }
    public Map<Integer, Boolean> rosterCopy() {
        HashMap<Integer, Boolean> copy = new HashMap<>();
        for (Map.Entry<Integer, Student> entry : studentRoster.entrySet()) {
            copy.putIfAbsent(entry.getValue().uid(), false);
        }
        return copy;
    }
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
