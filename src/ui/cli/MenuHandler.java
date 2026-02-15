package ui.cli;

import controllers.AttendanceSystemController;

import java.util.Scanner;

public class MenuHandler {
    private final InputValidator validator;
    private final StudentManagemenCLI studentManagement;
    private final AttendanceManagementCLI attendanceManagement;
    private final AttendanceSystemCLI attendanceSystem;


    public MenuHandler(InputValidator validator,
                       StudentManagemenCLI studentManagement,
                       AttendanceManagementCLI attendanceManagement,
                       AttendanceSystemCLI attendanceSystem) {
        this.validator = validator;
        this.studentManagement = studentManagement;
        this.attendanceManagement = attendanceManagement;
        this.attendanceSystem = attendanceSystem;
    }

    public void managementUI() {
        int decision;

        while (true) {

            System.out.println();
            System.out.println("1. Student Management");
            System.out.println("2. Attendance Management");
            System.out.println("3. Attendance System");
            System.out.println("4. Attendance Mode");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            decision = validator.safeIntInput();

            while (decision <= 0 || decision >= 6) {
                System.out.print("Enter 1, 2, 3, 4, and 5 only: ");
                decision = validator.safeIntInput();
            }


            switch (decision) {
                case 1:
                    studentManagement.initCLI();
                    break;
                case 2:
                    attendanceManagement.initCLI();
                    break;
                case 3:
                    attendanceSystem.initCLI();
                    break;
                case 4:
                    attendanceSystem.attendanceMode();
                    break;
                case 5:
                    return;
            }
        }

    }
}
