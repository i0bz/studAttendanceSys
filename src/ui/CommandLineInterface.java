package ui;

import controllers.*;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CommandLineInterface {


    private final AttendanceSystemController attendanceSystem;
    private final Scanner input = new Scanner(System.in);


    public CommandLineInterface(AttendanceSystemController controller) {
        this.attendanceSystem = controller;
    }


    /*
    CLI control flows
     */
    public void managementUI() {
        int decision;

        while (true) {

            System.out.println("1. Student Management");
            System.out.println("2. Attendance Management");
            System.out.println("3. Attendance System");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            decision = safeIntInput();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3, and 4 only: ");
                decision = safeIntInput();
            }


            switch (decision) {
                case 1:
                    studentManagement();
                    break;
                case 2:
                    attendanceManagementUI();
                    break;
                case 3:
                    attendanceSystemUI();
                    break;
                case 4:
                    return;
            }
        }

    }
    private void studentManagement() {
        int decision = 0;


        while (decision != 4) {
            System.out.println("1. Enroll Student");
            System.out.println("2. Drop Student");
            System.out.println("3. List Roster");
            System.out.println("4. Return");
            System.out.print("Enter choice: ");

            decision = safeIntInput();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3 and 4 only: ");
                decision = safeIntInput();
            }


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
            }
        }


    }
    private void attendanceManagementUI() {
        int decision = 0;



        while (decision != 4) {
            System.out.println("1. Create Attendance");
            System.out.println("2. Delete Attendance");
            System.out.println("3. List Attendances");
            System.out.println("4. Return");
            System.out.print("Enter choice: ");

            decision = safeIntInput();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3 and 4 only: ");
                decision = safeIntInput();
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



    /*
    Student Management CLI functionalities
     */
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
                System.out.print("Reenter: ");
                uid = input.nextLine();
            }
        }


    }
    private void listStudentNames() {

        System.out.println();
        System.out.println();
        Map<String, String> roster = attendanceSystem.rosterLists();
        int i = 0;
        for (Map.Entry<String, String> student : roster.entrySet()) {
            System.out.printf("%d. %s\t%s\n", ++i, student.getValue(), student.getKey());
        }
        System.out.println();
        System.out.println();

    }


    /*
    Attendance Management CLI functionalities
     */
    private void createAttendance() {

        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = input.nextLine();

        while (true) {
            try {
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

        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = input.nextLine();

        while (true) {
            try {
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
        List<String> attendances = attendanceSystem.attendanceDateLists();
        int i = 0;
        System.out.println();
        System.out.println();

        for (String attendance : attendances) {
            System.out.println(++i + ". " + attendance);
        }

        System.out.println();
    }



    private void attendanceSystemUI() {
        List<String> attendanceLists = attendanceSystem.attendanceDateLists();
        int i = 0, decision;


        System.out.println();
        System.out.println();
        if (attendanceLists.isEmpty()) {
            System.out.println("There are no registered attendances.");
            return;
        }



        for (String attendanceDate : attendanceLists) {
            System.out.println(++i + ". " + attendanceDate);
        }

        System.out.println();
        System.out.print("Select Attendance: ");
        decision = safeIntInput();

        String date;

        while (true) {
            try {
                date = attendanceLists.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = safeIntInput();
            }
        }

        List<String> studentUIDs = attendanceSystem.attendanceStudentUIDLists(date);
        Map<String, String> rosterList = attendanceSystem.rosterLists();
        System.out.println();
        System.out.println();

        i = 0;
        for (String studentID : studentUIDs) {
            System.out.println(++i + ". " + rosterList.get(studentID) + "\t " + studentID + " " + attendanceSystem.isPresent(studentID, date) );
        }



        String studentChosen;
        System.out.println();
        System.out.print("Select student to toggle: ");


        decision = safeIntInput();

        while (true) {
            try {
                if (studentUIDs.size() < decision) return;
                studentChosen = studentUIDs.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = safeIntInput();
            }
        }
        attendanceSystem.toggleAttendance(studentChosen, date);

    }





    private int safeIntInput() {
        int tmp;
        try {
            tmp = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
            return -1;
        }
        return tmp;
    }
}
