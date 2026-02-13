package entity;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttendanceSheet implements Serializable {
    private final LocalDate date;
    private final Map<Integer, Boolean> attendanceRoster;


    public AttendanceSheet(LocalDate date, Map<Integer, Boolean> attendanceRoster) {
        this.date = date;
        this.attendanceRoster = attendanceRoster;
    }


    //Attendance Manipulation
    public void toggleAttendance(int studentUID) {
        attendanceRoster.computeIfPresent(studentUID, (key, value) -> !value);
    }

//    public void markAbsent(int studentUID) {
//        if (attendanceRoster.replace(studentUID, false) == null) throw new RuntimeException("entity.Student does not exist");
//    }
//    public void markPresent(int studentUID) {
//        attendanceRoster.replace(studentUID, true);
//    }



    //Attendance Checkers
    public boolean hasStudent(int studentUID) {
        return attendanceRoster.containsKey(studentUID);
    }
    public boolean isPresent(int studentUID) {
        return attendanceRoster.get(studentUID);
    }




    //Query
    public List<Integer> attendanceStudentsUIDList() {
        return new ArrayList<>(attendanceRoster.keySet());
    }


    //Getter
    public LocalDate date(){
        return this.date;
    }




}
