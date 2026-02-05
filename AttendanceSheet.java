import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttendanceSheet {
    private final LocalDate date;
    private final Map<Integer, Boolean> attendanceRoster;


    AttendanceSheet(LocalDate date, Map<Integer, Boolean> attendanceRoster) {
        this.date = date;
        this.attendanceRoster = attendanceRoster;
    }

    public void toggleAttendance(int studentUID) {
        attendanceRoster.computeIfPresent(studentUID, (key, value) -> !value);
    }

    public void markAbsent(int studentUID) {
        if (attendanceRoster.replace(studentUID, false) == null) throw new RuntimeException("Student does not exists");
    }

    public void markPresent(int studentUID) {
        attendanceRoster.replace(studentUID, true);
    }

    public boolean isPresent(int studentUID) {
        return attendanceRoster.containsKey(studentUID);
    }

    public boolean hasStudent(int studentUID) {
        return attendanceRoster.containsKey(studentUID);
    }

    public List<Integer> attendanceStudentsList() {
        return new ArrayList<>(attendanceRoster.keySet());
    }

    public LocalDate date(){
        return this.date;
    }
}
