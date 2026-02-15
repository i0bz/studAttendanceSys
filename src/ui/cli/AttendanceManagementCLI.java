package ui.cli;

import controllers.AttendanceSystemController;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AttendanceManagementCLI {
    private final InputValidator validator;
    private final Scanner input;
    private final AttendanceSystemController attendanceSystem;

    AttendanceManagementCLI(InputValidator validator, Scanner input, AttendanceSystemController attendanceSystem) {
        this.validator = validator;
        this.input = input;
        this.attendanceSystem = attendanceSystem;
    }


    void initCLI() {
        System.out.println();
        System.out.println();
        int decision = 0;



        while (decision != 4) {
            System.out.println("1. Create Attendance");
            System.out.println("2. Delete Attendance");
            System.out.println("3. List Attendances");
            System.out.println("4. Return");
            System.out.print("Enter choice: ");

            decision = validator.safeIntInput();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3 and 4 only: ");
                decision = validator.safeIntInput();
            }


            switch (decision) {
                case 1:
                    createAttendance();
                    break;
                case 2:
                    deleteAttendance();
                    break;
                case 3:
                    listAttendances();
                    break;
            }
        }

    }



    private void createAttendance() {
        System.out.println();
        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = input.nextLine();

        while (true) {
            try {
                if (date.equals("q")) return;
                attendanceSystem.addAttendance(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date!!!");
                System.out.print("Reenter: ");
                date = input.nextLine();
            }
        }


    }


    private void deleteAttendance() {
        System.out.println();

        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = input.nextLine();

        while (true) {
            try {
                if (date.equals("q")) return;
                attendanceSystem.removeAttendance(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date!!!");
                System.out.print("Reenter: ");
                date = input.nextLine();
            }
        }


    }


    private void listAttendances() {
        System.out.println();
        System.out.println();
        List<String> attendances = attendanceSystem.attendanceDateLists();
        int i = 0;

        for (String attendance : attendances) {
            System.out.println(++i + ". " + attendance);
        }

        System.out.println();
    }
}
