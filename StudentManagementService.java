public class StudentManagementService {
    private final StudentRoster roster;

    StudentManagementService(StudentRoster roster) {
        this.roster = roster;
    }
    public void enrollStudent(String name, int uid) {
        roster.addStudentToRoster(new Student(name, uid));
    }
    public void dropStudent(int uid) {
        roster.dropStudentFromRoster(uid);
    }
}
