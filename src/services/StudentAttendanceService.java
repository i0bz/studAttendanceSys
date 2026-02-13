package services;

import entity.AttendanceSheet;
import repository.AttendanceRegistry;

import java.time.LocalDate;
import java.util.List;

public class StudentAttendanceService {

    private final AttendanceRegistry registry;

    public StudentAttendanceService(AttendanceRegistry registry) {
        this.registry = registry;
    }


    //Attendance Management
    public void createAttendance(LocalDate date) {
        registry.addAttendance(date);
    }
    public void deleteAttendance(LocalDate date) {
        registry.removeAttendance(date);
    }


    //Attendance Manipulation
    public void toggleAttendance(LocalDate date, int uid) {
        AttendanceSheet attendances =  registry.queryAttendance(date);
        if (attendances == null) throw new RuntimeException("Date given has no attendance");
        attendances.toggleAttendance(uid);
    }


    //Query functions
    public List<LocalDate> attendanceDateList() {
        return registry.attendanceDateList();
    }
    public AttendanceSheet queryAttendance(LocalDate date) {
        return registry.queryAttendance(date);
    }

}
