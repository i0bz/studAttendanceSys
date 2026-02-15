import controllers.AttendanceControllerFactory;
import controllers.AttendanceSystemController;
import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.StudentAttendanceService;
import services.StudentManagementService;
import ui.cli.CommandLineInterface;
import utility.Persist;

public class Main {
    static void main(String[] args) {

        AttendanceControllerFactory attendanceFactory = new AttendanceControllerFactory();
        AttendanceSystemController controller = attendanceFactory.createController();




        for (String arg : args) {
            if (arg.equals("--cli")) {
                CommandLineInterface cli = new CommandLineInterface(controller);
                cli.managementUI();
            }
        }

        Persist.saveRosterFile(attendanceFactory.roster());
        Persist.saveRegistry(attendanceFactory.registry());
    }
}
