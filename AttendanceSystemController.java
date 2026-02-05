
public class AttendanceSystemController {
    private final StudentManagementService studentManagement;
    private final StudentAttendanceService AttendanceService;

    /**
     * Instantiates a new Attendance system controller.
     *
     * @param managementService the student management service
     * @param attendanceService the attendance service
     */
    AttendanceSystemController(StudentManagementService managementService, StudentAttendanceService attendanceService) {
        this.studentManagement = managementService;
        this.AttendanceService = attendanceService;
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
     * @param name Full name of the Student
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
        AttendanceService.createAttendance(ParseUtility.parseDate(date));
    }

    /**
     * Removes attendance.
     *
     * @param date Date in yyyy-MM-dd format
     */
    public void removeAttendance(String date) {
        AttendanceService.deleteAttendance(ParseUtility.parseDate(date));
    }

    /**
     * Toggle attendance of a Student.
     *
     * @param uid  the School ID of a student
     * @param date the date of the attendance in yyyy-MM-dd format
     */
    public void toggleAttendance(String uid, String date) {
        AttendanceService.toggleAttendance(ParseUtility.parseDate(date), ParseUtility.parseUID(uid));
    }
}
