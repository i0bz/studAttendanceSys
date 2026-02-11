package repository;

import entity.AttendanceSheet;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendanceRegistry implements Serializable {
    private final HashMap<LocalDate, AttendanceSheet> registry;
    private final StudentRoster roster;

    public AttendanceRegistry(StudentRoster roster) {
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

    public List<LocalDate> attendanceDateLists() {
        return new ArrayList<>(registry.keySet());
    }

}
