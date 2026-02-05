public class Main {
    static void main() {
        StudentRoster studentRepo = new StudentRoster();
        AttendanceRegistry attendanceRepo = new AttendanceRegistry(studentRepo);
        StudentManagementService sService = new StudentManagementService(studentRepo);
        StudentAttendanceService aService = new StudentAttendanceService(attendanceRepo);
        AttendanceSystemController controller = new AttendanceSystemController(sService,aService);

        controller.enrollStudent("Kent Andrei Tyler P. Ancheta", "25-140001");
        controller.enrollStudent("Aedrian Clyde C. Aridao", "25-140167");

        controller.addAttendance("2026-02-05");
        controller.addAttendance("2026-02-06");


        controller.toggleAttendance("25-140001", "2026-02-05");


        AttendanceSheet a = attendanceRepo.queryAttendance(ParseUtility.parseDate("2026-02-05"));

        for (Integer i : a.attendanceStudentsList()) {
            System.out.println(ParseUtility.unparseUID(i));
        }

    }
}
