package ui;

import controllers.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CommandLineInterface {
    private boolean exit = false;
    private final AttendanceSystemController attendanceSystem;
    private Scanner input = new Scanner(System.in);

    public CommandLineInterface(AttendanceSystemController controller) {
        this.attendanceSystem = controller;
    }

    private void studentManagement() {
        int decision = 0;


        while (decision != 3) {
            System.out.println("1. Enroll Student");
            System.out.println("2. Drop Student");
            System.out.println("3. List Roster");
            System.out.println("4. Return");
            System.out.print("Enter choice: ");

            decision = input.nextInt();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3 and 4 only: ");
                decision = input.nextInt();
            }




            input.nextLine();
            switch (decision) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    dropStudent();
                    break;
                case 3:
                    listStudentNames();
                    break;
                case 4:
                    return;
            }
        }


    }

    private void addStudent() {

        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Enter valid uid: ");
        String uid = input.nextLine();

        while (true) {
            try {
                attendanceSystem.enrollStudent(name, uid);
                break;
            } catch (RuntimeException e) {
                System.out.println("Invalid UID!!!");
                System.out.println("Reenter: ");
                uid = input.nextLine();
            }
        }


    }

    private void dropStudent() {

        System.out.print("Enter valid uid: ");
        String uid = input.nextLine();

        while (true) {
            try {
                attendanceSystem.dropStudent(uid);
                break;
            } catch (RuntimeException e) {
                System.out.println("Invalid UID!!!");
                System.out.println("Reenter: ");
                uid = input.nextLine();
            }
        }


    }

    private void listStudentNames() {

        System.out.println("");
        System.out.println("");
        Map<String, String> roster = attendanceSystem.rosterLists();
        int i = 0;
        for (Map.Entry<String, String> student : roster.entrySet()) {
            System.out.printf("%d. %s\t%s\n", ++i, student.getValue(), student.getKey());
        }
        System.out.println("");
        System.out.println("");

    }


    private void attendanceManagementUI() {
        int decision = 0;



        while (decision != 3) {
            System.out.println("1. Create Attendance");
            System.out.println("2. Delete Attendance");
            System.out.println("3. Return");
            System.out.print("Enter choice: ");

            decision = input.nextInt();

            while (decision <= 0 || decision >= 4) {
                System.out.print("Enter 1, 2, 3 only: ");
                decision = input.nextInt();
            }


            input.nextLine();

            switch (decision) {
                case 1:
                    createAttendance();
                    break;
                case 2:
                    deleteAttendance();
                    break;
                case 3:
                    return;
            }
        }

    }



    private void createAttendance() {

        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = input.nextLine();

        while (true) {
            try {
                attendanceSystem.addAttendance(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date!!!");
                System.out.println("Reenter: ");
                date = input.nextLine();
            }
        }


    }


    private void deleteAttendance() {

        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = input.nextLine();

        while (true) {
            try {
                attendanceSystem.removeAttendance(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date!!!");
                System.out.println("Reenter: ");
                date = input.nextLine();
            }
        }


    }


//    private void attendanceSystemUI() {
//        List<String> attendanceLists = attendanceSystem.attendanceDateLists();
//        int i = 0, decision = 0;
//
//        if (attendanceLists.isEmpty()) {
//            System.out.println("There are no registered attendances.");
//            return;
//        }
//
//        for (String attendanceDate : attendanceLists) {
//            System.out.println(i++ + attendanceDate);
//        }
//        System.out.print("Select Attendance: ");
//
//        return;
//    }

    public void managementUI() {
        int decision = 0;

        while (true) {

            System.out.println("1. Student Management");
            System.out.println("2. Attendance Management");
            System.out.println("3. Attendance System");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            decision = input.nextInt();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3, and 4 only: ");
                decision = input.nextInt();
            }


            switch (decision) {
                case 1:
                    studentManagement();
                    break;
                case 2:
                    attendanceManagementUI();
                    break;
//                case 3:
//                    break;
                case 4:
                    return;
            }
        }

    }





    private void exitUI() {
         exit = true;
    }





    public boolean exit() {
        return exit;
    }
}
