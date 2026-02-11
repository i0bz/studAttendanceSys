package services;

import entity.Student;
import repository.StudentRoster;

import java.util.List;
import java.util.Map;

public class StudentManagementService {
    private final StudentRoster roster;

    public StudentManagementService(StudentRoster roster) {
        this.roster = roster;
    }
    public void enrollStudent(String name, int uid) {
        roster.addStudentToRoster(new Student(name, uid));
    }
    public void dropStudent(int uid) {
        roster.dropStudentFromRoster(uid);
    }
    public List<String> queryAllStudentName() {
        return roster.queryAllStudentName();
    }
    public Map<Integer, Student> queryAllStudent() {
        return roster.queryAllStudent();
    }
    public List<Integer> queryAllStudentId() {
        return roster.queryAllStudentId();
    }
}
