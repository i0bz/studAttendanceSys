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

    public void createAttendance(LocalDate date) {
        registry.addAttendance(date);
    }
    public void deleteAttendance(LocalDate date) {
        registry.removeAttendance(date);
    }


    public void toggleAttendance(LocalDate date, int uid) {
        AttendanceSheet attendances =  registry.queryAttendance(date);
        if (attendances == null) throw new RuntimeException("Date given has no attendance");
        if (!attendances.hasStudent(uid)) throw new RuntimeException("entity.Student not in Attendance Roster");
        attendances.toggleAttendance(uid);
    }

    public List<LocalDate> attendanceDateLists() {
        return registry.attendanceDateLists();
    }

    public AttendanceSheet queryAttendance(LocalDate date) {
        return registry.queryAttendance(date);
    }

}
