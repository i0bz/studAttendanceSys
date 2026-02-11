import controllers.AttendanceSystemController;
import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.StudentAttendanceService;
import services.StudentManagementService;
import ui.CommandLineInterface;
import utility.Persist;

public class Main {
    static void main(String[] args) {

        StudentRoster roster = Persist.loadRoster();
        AttendanceRegistry registry = Persist.loadRegistry(roster);
        StudentAttendanceService attendanceService = new StudentAttendanceService(registry);
        StudentManagementService managementService = new StudentManagementService(roster);
        AttendanceSystemController controller = new AttendanceSystemController(managementService, attendanceService);


        for (String arg : args) {
            if (arg.equals("--cli")) {
                CommandLineInterface cli = new CommandLineInterface(controller);
                cli.managementUI();
            }
        }

        Persist.saveRosterFile(roster);
        Persist.saveRegistry(registry);
    }
}
