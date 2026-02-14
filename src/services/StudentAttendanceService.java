package services;

import entity.AttendanceSheet;
import entity.Student;
import repository.AttendanceRegistry;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    public SortedSet<LocalDate> queryAttendanceDateList() {
        return registry.attendanceDateList();
    }
    public AttendanceSheet queryAttendance(LocalDate date) {
        return registry.queryAttendance(date);
    }
    public List<String> queryAttendanceNames(LocalDate date) {
        AttendanceSheet sheet = queryAttendance(date);
        Set<Student> studentSet = sheet.attendanceStudentsSet();
        return new ArrayList<>(studentSet.stream().map(Student::name).sorted().toList());
    }
    public SortedSet<Integer> queryAttendanceIDs(LocalDate date) {
        AttendanceSheet sheet = queryAttendance(date);
        return sheet.attendanceStudentsSet().stream().map(Student::uid).collect(Collectors.toCollection(TreeSet::new));
    }
    public Set<Map.Entry<Integer, String>> queryAttendanceStudents(LocalDate date) {
        AttendanceSheet sheet = queryAttendance(date);
        return sheet.attendanceStudentsSet()
                .stream()
                .map(student -> Map.entry(student.uid(),student.name()))
                .collect(Collectors.toCollection(HashSet::new));
    }

}
