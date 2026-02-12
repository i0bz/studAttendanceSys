
package controllers;


//Domains
import entity.AttendanceSheet;

//Services
import services.StudentAttendanceService;
import services.StudentManagementService;

//Utilities
import utility.ParseUtility;

import javax.lang.model.type.MirroredTypeException;
import java.util.AbstractMap;
import java.util.List;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AttendanceSystemController {
    private final StudentManagementService studentManagement;
    private final StudentAttendanceService attendanceService;

    /**
     * Instantiates a new Attendance system controller.
     *
     * @param managementService the student management service
     * @param attendanceService the attendance service
     */
    public AttendanceSystemController(StudentManagementService managementService, StudentAttendanceService attendanceService) {
        this.studentManagement = managementService;
        this.attendanceService = attendanceService;
    }


    /**
     * Drop student.
     *
     * @param uid School ID number of the student
     */
    public void dropStudent(String uid) {
        studentManagement.dropStudent(ParseUtility.parseUID(uid));
    }

    /**
     * Enroll student.
     *
     * @param name Full name of the entity.Student
     * @param uid  School ID number of the student
     */
    public void enrollStudent(String name, String uid) {
        studentManagement.enrollStudent(name, ParseUtility.parseUID(uid));
    }


    /**
     * Adds an attendance for a particular date.
     *
     * @param date Date in yyyy-MM-dd format
     */
    public void addAttendance(String date) {
        attendanceService.createAttendance(ParseUtility.parseDate(date));
    }

    /**
     * Removes attendance.
     *
     * @param date Date in yyyy-MM-dd format
     */
    public void removeAttendance(String date) {
        attendanceService.deleteAttendance(ParseUtility.parseDate(date));
    }

    /**
     * Toggle attendance of a entity.Student.
     *
     * @param uid  the School ID of a student
     * @param date the date of the attendance in yyyy-MM-dd format
     */
    public void toggleAttendance(String uid, String date) {
        attendanceService.toggleAttendance(ParseUtility.parseDate(date), ParseUtility.parseUID(uid));
    }

    public List<String> attendanceStudentLists(String date) {
        LocalDate parsedDate = ParseUtility.parseDate(date);
        AttendanceSheet sheet = attendanceService.queryAttendance(parsedDate);
        return sheet.attendanceStudentsList().stream().map(ParseUtility::unparseUID).toList();
    }

    public List<String> attendanceDateLists() {
        return attendanceService.attendanceDateLists()
                .stream()
                .map(LocalDate::toString)
                .toList();
    }


    public List<String> rosterNameLists() {
        return studentManagement.queryAllStudentName();
    }

    public Map<String, String> rosterLists() {
        return new TreeMap<>(studentManagement.queryAllStudent()
                .entrySet()
                .stream()
                .collect(Collectors
                        .toMap(entry -> ParseUtility.unparseUID(entry.getKey()),
                                entry -> entry.getValue().name())));
    }

    public Map<String, String> attendanceRoster(String date) {
        AttendanceSheet attendanceSheet = attendanceService.queryAttendance(ParseUtility.parseDate(date));

        return studentManagement.queryAllStudent()
                .entrySet()
                .stream()
                .filter(entry -> attendanceSheet.attendanceStudentsList().contains(entry.getKey()))
                .collect(Collectors.toMap(entry -> ParseUtility.unparseUID(entry.getKey()),
                        entry -> entry.getValue().name()));

    }

}
