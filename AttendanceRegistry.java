import java.time.LocalDate;
import java.util.HashMap;

public class AttendanceRegistry {
    private final HashMap<LocalDate, AttendanceSheet> registry;
    private final StudentRoster roster;

    AttendanceRegistry(StudentRoster roster) {
        this.registry = new HashMap<>();
        this.roster = roster;
    }

    public void addAttendance(LocalDate date) {
        registry.putIfAbsent(date, new AttendanceSheet(date, roster.rosterCopy()));
    }
    public void removeAttendance(LocalDate date) {
        registry.remove(date);
    }

    public AttendanceSheet queryAttendance(LocalDate date) {
        return registry.get(date);
    }


}
