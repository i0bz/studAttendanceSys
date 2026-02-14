
package controllers;


//Domains
import entity.AttendanceSheet;

//Services
import services.StudentAttendanceService;
import services.StudentManagementService;

//Utilities
import utility.ParseUtility;

import java.util.*;

import java.time.LocalDate;
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

    //Student Management
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




    //Attendance Management
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
     * Toggle attendance of Student.
     *
     * @param uid  the School ID of a student
     * @param date the date of the attendance in yyyy-MM-dd format
     */


    //Attendance System
    public void toggleAttendance(String uid, String date) {
        attendanceService.toggleAttendance(ParseUtility.parseDate(date), ParseUtility.parseUID(uid));
    }
    public boolean isPresent(String uid, String date) {
        LocalDate parsedDate = ParseUtility.parseDate(date);
        int studentID = ParseUtility.parseUID(uid);

        AttendanceSheet sheet = attendanceService.queryAttendance(parsedDate);

        return sheet.isPresent(studentID);
    }


    public List<String> attendanceDateLists() {
        return attendanceService.attendanceDateList()
                .stream()
                .map(LocalDate::toString)
                .toList();
    }


    //Querying (Student Management specific)
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


    //Querying (Attendance specific)
    public SortedSet<String> attendanceStudentUIDLists(String date) {
        LocalDate parsedDate = ParseUtility.parseDate(date);
        AttendanceSheet sheet = attendanceService.queryAttendance(parsedDate);
        return sheet.attendanceStudentsUIDList().stream().map(ParseUtility::unparseUID).collect(Collectors.toCollection(TreeSet::new));
    }
    public Map<String, String> attendanceRoster(String date) {
        AttendanceSheet attendanceSheet = attendanceService.queryAttendance(ParseUtility.parseDate(date));

        return studentManagement.queryAllStudent()
                .entrySet()
                .stream()
                .filter(entry -> attendanceSheet.attendanceStudentsUIDList().contains(entry.getKey()))
                .collect(Collectors.toMap(entry -> ParseUtility.unparseUID(entry.getKey()),
                        entry -> entry.getValue().name()));
    }

}
