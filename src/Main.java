import entity.AttendanceSheet;
import entity.Student;
import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.StudentAttendanceService;
import services.StudentManagementService;
import utility.ParseUtility;
import utility.Persist;

public class Main {
    static void main() {
        StudentRoster a = Persist.loadRoster();
        AttendanceRegistry b = Persist.loadRegistry(a);
        StudentAttendanceService c = new StudentAttendanceService(b);
        StudentManagementService d = new StudentManagementService(a);
        AttendanceSystemController e = new AttendanceSystemController(d, c);




        AttendanceSheet siak = b.queryAttendance(ParseUtility.parseDate("2026-09-01"));

        System.out.println(siak.isPresent(25140001));


        Persist.saveRegistry(b);
        Persist.saveRosterFile(a);
    }
}
