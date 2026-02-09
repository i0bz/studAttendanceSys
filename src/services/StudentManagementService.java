package services;

import entity.Student;
import repository.StudentRoster;

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
}
