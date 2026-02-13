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


    //Attendance Management
    /**
     * Add attendance sheet.
     *
     * @param date the date
     */
    public void addAttendance(LocalDate date) {
        registry.putIfAbsent(date, new AttendanceSheet(date, roster));
    }
    /**
     * Remove attendance sheet.
     *
     * @param date the date
     */
    public void removeAttendance(LocalDate date) {
        registry.remove(date);
    }



    //Query functions
    /**
     * Query attendance sheet.
     *
     * @param date the date
     * @return the attendance sheet
     */
    public AttendanceSheet queryAttendance(LocalDate date) {
        return registry.get(date);
    }
    /**
     * Attendance date list.
     *
     * @return the list
     */
    public List<LocalDate> attendanceDateList() {
        return new ArrayList<>(registry.keySet()).stream().sorted().toList();
    }
}
